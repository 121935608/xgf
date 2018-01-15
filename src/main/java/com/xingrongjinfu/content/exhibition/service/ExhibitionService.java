package com.xingrongjinfu.content.exhibition.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.cashierManage.dao.ICashierManageDao;
import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.dao.ICarouselDao;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.exhibition.dao.IExhibitionDao;
import com.xingrongjinfu.content.exhibition.model.Exhibition;

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
	
}
