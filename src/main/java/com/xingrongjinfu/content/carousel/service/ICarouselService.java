package com.xingrongjinfu.content.carousel.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;

/**
 *  业务层
 * 
 * @author 
 */
public interface ICarouselService
{

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<Carousel> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 修改状态
     * 
     */
    public int changeCarouselStatus(Carousel carousel);
    

    /**
     * 添加
     * 
     */
	public int addCarouselInfo(Carousel carousel);

	/**
     * 修改信息
     * 
     */
	public int updateCarouselInfo(Carousel carousel);

	/**
     * 通过ID查询
     * 
     */
	public Carousel findByCarouselId(String carouselId);

	public int deleteById(Carousel carousel);

}
