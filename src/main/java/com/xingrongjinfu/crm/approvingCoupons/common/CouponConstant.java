package com.xingrongjinfu.crm.approvingCoupons.common;

/**
 * 
* @author chenmengzhen    
* @version V1.0  
* @Description:  优惠券管理   常量信息 
* @date 2018年4月25日
 */
public class CouponConstant
{
    /**
     * 请求地址：跳转至访问优惠券列表
     */
    public final static String COUPON_URL = "couponView";

    /**
     * 请求地址：优惠券列表查询
     */
    public final static String COUPON_LIST_URL = "couponList";
    
    /**
     * 请求地址：优惠券信息查询
     */
    public final static String COUPON_QUERY_URL = "toCouponQuery";
    
    /**
     * 请求地址：优惠券审核
     */
    public final static String COUPON_AUDIT = "couponAudit";
    
    
    /**
     * 请求地址：优惠券审核
     */
    public final static String UPDATE_COUPON_AUDIT = "updateCouponAudit";
    
    /**
     * 逻辑视图名：跳转至优惠券列表界面
     */
    public final static String COUPON_PAGE = "xfsxCRM/coupon-list";
    
    
    /**
     * 逻辑视图名：查询优惠券信息界面
     */
    public final static String COUPON_QUERY_PAGE = "xfsxCRM/coupon-query";
    
    /**
     * 逻辑视图名：优惠券审核界面
     */
    public final static String COUPON_AUDIT_PAGE = "xfsxCRM/coupon-audit";
    
}
