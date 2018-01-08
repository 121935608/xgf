package com.xingrongjinfu.commodity.label.controller;

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

import com.xingrongjinfu.commodity.CommodityConstant;
import com.xingrongjinfu.commodity.classification.common.ClassificationConstant;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.label.common.LabelConstant;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.commodity.label.service.ILabelService;
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
		return this.getModelAndView(LabelConstant.LABEL_PAGE);
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(LabelConstant.LABEL_LIST_URL)
	public ModelAndView LabelList() {
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		List<TableDataInfo> tableDataInfo = labelService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 跳转新增界面
	 */
	@RequestMapping(LabelConstant.TO_ADD_URL)
	public ModelAndView toLabelAdd(String categoryId) {
		ModelAndView modelAndView = this.getModelAndView(LabelConstant.ADD_PAGE);
		return modelAndView;
	}

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "商品管理", action = "商品管理-保存标签", isSaveRequestData = true)
	@RequestMapping(LabelConstant.SAVE_URL)
	public @ResponseBody Message saveCategory(Label category, String categoryId,MultipartFile picture) {
		int result = 0;
		
		AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
		String key = aliyunOSSClientUtil.uploadImg(picture);
		String originalFilename = picture.getOriginalFilename();
		String filePath = aliyunOSSClientUtil.FOLDER + originalFilename;
		category.setImg(filePath);
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
		if (categoryId != null) {
			modelAndView.addObject("category", this.labelService.findByCategoryId(categoryId));
		}

		return modelAndView;
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
