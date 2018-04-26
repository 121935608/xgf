package com.xingrongjinfu.crm.approvingCoupons.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.crm.approvingCoupons.model.Coupon;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理 数据层处理 
* @date 2018年4月25日
 */
@Repository("couponDao")
public class CouponDao extends DynamicObjectBaseDao implements ICouponDao
{
	 /**
     * 根据条件分页查询优惠券信息
     * 
     * @param page 分页对象
     * @return 优惠券列表
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
    	List<TableDataInfo> couponPageInfo = null;
        try
        {
        	couponPageInfo = (List<TableDataInfo>) this.findForList("CouponMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return couponPageInfo;
	}

    /**
     * 根据优惠券ID查询优惠券详细信息
     * 
     * @param: @param couponId
     * @return: Coupon      
     */
	public Coupon getCouponDetails(String couponId) {
		Coupon coupon = null;
        try
        {
        	coupon=(Coupon)this.findForObject("CouponMapper.couponQuery",couponId);
        }
        catch (Exception e) 
        {
			e.printStackTrace();
		}
		return coupon;
	}

	/**
	 * @Description: 审核优惠券
	 * @param: coupons
	 * @return: int      
	 */
	public int updateCouponAudit(Coupon coupon) {
		return this.update("CouponMapper.updateCouponAudit", coupon);
	}
}
