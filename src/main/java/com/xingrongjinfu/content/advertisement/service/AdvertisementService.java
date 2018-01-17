package com.xingrongjinfu.content.advertisement.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.content.advertisement.common.AdvertisementConstant;
import com.xingrongjinfu.content.advertisement.dao.IAdvertisementDao;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.utils.ObjectUtil;

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
	
	/**
     * 校验名称是否唯一
     */
    @Override
	public String checkNameUnique(Advertisement advertisement) {
		
    	if(advertisement.getAdvertisementId()==null){
    		advertisement.setAdvertisementId("-1");
    	}
    	String advertisementName = advertisement.getAdvertisementName();
    	String advertisementId = advertisement.getAdvertisementId();
    	advertisement = new Advertisement();
    	advertisement.setAdvertisementName(advertisementName);
    	Advertisement newAdvertisement = advertisementDao.findByAdvertisementName(advertisementName);
    	if(ObjectUtil.isNotNull(newAdvertisement)&&newAdvertisement.getAdvertisementId()!=advertisementId){
    		return AdvertisementConstant.NAME_NOT_UNIQUE;
    	}
    	
    	return AdvertisementConstant.NAME_UNIQUE;
	}

	/**
     * 删除信息
     * 
     */
	@Override
	public int deleteById(Advertisement advertisement) {
		return advertisementDao.deleteById(advertisement);
	}

	@Override
	public Advertisement findByAdvertisementName(String advertisementName) {
		return advertisementDao.findByAdvertisementName(advertisementName);
	}
	
}
