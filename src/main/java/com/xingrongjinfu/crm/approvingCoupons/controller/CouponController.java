package com.xingrongjinfu.crm.approvingCoupons.controller;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.approvingCoupons.common.CouponConstant;
import com.xingrongjinfu.crm.approvingCoupons.service.ICouponService;

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
}
