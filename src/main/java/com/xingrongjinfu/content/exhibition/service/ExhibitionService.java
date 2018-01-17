package com.xingrongjinfu.content.exhibition.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.exhibition.common.ExhibitionConstant;
import com.xingrongjinfu.content.exhibition.dao.IExhibitionDao;
import com.xingrongjinfu.content.exhibition.model.Exhibition;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("exhibitionService")
public class ExhibitionService implements IExhibitionService
{

    @Autowired
    private IExhibitionDao exhibitionDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<Exhibition> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return exhibitionDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeExhibitionStatus(Exhibition exhibition)
    {
        return exhibitionDao.updateExhibitionInfo(exhibition);
    }

    /**
     * 添加
     * 
     */
	@Override
	public int addExhibitionInfo(Exhibition exhibition) {
		return exhibitionDao.addExhibitionInfo(exhibition);
	}

	/**
     * 修改信息
     * 
     */
	@Override
	public int updateExhibitionInfo(Exhibition exhibition) {
		return exhibitionDao.updateExhibitionInfo(exhibition);
	}

	/**
     * 通过ID查询
     */
	@Override
	public Exhibition findByExhibitionId(String exhibitionId) {
		return exhibitionDao.findByExhibitionId(exhibitionId);
	}

	@Override
	public int deleteById(Exhibition exhibition) {
		return exhibitionDao.deleteById(exhibition);
	}

	@Override
	public Exhibition findByExhibitionName(String exhibitionName) {
		return exhibitionDao.findByExhibitionName(exhibitionName);
	}

	@Override
	public String checkNameUnique(Exhibition exhibition) {
		if(exhibition.getExhibitionId()==null){
			exhibition.setExhibitionId("-1");
    	}
    	String categoryName = exhibition.getCategoryName();
    	String exhibitionId = exhibition.getExhibitionId();
    	exhibition = new Exhibition();
    	exhibition.setCategoryName(categoryName);
    	Exhibition newExhibition = exhibitionDao.findByExhibitionName(categoryName);
    	if(ObjectUtil.isNotNull(newExhibition)&&newExhibition.getExhibitionId()!=exhibitionId){
    		return ExhibitionConstant.NAME_NOT_UNIQUE;
    	}
    	
    	return ExhibitionConstant.NAME_UNIQUE;
	}
	
}
