package com.xingrongjinfu.activity.conpusmanage.controller;

import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.service.IACouponService;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.product.service.IProductService;
import com.xingrongjinfu.utils.DateUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:25
 * @Description:
 */
@Controller("aCoupon")
@RequestMapping("coupon")
public class ACouponController extends BaseController {

    @Autowired
    private IACouponService couponService;
    @Autowired
    private IProductService productService;

    @RequestMapping("list")
    @ResponseBody
    public ModelAndView list() {

        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> coupons = couponService.pageInfoQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), coupons);

    }

    @RequestMapping("view")
    public ModelAndView toView() throws Exception {
        ModelAndView modelAndView = new ModelAndView("activity/coupon-list");
        return modelAndView;
    }

    @RequestMapping("addUI")
    public ModelAndView addUI() throws Exception {
        ModelAndView modelAndView = new ModelAndView("activity/coupon-add");
        return modelAndView;
    }

    @RequestMapping("add")
    @ResponseBody
    public Message add(ACoupon coupon, HttpServletRequest request) {
        String commodityNos = request.getParameter("commodityNos");

        boolean b = couponService.addCoupon(coupon,commodityNos);
        return true ? new Message(1) : new Message(0);
    }

    @RequestMapping("editUI")
    public ModelAndView editUI(int id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("activity/coupon-edit");
        ACoupon acoupon = couponService.findById(id);
        modelAndView.addObject("acoupon",acoupon);
        return modelAndView;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Message edit(ACoupon coupon) throws Exception {
        boolean b = couponService.updateCoupon(coupon);
        return b ? new Message(1) : new Message(0);
    }


    @RequestMapping("getCommodityNos")
    @ResponseBody
    public List<String> getCommodityNos() throws Exception {
        return productService.getCommodityNos();
    }

    @RequestMapping("getCommodityByNo")
    @ResponseBody
    public Product getCommodityByNo(String commodityNo) throws Exception {
        return productService.findProductByNo(commodityNo);
    }



}
