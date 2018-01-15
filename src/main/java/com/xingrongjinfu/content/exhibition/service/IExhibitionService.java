package com.xingrongjinfu.content.exhibition.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.exhibition.model.Exhibition;

/**
 *  业务层
 * 
 * @author 
 */
public interface IExhibitionService
{

    /**
     * 根据条件分页查询对象
     * 
     */
    public List<Exhibition> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 修改状态
     * 
     */
    public int changeExhibitionStatus(Exhibition exhibition);
    

    /**
     * 添加
     * 
     */
	public int addExhibitionInfo(Exhibition exhibition);

	/**
     * 修改信息
     * 
     */
	public int updateExhibitionInfo(Exhibition exhibition);

	/**
     * 通过ID查询
     * 
     */
	public Exhibition findByExhibitionId(String exhibitionId);

}
