package com.xingrongjinfu.content.advertisement.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.model.Carousel;

/**
 *  业务层
 * 
 * @author 
 */
public interface IAdvertisementService
{

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<Advertisement> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 修改状态
     * 
     */
    public int changeAdvertisementStatus(Advertisement advertisement);
    

    /**
     * 添加
     * 
     */
	public int addAdvertisementInfo(Advertisement advertisement);

	/**
     * 修改信息
     * 
     */
	public int updateAdvertisementInfo(Advertisement advertisement);

	/**
     * 通过ID查询
     * 
     */
	public Advertisement findByAdvertisementId(String advertisementId);

	/**
     * 删除信息
     * 
     */
	public int deleteById(Advertisement advertisement);

}
