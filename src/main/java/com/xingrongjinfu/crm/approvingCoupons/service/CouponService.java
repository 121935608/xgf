package com.xingrongjinfu.crm.approvingCoupons.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.crm.approvingCoupons.dao.ICouponDao;
import com.xingrongjinfu.crm.approvingCoupons.model.Coupon;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理  业务层处理 
* @date 2018年4月25日
 */
@Service("couponService")
public class CouponService implements ICouponService
{

    @Autowired
    private ICouponDao couponDao;

    /**
     * 根据条件分页查询优惠券对象
     * 
     * @param page 分页对象
     * @return 优惠券列表
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return couponDao.pageInfoQuery(pageUtilEntity);
    }

    
    /**
     * 根据优惠券ID查询优惠券详细信息
     * 
     * @param: @param couponId
     * @return: Coupon      
     */
	public Coupon getCouponDetails(String couponId) {
		return couponDao.getCouponDetails(couponId);
	}

	/**
	 * @Description: 审核优惠券
	 * @param: coupons
	 * @return: int      
	 */
	public int updateCouponAudit(Coupon coupon) {
		return couponDao.updateCouponAudit(coupon);
	}

}
