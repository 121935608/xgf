package com.xingrongjinfu.crm.approvingCoupons.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理   业务层
* @date 2018年4月25日
 */
public interface ICouponService
{
    /**
     * 根据条件分页查询优惠券列表
     * 
     * @param page 分页对象
     * @return 优惠券列表
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

}
