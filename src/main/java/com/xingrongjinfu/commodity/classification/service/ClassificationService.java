package com.xingrongjinfu.commodity.classification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.classification.dao.IClassificationDao;
import com.xingrongjinfu.commodity.classification.model.Category;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("classificationService")
public class ClassificationService implements IClassificationService {
	@Autowired
	private IClassificationDao classificationDao;

	/**
     * 查询所有信息
     * 
     * @return 集合
     */
	public List<Category> queryCategorys() {

		return classificationDao.queryCategorys();
	}

	/**
	 * 通过ID查询
	 */
	public Category findByCategoryId(String categoryId) {
		return classificationDao.findByCategoryId(categoryId);
	}
	
	/**
	 * 新增信息
	 * 
	 */
	public int addCategoryInfo(Category category) {
		return classificationDao.addCategoryInfo(category);
	}

	/**
	 * 修改信息
	 * 
	 */
	public int updateCategoryInfo(Category category) {
		return classificationDao.updateCategoryInfo(category);
	}

	

	/**
	 * 修改状态
	 * 
	 */
	public int changeCategoryStatus(Category category) {
		return classificationDao.updateCategoryInfo(category);
	}

	@Override
	public List<Category> findCategoryByPid(String parentId) {
		return classificationDao.findCategoryByPid(parentId);
	}

	@Override
	public int deleteCategory(Category category) {
		return classificationDao.deleteCategory(category);
	}

    @Override
    public int queryCom(String id) {
        
        return classificationDao.queryCom(id);
    }

    @Override
    public int isParentId(String id) {
        return classificationDao.isParentId(id);
    }

}
