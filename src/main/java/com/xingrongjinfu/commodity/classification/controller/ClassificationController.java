package com.xingrongjinfu.commodity.classification.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.commodity.CommodityConstant;
import com.xingrongjinfu.commodity.classification.common.ClassificationConstant;
import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.commodity.classification.service.IClassificationService;
import com.xingrongjinfu.system.user.common.UserConstant;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.UuidUtil;

/**
 *  业务处理
 * 
 * @author 
 */
@Controller
@RequestMapping(CommodityConstant.COMMODITY_URL)
public class ClassificationController extends BaseController
{

    @Autowired
    private IClassificationService classificationService;

    /**
     * 跳转列表界面
     */
    @RequestMapping(ClassificationConstant.CLASSIFICATION_URL)
    public ModelAndView loadCommodityClassification()
    {
        return this.getModelAndView(ClassificationConstant.CLASSIFICATION_PAGE);
    }

    
    /**
     * 查询列表
     */
    @RequestMapping(ClassificationConstant.CLASSIFICATION_LIST_URL)
    public ModelAndView ClassificationList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = classificationService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 跳转新增界面
     */
    @RequestMapping(ClassificationConstant.TO_ADD_URL)
    public ModelAndView toClassificationAdd(String categoryId)
    {
    	ModelAndView modelAndView = this.getModelAndView(ClassificationConstant.ADD_PAGE);
        return modelAndView;
    }

    /**
     * 保存信息
     */
    @ActionControllerLog(title = "商品管理", action = "商品管理-保存分类", isSaveRequestData = true)
    @RequestMapping(ClassificationConstant.SAVE_URL)
    public @ResponseBody Message saveCategory(Category category, String categoryId)
    {
    	int result = 0;
    	if(categoryId!=null){
    		result = classificationService.updateCategoryInfo(category);
    	}else{
    		result = classificationService.addCategoryInfo(category);
    	}
        
       
        return new Message(result);
        
    }
    

    /**
     * 跳转修改界面
     */
    @RequestMapping(ClassificationConstant.TO_MODIFY_URL)
    public ModelAndView toClassificationModify(@RequestParam(required = true) String categoryId)
    {
        ModelAndView modelAndView = this.getModelAndView(ClassificationConstant.MODIFY_PAGE);
        if (categoryId != null)
        {
            modelAndView.addObject("category", this.classificationService.findByCategoryId(categoryId));
        }
        
        return modelAndView;
    }
    
    /**
     * 启动/停用 操作
     */
    @ActionControllerLog(title = "商品管理", action = "商品管理-启用/停用", isSaveRequestData = true)
    @RequestMapping(ClassificationConstant.CHANGE_STATUS_URL)
    public @ResponseBody Message changeClassificationStatus(Category category)
    {
        int result = 0;
        String id = category.getCategoryId();
        if (id != null)
        {
            result = classificationService.changeCategoryStatus(category);
        }
        return new Message(result);
    }

}
