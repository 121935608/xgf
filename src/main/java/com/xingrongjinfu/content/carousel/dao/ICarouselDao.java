package com.xingrongjinfu.content.carousel.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.model.Carousel;


/**
 * 数据层
 * 
 * @author 
 */
public interface ICarouselDao
{
   
    /**
     * 根据条件分页查询
     * 
     */
    public List<Carousel> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 通过名字查询
     * 
     */
    public Carousel findByCarouselName(String carouselName);
    
    /**
     * 修改
     * 
     */
    public int updateCarouselInfo(Carousel carousel);

    /**
     * 添加
     * 
     */
	public int addCarouselInfo(Carousel carousel);

	/**
     * 通过ID查询
     * 
     */
	public Carousel findByCarouselId(String carouselId);

	public int deleteById(Carousel carousel);

	

}
