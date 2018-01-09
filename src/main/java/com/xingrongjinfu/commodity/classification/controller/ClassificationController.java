package com.xingrongjinfu.commodity.classification.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.commodity.CommodityConstant;
import com.xingrongjinfu.commodity.classification.common.ClassificationConstant;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.classification.service.IClassificationService;
import com.xingrongjinfu.system.permission.common.PermissionConstant;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(CommodityConstant.COMMODITY_URL)
public class ClassificationController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClassificationController.class);

	@Autowired
	private IClassificationService classificationService;

	/**
	 * 跳转列表界面...
	 * 
	 */
	@RequestMapping(ClassificationConstant.CLASSIFICATION_URL)
	public ModelAndView loadCommodityClassification() {
		ModelAndView modelAndView = this.getModelAndView(ClassificationConstant.CLASSIFICATION_PAGE);

		// 1 启用 -1禁用
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
		modelAndView.addObject("parents", getParentList());
		return modelAndView;
	}

	/**
	 * 查询列表...
	 */
	@RequestMapping(ClassificationConstant.CLASSIFICATION_LIST_URL)
	public @ResponseBody List<Map<String, Object>> ClassificationList() {
		List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();
		List<Category> categoryList = classificationService.queryCategorys();

		for (Category category : categoryList) {
			Map<String, Object> categoryMap = new HashMap<String, Object>();
			categoryMap.put("id", category.getCategoryId());
			categoryMap.put("pId", category.getParentId());
			categoryMap.put("isParent", category.getParentId().equals("0") ? true : false);
			categoryMap.put("name", category.getCategoryName());
			categoryMap.put("open", false);
			resMapTrees.add(categoryMap);
		}

		return resMapTrees;
	}

	/**
     * 获取菜单详细信息...
     * 
     */
	@RequestMapping(ClassificationConstant.VIEW_URL)
	public @ResponseBody Category getMenu(String categoryId) {
		logger.info("get getMenu:{}", categoryId);
		Category category = classificationService.findByCategoryId(categoryId);
		return category;
	}
	
	/**
     * 删除
     */
	@ActionControllerLog(title = "商品管理", action = "分类管理-删除菜单", isSaveRequestData = true)
    @RequestMapping(ClassificationConstant.DEL_URL)
    public @ResponseBody Message delMenu(Category category)
    {
        int result = 0;
        String categoryId = category.getCategoryId();
        String parentId = category.getParentId();
        if (ObjectUtil.isNotNull(categoryId) || ObjectUtil.isNotNull(parentId))
        {
            result = classificationService.deleteCategory(category);
        }
        if (ObjectUtil.isNull(parentId))
        {
            category.setParentId(categoryId);
            category.setCategoryId(null);
            classificationService.deleteCategory(category);
        }
        return new Message(result);
    }

	
	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "商品管理", action = "商品管理-保存分类", isSaveRequestData = true)
	@RequestMapping(ClassificationConstant.SAVE_URL)
	public @ResponseBody Message saveCategory(Category category, MultipartFile picture) {
		int result = 0;
		
		AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
		try {
			String key = aliyunOSSClientUtil.uploadImg(picture);
			if (key != null) {

				String originalFilename = picture.getOriginalFilename();
				String filePath = aliyunOSSClientUtil.FOLDER + originalFilename;
				category.setImg(filePath);

			}
		} catch (OSSException e) {
			e.printStackTrace();
			return new Message(result);
		}

		String categoryId = category.getCategoryId();
		if (categoryId != null) {
			result = classificationService.updateCategoryInfo(category);
		} else {
			result = classificationService.addCategoryInfo(category);
		}

		return new Message(result);

	}

	/**
	 * 获取上级菜单对象key
	 */
	public List<SysCode> getParentList() {
		List<Category> categoryList = (List<Category>) classificationService.findCategoryByPid("");
		List<SysCode> sysCodeList = new ArrayList<SysCode>();
		for (Category category : categoryList) {
			SysCode sysCode = new SysCode();
			sysCode.setCodeid(category.getCategoryId().toString());
			sysCode.setCodevalue(category.getCategoryName());
			sysCodeList.add(sysCode);
		}
		return sysCodeList;
	}
	
	/**
	 * 启动/停用 操作
	 */
	@ActionControllerLog(title = "商品管理", action = "商品管理-启用/停用", isSaveRequestData = true)
	@RequestMapping(ClassificationConstant.CHANGE_STATUS_URL)
	public @ResponseBody Message changeClassificationStatus(Category category) {
		int result = 0;
		String id = category.getCategoryId();
		if (id != null) {
			result = classificationService.changeCategoryStatus(category);
		}
		return new Message(result);
	}
}
