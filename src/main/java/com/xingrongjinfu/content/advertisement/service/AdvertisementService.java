package com.xingrongjinfu.content.advertisement.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.cashierManage.dao.ICashierManageDao;
import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.dao.IAdvertisementDao;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.dao.ICarouselDao;
import com.xingrongjinfu.content.carousel.model.Carousel;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("advertisementService")
public class AdvertisementService implements IAdvertisementService
{

    @Autowired
    private IAdvertisementDao advertisementDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<Advertisement> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return advertisementDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeAdvertisementStatus(Advertisement advertisement)
    {
        return advertisementDao.updateAdvertisementInfo(advertisement);
    }

    /**
     * 添加
     * 
     */
	@Override
	public int addAdvertisementInfo(Advertisement advertisement) {
		return advertisementDao.addAdvertisementInfo(advertisement);
	}

	/**
     * 修改信息
     * 
     */
	@Override
	public int updateAdvertisementInfo(Advertisement advertisement) {
		return advertisementDao.updateAdvertisementInfo(advertisement);
	}

	/**
     * 通过ID查询
     */
	@Override
	public Advertisement findByAdvertisementId(String advertisementId) {
		return advertisementDao.findByAdvertisementId(advertisementId);
	}
	
}
