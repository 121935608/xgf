package com.xingrongjinfu.commodity.classification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.classification.model.Category;

/**
 * 数据层处理
 * 
 * @author
 */
@Repository("classificationDao")
public class ClassificationDao extends DynamicObjectBaseDao implements IClassificationDao {

	public List<Category> queryCategorys() {
		List<Category> categoryList = null;
		try {
			categoryList = (List<Category>) this.findForList("CommodityClassificationMapper.queryCategorys", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	/**
	 * 通过ID查询
	 * 
	 */
	public Category findByCategoryId(@Param(value = "categoryId") String categoryId) {
		return (Category) this.findForObject("CommodityClassificationMapper.findByCategoryId", categoryId);
	}
	
	@Override
	public List<Category> findCategoryByPid(@Param(value = "parentId")String parentId) {
		List<Category> parentList = null;
        try
        {
            parentList = (List<Category>) this.findForList("CommodityClassificationMapper.findCategoryByPid", parentId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return parentList;
	}
	
	
	/**
	 * 新增信息
	 * 
	 */
	public int addCategoryInfo(Category category) {
		return (int) this.save("CommodityClassificationMapper.addCategoryInfo", category);
	}

	/**
	 * 修改信息
	 * 
	 */
	public int updateCategoryInfo(Category category) {
		return this.update("CommodityClassificationMapper.updateCategoryInfo", category);
	}

	@Override
	public int deleteCategory(Category category) {
		return (int) this.delete("CommodityClassificationMapper.deleteCategoryInfo", category);
	}

    @Override
    public int queryCom(String id) {
       
        return (int) this.findForObject("CommodityClassificationMapper.queryCom", id);
    }

	

}
