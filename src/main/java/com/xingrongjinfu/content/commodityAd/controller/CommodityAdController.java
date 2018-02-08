
package com.xingrongjinfu.content.commodityAd.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
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
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.content.ContentConstant;
import com.xingrongjinfu.content.commodityAd.common.CommodityAdConstant;
import com.xingrongjinfu.content.commodityAd.model.CommodityAd;
import com.xingrongjinfu.content.commodityAd.service.ICommodityAdService;
import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.IdUtil;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(ContentConstant.CONTENT_URL)
public class CommodityAdController extends BaseController {

	@Autowired
	private ICommodityAdService commodityAdService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(CommodityAdConstant.COMM_URL)
	public ModelAndView loadCommodityAd() {
		ModelAndView modelAndView = this.getModelAndView(CommodityAdConstant.COMM_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("停用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		
		List<SysCode> sysCodeList2 = new ArrayList<SysCode>();
		SysCode sysCode3 = new SysCode();
		sysCode3.setCodeid("1");
		sysCode3.setCodevalue("横栏广告");
		sysCodeList2.add(sysCode3);

		SysCode sysCode4 = new SysCode();
		sysCode4.setCodeid("2");
		sysCode4.setCodevalue("fit广告");
		sysCodeList2.add(sysCode4);

		modelAndView.addObject("typeList", sysCodeList2);
		
		return modelAndView;
	}

	/**
	 * 跳转新增界面
	 */
	@RequestMapping(CommodityAdConstant.TO_ADD_URL)
	public ModelAndView toCommodityAdAdd(String commodityAdId) {
		ModelAndView modelAndView = this.getModelAndView(CommodityAdConstant.ADD_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("停用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
//		
//		List<SysCode> sysCodeList2 = new ArrayList<SysCode>();
//		SysCode sysCode3 = new SysCode();
//		sysCode3.setCodeid("1");
//		sysCode3.setCodevalue("横栏广告");
//		sysCodeList2.add(sysCode3);
//
//		SysCode sysCode4 = new SysCode();
//		sysCode4.setCodeid("2");
//		sysCode4.setCodevalue("fit广告");
//		sysCodeList2.add(sysCode4);
//
		//modelAndView.addObject("typeList", this.getAllType());
		modelAndView.addObject("FLList", this.getFL());

		return modelAndView;
	}
	
	/**
	 * 跳转修改界面
	 */
	@RequestMapping(CommodityAdConstant.TO_MODIFY_URL)
	public ModelAndView toCommodityAdModify(@RequestParam(required = true) String commodityAdId) {
		ModelAndView modelAndView = this.getModelAndView(CommodityAdConstant.MODIFY_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("停用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		modelAndView.addObject("CommodityAd", this.commodityAdService.findByCommodityAdId(commodityAdId));
		//modelAndView.addObject("typeList", this.getAllType());
        modelAndView.addObject("FLList", this.getFL());
        modelAndView.addObject("commoditysList", this.getCommoditysByAdId(commodityAdId));
		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(CommodityAdConstant.COMM_LIST_URL)
	public ModelAndView commodityAdList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");

		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<TableDataInfo> tableDataInfo = commodityAdService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "商品广告", action = "商品广告-保存", isSaveRequestData = true)
	@RequestMapping(CommodityAdConstant.SAVE_COMM_URL)
	public @ResponseBody Message saveCommodityAd(CommodityAd commodityAd,String commodityAdId,MultipartFile picture) {
		int result = 0;		
		
		try {
		    if(picture.getSize() != 0){
		        AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
    			String key = aliyunOSSClientUtil.uploadImg(picture);
    			if(key!=null){
    				
    				String originalFilename = picture.getOriginalFilename();
    				String filePath = aliyunOSSClientUtil.FOLDER + AliyunOSSClientUtil.filePath;
    				commodityAd.setCommodityAdImg(filePath);
    				
    			}
		    }
		} catch (OSSException e) {
			e.printStackTrace();
            return new Message(result);
		}
		if (commodityAdId != null) {
		    commodityAd.setUpdateTime(new Date());
			result = commodityAdService.updateCommodityAdInfo(commodityAd);
		} else {
		    commodityAd.setCommodityAdId(IdUtil.get32UUID());
			result = commodityAdService.addCommodityAdInfo(commodityAd);
		}
		
		return new Message(result);

	}

	/**
	 * 启动/停用 操作
	 */
	@ActionControllerLog(title = "商品广告", action = "商品广告-启用/停用", isSaveRequestData = true)
	@RequestMapping(CommodityAdConstant.CHANGE_STATUS_URL)
	public @ResponseBody Message changeCommodityAdStatus(CommodityAd commodityAd) {
		int result = 0;
		String id = commodityAd.getCommodityAdId();
		if (id != null) {
			result = commodityAdService.changeCommodityAdStatus(commodityAd);
		}
		return new Message(result);
	}
	/**
	 * Description: 获取所有类型<br/>
	 *
	 * @author huYL
	 * @return
	 */
	public List<SysCode> getAllType(){
	    List<Integer> typeList = commodityAdService.getAllType();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Integer type:typeList){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(type+"");
            String name = "";
            if(type == 1){
                name = "横栏广告";
            }else if(type == 2){
                name="fit广告";
            }
            sysCode.setCodevalue(name);
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
	}
	/**
	 * Description: 获取所有分类<br/>
	 *
	 * @author huYL
	 * @return
	 */
	public List<SysCode> getFL(){
	    List<Category> typeList = commodityAdService.getFL();
	    List<SysCode> sysCodeList = new ArrayList<SysCode>();
	    for (Category category:typeList){
	        SysCode sysCode = new SysCode();
	        sysCode.setCodeid(category.getCategoryId());
	        sysCode.setCodevalue(category.getCategoryName());
	        sysCodeList.add(sysCode);
	    }
	    return sysCodeList;
	}
	/**
	 * Description: 获取某分类的所有商品<br/>
	 *
	 * @author huYL
	 * @return
	 */
	@ResponseBody
	@RequestMapping(CommodityAdConstant.GET_COMMODITYS_URL)
	public List<Commodity> getCommoditys(String categoryId){
	    List<Commodity> typeList = commodityAdService.getCommoditys(categoryId);
	    /*List<SysCode> sysCodeList = new ArrayList<SysCode>();
	    for (Commodity commodity:typeList){
	        SysCode sysCode = new SysCode();
	        sysCode.setCodeid(commodity.getCommodityId());
	        sysCode.setCodevalue(commodity.getCommodityName());
	        sysCodeList.add(sysCode);
	    }
	    return sysCodeList;*/
	    return typeList;
	    
	}
	/**
	 * Description: 获取某商品的所有分类商品<br/>
	 *
	 * @author huYL
	 * @param categoryId
	 * @return
	 */
	public List<Map<String, String>> getCommoditysByAdId(String commodityAdId){
	    List<Map<String, String>> typeList = commodityAdService.getCommoditysByAdId(commodityAdId);
	    return typeList;
	    
	}
}
