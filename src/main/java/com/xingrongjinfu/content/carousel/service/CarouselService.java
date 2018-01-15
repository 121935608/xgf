package com.xingrongjinfu.content.carousel.service;

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

/**
 * 业务层处理
 * 
 * @author
 */
@Service("carouselService")
public class CarouselService implements ICarouselService
{

    @Autowired
    private ICarouselDao carouselDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return carouselDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeCarouselStatus(Carousel carousel)
    {
        return carouselDao.updateCarouselInfo(carousel);
    }

    /**
     * 添加
     * 
     */
	@Override
	public int addCarouselInfo(Carousel carousel) {
		return carouselDao.addCarouselInfo(carousel);
	}

	/**
     * 修改信息
     * 
     */
	@Override
	public int updateCarouselInfo(Carousel carousel) {
		return carouselDao.updateCarouselInfo(carousel);
	}

	/**
     * 通过ID查询
     */
	@Override
	public Carousel findByCarouselId(String carouselId) {
		return carouselDao.findByCarouselId(carouselId);
	}

	@Override
	public int deleteById(Carousel carousel) {
		return carouselDao.deleteById(carousel);
	}
	
}
