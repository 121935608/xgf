package com.xingrongjinfu.activity.conpusmanage.dao;


import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.model.ACouponCommodity;
import com.xingrongjinfu.system.product.model.Product;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:16
 * @Description:
 */

public interface IACouponDao {

    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    boolean addCoupon(ACoupon coupon);

    ACoupon findById(int id);

    boolean updateCoupon(ACoupon coupon);

    void addCouponCommoditys(List<ACouponCommodity> aCouponCommodity);

    List<Product> getProductByCouponId(Integer id);



    void deletCouponCommoditys(Integer id);
}
