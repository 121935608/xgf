package com.xingrongjinfu.commodity.label.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xingrongjinfu.commodity.CommodityConstant;
import com.xingrongjinfu.commodity.label.common.LabelConstant;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.commodity.label.service.ILabelService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(CommodityConstant.COMMODITY_URL)
public class LabelController extends BaseController {

	@Autowired
	private ILabelService labelService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(LabelConstant.LABEL_URL)
	public ModelAndView loadCommodityLabel() {
		ModelAndView modelAndView = this.getModelAndView(LabelConstant.LABEL_PAGE);
		
		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(LabelConstant.LABEL_LIST_URL)
	public ModelAndView LabelList() {
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		
		List<Label> tableDataInfo = labelService.pageInfoQuery(pageUtilEntity);
		Integer i=1;
		for (Label label : tableDataInfo) {
		label.setLaberNum(pageUtilEntity.getPage()+(i++));
		}

		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 跳转新增界面
	 */
	@RequestMapping(LabelConstant.TO_ADD_URL)
	public ModelAndView toLabelAdd(String categoryId) {
		ModelAndView modelAndView = this.getModelAndView(LabelConstant.ADD_PAGE);
		
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
	 * 保存信息
	 */
	@ActionControllerLog(title = "商品管理", action = "商品管理-保存标签", isSaveRequestData = true)
	@RequestMapping(LabelConstant.SAVE_URL)
	public @ResponseBody Message saveCategory(Label category, String categoryId,MultipartFile picture) {
		int result = 0;		
		
		try {
		    if(picture.getSize() !=0){
    		    AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
    			String key = aliyunOSSClientUtil.uploadImg(picture);
    			if(key!=null){
    				
    				String originalFilename = picture.getOriginalFilename();
    				String filePath = aliyunOSSClientUtil.FOLDER + aliyunOSSClientUtil.filePath;
    				category.setImg(filePath);
    				
    			}
		    }
		} catch (OSSException e) {
			e.printStackTrace();
            return new Message(result);
		}
		if (categoryId != null) {
			result = labelService.updateCategoryInfo(category);
		} else {
			result = labelService.addCategoryInfo(category);
		}
		
		return new Message(result);

	}

	/**
	 * 跳转修改界面
	 */
	@RequestMapping(LabelConstant.TO_MODIFY_URL)
	public ModelAndView toLabelModify(@RequestParam(required = true) String categoryId) {
		ModelAndView modelAndView = this.getModelAndView(LabelConstant.MODIFY_PAGE);
		
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
		
		if (categoryId != null) {
			Label category = labelService.findByCategoryId(categoryId);
			modelAndView.addObject("category",category);
		}

		return modelAndView;
	}
	
	/**
     * 根据ID删除
     */
    @ActionControllerLog(title = "商品管理", action = "商品管理-删除标签", isSaveRequestData = true)
    @RequestMapping(LabelConstant.DEL_URL)
    public @ResponseBody Message deleteLabelById(Label category)
    {
        int result = 0;
        String id = category.getCategoryId();
        if (id != null)
        {           
                result = labelService.deleteById(category);           
        }
        return new Message(result);
    }
    
    /**
     * 校验名称
     */
    @RequestMapping(LabelConstant.CHECK_NAME_UNIQUE_URL)
    public @ResponseBody
    String checkNamesUnique(Label category) {
        String uniqueFlag = "0";
        if (category != null) {
            uniqueFlag = labelService.checkNameUnique(category);
        }
        return uniqueFlag;
    }
    /**
     * 校验名称
     */
    @RequestMapping(LabelConstant.ISEXIST_BY_NAME_URL)
    public @ResponseBody String  isExistByName(Label label) {
		String uniqueFlag="0";
		if (label !=null)
		{
			uniqueFlag =labelService.isExistByName(label);
		}
		return uniqueFlag;
    }
    
	/**
     * 启动/停用 操作
     */
    @ActionControllerLog(title = "商品管理", action = "商品管理-启用/停用", isSaveRequestData = true)
    @RequestMapping(LabelConstant.CHANGE_STATUS_URL)
    public @ResponseBody Message changeLabelStatus(Label category)
    {
        int result = 0;
        String id = category.getCategoryId();
        if (id != null)
        {
            result = labelService.changeCategoryStatus(category);
        }
        return new Message(result);
    }

}
