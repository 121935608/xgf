package com.xingrongjinfu.crm.approvingCoupons.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.crm.approvingCoupons.model.Coupon;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理  数据层
* @date 2018年4月25日
 */
public interface ICouponDao
{
	/**
     * 根据条件分页查询优惠券列表
     * 
     * @param page 分页对象
     * @return 优惠券列表
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 根据优惠券ID查询优惠券详细信息
     * 
     * @param: @param couponId
     * @return: Coupon      
     */
	public Coupon getCouponDetails(String couponId);

	/**
	 * @Description: 审核优惠券
	 * @param: coupons
	 * @return: int      
	 */
	public int updateCouponAudit(Coupon coupon);
}
