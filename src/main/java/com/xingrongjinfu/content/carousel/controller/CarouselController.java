package com.xingrongjinfu.content.carousel.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.commodity.service.ICommodityService;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.product.service.IProductService;
import com.xingrongjinfu.utils.StringUtil;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.content.ContentConstant;
import com.xingrongjinfu.content.carousel.common.CarouselConstant;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.carousel.service.ICarouselService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;

/**
 * 业务处理   轮播管理（平台端）
 * 
 * @author
 */
@Controller
@RequestMapping(ContentConstant.CONTENT_URL)
public class CarouselController extends BaseController {

	@Autowired
	private ICarouselService carouselService;
	@Autowired
	private IProductService productService;


	/**
	 * 跳转列表界面
	 */
	@RequestMapping(CarouselConstant.CAROUSEL_URL)
	public ModelAndView loadCarousel() {
		ModelAndView modelAndView = this.getModelAndView(CarouselConstant.CAROUSEL_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("禁用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		
		return modelAndView;
	}

	/**
	 * 跳转新增界面
	 */
	@RequestMapping(CarouselConstant.TO_ADD_URL)
	public ModelAndView toCarouselAdd(String carouselId) {
		ModelAndView modelAndView = this.getModelAndView(CarouselConstant.ADD_PAGE);

		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("禁用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);

		return modelAndView;
	}

	@RequestMapping(CarouselConstant.PRODUCTTREE_URL)
	public @ResponseBody List<Map<String, Object>> productTree(){
		List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();
//		PageUtilEntity pageUtilEntity=new PageUtilEntity();
		List<Category> categoryList=productService.findAllClass();
		List<Product> productList=productService.getProductList();
		for(Category category:categoryList){
			Map<String,Object> map=new HashMap<>();
			map.put("id", category.getCategoryId());
			map.put("pId", category.getParentId());
			map.put("name", category.getCategoryName());
			map.put("isCategory", true);
			resMapTrees.add(map);
		}
		for(Product product:productList){
			Map<String,Object> map=new HashMap<>();
			map.put("id", product.getCommodityId());
			map.put("pId", product.getCategoryId());
			map.put("name", product.getCommodityName());
			map.put("isCategory", false);
			map.put("icon", "/uiloader/lib/zTree/v3/css/zTreeStyle/img/diy/5.png");
			resMapTrees.add(map);
		}
		return resMapTrees;
	}

	@RequestMapping(CarouselConstant.COMMODITY_URL)
	public @ResponseBody List<Map<String, Object>> commodityTree(){
		List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();
//		PageUtilEntity pageUtilEntity=new PageUtilEntity();
		List<Category> categoryList=productService.findAllClass();
		List<Category> productList=productService.findFirstCategory();
		for(Category category:productList){
			Map<String,Object> map=new HashMap<>();
			map.put("id", category.getCategoryId());
			map.put("name", category.getCategoryName());
			map.put("pId", category.getParentId());
			map.put("isCategory", true);
			resMapTrees.add(map);
		}
		for(Category category:categoryList){
			Map<String,Object> map=new HashMap<>();
			map.put("id", category.getCategoryId());
			map.put("pId", category.getParentId());
			map.put("name", category.getCategoryName());
			map.put("isCategory", false);
			map.put("icon", "/uiloader/lib/zTree/v3/css/zTreeStyle/img/diy/5.png");
			resMapTrees.add(map);
		}
		return resMapTrees;
	}

	/**
	 * 跳转修改界面
	 */
	@RequestMapping(CarouselConstant.TO_MODIFY_URL)
	public ModelAndView toCarouselModify(@RequestParam(required = true) String carouselId) {
		ModelAndView modelAndView = this.getModelAndView(CarouselConstant.MODIFY_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("禁用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		String name=null;
		String id=null;
		if (carouselId != null) {
			Carousel carousel=carouselService.findByCarouselId(carouselId);
			if(carousel.getType().equals("1")){
				name=carousel.getUrl();
			}else if(carousel.getType().equals("2")){
				Product product=new Product();
				product.setCommodityId(carousel.getUrl());
				Product products=productService.findProductById(product);
				name=products.getCommodityName();
				id=carousel.getUrl();
			}else if(carousel.getType().equals("3")){
                 Category category=productService.getCategoryById(carousel.getUrl());
                 name=category.getCategoryName();
                 id=carousel.getUrl();
			}
			modelAndView.addObject("carousel",carousel);
			modelAndView.addObject("name",name);
			modelAndView.addObject("id",id);
		}

		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(CarouselConstant.CAROUSEL_LIST_URL)
	public ModelAndView carouselList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Carousel> tableDataInfo = carouselService.pageInfoQuery(pageUtilEntity);
		Integer i=1;
		for (Carousel carousel : tableDataInfo) {
			carousel.setSort(pageUtilEntity.getPage()+(i++));
			}


		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "轮播管理", action = "轮播管理-保存", isSaveRequestData = true)
	@RequestMapping(CarouselConstant.SAVE_CAROUSEL_URL)
	public @ResponseBody Message saveCarousel(Carousel carousel,String carouselId,MultipartFile picture,String radio,String category1,String commodity1,String url) {
		int result = 0;		
        if(StringUtil.nullOrBlank(commodity1)&&StringUtil.nullOrBlank(category1)&&StringUtil.nullOrBlank(carousel.getUrl())){
        	return new Message(false,"请完善所有信息");
		}
		
        carousel.setType(radio);
        if(radio.equals("2")){
			if(!StringUtil.nullOrBlank(commodity1)){
				carousel.setUrl(commodity1);
			}
		}
		if(radio.equals("3")){
        	if(!StringUtil.nullOrBlank(category1)){
        		carousel.setUrl(category1);
			}
        }

		try {
		    if(picture.getSize()!=0){
    		    AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
    			String key = aliyunOSSClientUtil.uploadImg(picture);
    			if(key!=null){
    				
    				String filePath = aliyunOSSClientUtil.FOLDER + AliyunOSSClientUtil.filePath;
    				carousel.setCarouselImg(filePath);
    			}
		    }
		} catch (OSSException e) {
			e.printStackTrace();
            return new Message(result);
		}
		
		
		if (carousel.getCarouselId() != null) {
			result = carouselService.updateCarouselInfo(carousel);
		} else {
			result = carouselService.addCarouselInfo(carousel);
		}
		
		return new Message(result);

	}
	
	/**
     * 根据ID删除
     */
    @ActionControllerLog(title = "内容管理", action = "内容管理-删除轮播图", isSaveRequestData = true)
    @RequestMapping(CarouselConstant.DEL_URL)
    public @ResponseBody Message deleteCarouselById(Carousel carousel)
    {
        int result = 0;
        String id = carousel.getCarouselId();
        if (id != null)
        {           
                result = carouselService.deleteById(carousel);           
        }
        return new Message(result);
    }
    
    /**
     * 校验名称
     */
    @RequestMapping(CarouselConstant.CHECK_NAME_UNIQUE_URL)
    public @ResponseBody
    String checkNamesUnique(Carousel carousel) {
        String uniqueFlag = "0";
        if (carousel != null) {
            uniqueFlag = carouselService.checkNameUnique(carousel);
        }
        return uniqueFlag;
    }

	/**
	 * 启动/停用 操作
	 */
	@ActionControllerLog(title = "轮播管理", action = "轮播管理-启用/停用", isSaveRequestData = true)
	@RequestMapping(CarouselConstant.CHANGE_STATUS_URL)
	public @ResponseBody Message changeCarouselStatus(Carousel carousel) {
		int result = 0;
		String id = carousel.getCarouselId();
		if (id != null) {
			result = carouselService.changeCarouselStatus(carousel);
		}
		return new Message(result);
	}
}
