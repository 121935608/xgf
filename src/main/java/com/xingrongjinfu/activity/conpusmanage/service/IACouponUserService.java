package com.xingrongjinfu.activity.conpusmanage.service;

import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.system.product.model.Product;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:19
 * @Description:
 */
public interface IACouponUserService {


    List<TableDataInfo>   pageInfoQuery(PageUtilEntity pageUtilEntity);

//    void addCoupon(ACoupon coupon, String commodityNos);
//
//
//    ACoupon findById(int id);
//
//    boolean updateCoupon(ACoupon coupon, String commodityNos);
//
//    List<Product> getProductByCouponId(Integer id);
}
