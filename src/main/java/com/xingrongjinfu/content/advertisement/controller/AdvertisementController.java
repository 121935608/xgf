package com.xingrongjinfu.content.advertisement.controller;

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
import com.xingrongjinfu.content.advertisement.service.IAdvertisementService;
import com.xingrongjinfu.content.carousel.common.CarouselConstant;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.exhibition.model.Exhibition;
import com.xingrongjinfu.system.role.common.RoleConstant;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(ContentConstant.CONTENT_URL)
public class AdvertisementController extends BaseController {

	@Autowired
	private IAdvertisementService advertisementService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(AdvertisementConstant.ADVER_URL)
	public ModelAndView loadAdvertisement() {
		ModelAndView modelAndView = this.getModelAndView(AdvertisementConstant.ADVER_PAGE);
		
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
	@RequestMapping(AdvertisementConstant.TO_ADD_URL)
	public ModelAndView toAdvertisementAdd(String advertisementId) {
		ModelAndView modelAndView = this.getModelAndView(AdvertisementConstant.ADD_PAGE);
		
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
	@RequestMapping(AdvertisementConstant.TO_MODIFY_URL)
	public ModelAndView toAdvertisementModify(@RequestParam(required = true) String advertisementId) {
		ModelAndView modelAndView = this.getModelAndView(AdvertisementConstant.MODIFY_PAGE);
		
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
		
		if (advertisementId != null) {
			modelAndView.addObject("advertisement",advertisementService.findByAdvertisementId(advertisementId));
			modelAndView.addObject("advertisementId",advertisementId);
			System.out.println("~~~~~~~~~"+modelAndView);
		}

		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(AdvertisementConstant.ADVER_LIST_URL)
	public ModelAndView advertisementList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Advertisement> tableDataInfo = advertisementService.pageInfoQuery(pageUtilEntity);
		Integer i=1;
		for (Advertisement advertisement : tableDataInfo) {
			advertisement.setAdvertisementNum(pageUtilEntity.getPage()+(i++));
			}

		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "广告通知", action = "广告通知-保存", isSaveRequestData = true)
	@RequestMapping(AdvertisementConstant.SAVE_ADVER_URL)
	public @ResponseBody Message saveAdvertisement(Advertisement advertisement,String advertisementId,MultipartFile picture) {
		int result = 0;		
		AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
		
		try {
			String key = aliyunOSSClientUtil.uploadImg(picture);
			if(key!=null){
				
				String originalFilename = picture.getOriginalFilename();
				String filePath = aliyunOSSClientUtil.FOLDER + originalFilename;
				advertisement.setAdvertisementImg(filePath);
				
			}
		} catch (OSSException e) {
			e.printStackTrace();
            return new Message(result);
		}
		if (advertisementId != null) {
			result = advertisementService.updateAdvertisementInfo(advertisement);
		} else {
			result = advertisementService.addAdvertisementInfo(advertisement);
		}
		
		return new Message(result);

	}
	
	/**
     * 根据ID删除
     *//*
    @ActionControllerLog(title = "内容管理", action = "内容管理-删除广告", isSaveRequestData = true)
    @RequestMapping(AdvertisementConstant.DEL_URL)
    public @ResponseBody Message deleteAdvertisementById(Advertisement advertisement)
    {
        int result = 0;
        String id = advertisement.getAdvertisementId();
        if (id != null)
        {           
                result = advertisementService.deleteById(advertisement);           
        }
        return new Message(result);
    }*/

	/**
	 * 启动/停用 操作
	 */
	@ActionControllerLog(title = "广告通知", action = "广告通知-启用/停用", isSaveRequestData = true)
	@RequestMapping(AdvertisementConstant.CHANGE_STATUS_URL)
	public @ResponseBody Message changeAdvertisementStatus(Advertisement advertisement) {
		int result = 0;
		String id = advertisement.getAdvertisementId();
		if (id != null) {
			result = advertisementService.changeAdvertisementStatus(advertisement);
		}
		return new Message(result);
	}
}
