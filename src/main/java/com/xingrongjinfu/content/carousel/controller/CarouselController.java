package com.xingrongjinfu.content.carousel.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

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
import com.xingrongjinfu.content.ContentConstant;
import com.xingrongjinfu.content.advertisement.common.AdvertisementConstant;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.common.CarouselConstant;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.carousel.service.ICarouselService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(ContentConstant.CONTENT_URL)
public class CarouselController extends BaseController {

	@Autowired
	private ICarouselService carouselService;

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
		
		if (carouselId != null) {
			modelAndView.addObject("carousel", this.carouselService.findByCarouselId(carouselId));
			modelAndView.addObject("carouselId",carouselId);
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
		List<TableDataInfo> tableDataInfo = carouselService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "轮播管理", action = "轮播管理-保存", isSaveRequestData = true)
	@RequestMapping(CarouselConstant.SAVE_CAROUSEL_URL)
	public @ResponseBody Message saveCarousel(Carousel carousel,String carouselId,MultipartFile picture) {
		int result = 0;		
		AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
		
		try {
			String key = aliyunOSSClientUtil.uploadImg(picture);
			if(key!=null){
				
				String originalFilename = picture.getOriginalFilename();
				String filePath = aliyunOSSClientUtil.FOLDER + originalFilename;
				carousel.setCarouselImg(filePath);
				
			}
		} catch (OSSException e) {
			e.printStackTrace();
            return new Message(result);
		}
		if (carouselId != null) {
			result = carouselService.updateCarouselInfo(carousel);
		} else {
			result = carouselService.addCarouselInfo(carousel);
		}
		
		return new Message(result);

	}
	
	/**
     * 根据ID删除
     *//*
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
*/
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
