package com.xingrongjinfu.crm.approvingCoupons.controller;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.approvingCoupons.common.CouponConstant;
import com.xingrongjinfu.crm.approvingCoupons.model.Coupon;
import com.xingrongjinfu.crm.approvingCoupons.service.ICouponService;
import com.xingrongjinfu.system.merchant.common.MerchantConstant;
import com.xingrongjinfu.system.merchant.model.AccountInfo;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理   处理 
* @date 2018年4月25日
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL)
public class CouponController extends BaseController
{
	
	@Autowired
    private ICouponService couponService;
	
    /**
     * 跳转优惠券列表界面
     */
    @RequestMapping(CouponConstant.COUPON_URL)
    public ModelAndView loadCrmCoupon()
    {
        return this.getModelAndView(CouponConstant.COUPON_PAGE);
    }
    
    
    /**
     * 查询优惠券列表
     */
    @RequestMapping(CouponConstant.COUPON_LIST_URL)
    public ModelAndView couponList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = couponService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    
    
    /**
     * 跳转到查看优惠券界面
     * @param
     * @return
     */
    @RequestMapping(CouponConstant.COUPON_QUERY_URL)
    public ModelAndView couponQuery(String couponId)
    {
        ModelAndView modelAndView=this.getModelAndView(CouponConstant.COUPON_QUERY_PAGE);
        Coupon coupon= couponService.getCouponDetails(couponId);
        if (coupon !=null) {
            modelAndView.addObject("coupon", coupon);
        }
        return modelAndView;
    }
    
    /**
     * 跳转到优惠券审核界面
     * @param
     * @return
     */
    @RequestMapping(CouponConstant.COUPON_AUDIT)
    public ModelAndView couponAudit(String couponId)
    {
        ModelAndView modelAndView=this.getModelAndView(CouponConstant.COUPON_AUDIT_PAGE);
        Coupon coupon= couponService.getCouponDetails(couponId);
        if (coupon !=null) {
            modelAndView.addObject("coupon", coupon);
        }
        return modelAndView;
    }
    
    /**
     * 审核
     * @param
     * @return
     */
    @ActionControllerLog(title = "谢鲜CRM管理", action = "谢鲜CRM管理-审核优惠券", isSaveRequestData = true)
    @RequestMapping(CouponConstant.UPDATE_COUPON_AUDIT)
    public @ResponseBody Message updateCouponAudit(Coupon coupon)
    {
    	int result = 0;
    	if(coupon.getCouponId() != null){
    		 result = couponService.updateCouponAudit(coupon);
    	}

    	return new Message(result);
    }
}
