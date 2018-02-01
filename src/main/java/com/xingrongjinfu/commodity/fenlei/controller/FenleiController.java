package com.xingrongjinfu.commodity.fenlei.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.commodity.classification.controller.ClassificationController;
import com.xingrongjinfu.commodity.fenlei.common.FenleiConstant;
import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
import com.xingrongjinfu.commodity.fenlei.service.IFenleiService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.ObjectUtil;

@Controller
@RequestMapping(FenleiConstant.FENLEI_URL)
public class FenleiController extends BaseController{


    private static final Logger logger = LoggerFactory.getLogger(ClassificationController.class);

    @Autowired
    private IFenleiService fenleiService;

    /**
     * 跳转列表界面...
     * @throws Exception 
     * 
     */
    @RequestMapping(FenleiConstant.TO_FENLEI_URL)
    public ModelAndView loadCommodityClassification() throws Exception {
        ModelAndView modelAndView = this.getModelAndView(FenleiConstant.FENLEI_LIST_PAGE);
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        modelAndView.addObject("parents", getParentList(storeId));
        return modelAndView;
    }

    /**
     * 查询列表...
     * @throws Exception 
     */
    @RequestMapping(FenleiConstant.FENLEI_LIST_URL)
    public @ResponseBody List<Map<String, Object>> ClassificationList() throws Exception {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();
        List<Fenlei> categoryList = fenleiService.queryCategorys(storeId);

        for (Fenlei category : categoryList) {
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
    @RequestMapping(FenleiConstant.VIEW_URL)
    public @ResponseBody Fenlei getMenu(String categoryId) {
        logger.info("get getMenu:{}", categoryId);
        Fenlei category = fenleiService.findByCategoryId(categoryId);
        return category;
    }
    
    /**
     * 删除
     */
    @RequestMapping(FenleiConstant.DEL_URL)
    public @ResponseBody Message delMenu(Fenlei category)
    {
        int result = 0;
        String categoryId = category.getCategoryId();
        String parentId = category.getParentId();
        if (ObjectUtil.isNotNull(categoryId) || ObjectUtil.isNotNull(parentId))
        {
            result = fenleiService.deleteCategory(category);
        }
        if (ObjectUtil.isNull(parentId))
        {
            category.setParentId(categoryId);
            category.setCategoryId(null);
            fenleiService.deleteCategory(category);
        }
        return new Message(result);
    }

    
    /**
     * 保存信息
     */
    @RequestMapping(FenleiConstant.SAVE_URL)
    public @ResponseBody Message saveCategory(Fenlei category, MultipartFile picture) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        int result = 0;
        
        try {
            if(picture.getSize() !=0){
                AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
                String key = aliyunOSSClientUtil.uploadImg(picture);
                if (key != null) {
                    
                    String originalFilename = picture.getOriginalFilename();
                    String filePath = aliyunOSSClientUtil.FOLDER + aliyunOSSClientUtil.filePath;
                    category.setImg(filePath);
                    
                }
            }
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(result);
        }

        String categoryId = category.getCategoryId();
        if (categoryId != null&&categoryId.length()!=0) {
            result = fenleiService.updateCategoryInfo(category);
        } 
        else{
            category.setStoreId(storeId);
            category.setStatus(1);
            result = fenleiService.addCategoryInfo(category);
        }

        return new Message(result);

    }

    /**
     * 获取上级菜单对象key
     * @throws Exception 
     */
    public List<SysCode> getParentList(String storeId) throws Exception {
        List<Fenlei> categoryList = (List<Fenlei>) fenleiService.findCategoryByPid(storeId);
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Fenlei category : categoryList) {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(category.getCategoryId().toString());
            sysCode.setCodevalue(category.getCategoryName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
}
