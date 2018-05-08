package com.xingrongjinfu.activity.conpusmanage.service;

import com.xingrongjinfu.activity.conpusmanage.dao.IACouponDao;
import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.model.ACouponCommodity;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.utils.DateUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:16
 * @Description:
 */
@Service
public class ACouponService implements IACouponService {

    @Autowired
    IACouponDao couponDao;





    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return couponDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public boolean addCoupon(ACoupon coupon,String commodityNos) {


        coupon.setCreateTime(DateUtil.toSimpleDateString());
        couponDao.addCoupon(coupon);
        String[] Nos = commodityNos.split(",");
        for(String no:Nos){
            ACouponCommodity aCouponCommodity = new ACouponCommodity();
            aCouponCommodity.setCommodityNo(no);
            aCouponCommodity.setCouponId(coupon.getId());
            couponDao.addCouponCommodity(aCouponCommodity);
        }



        return true;
    }

    @Override
    public ACoupon findById(int id) {
        return couponDao.findById(id);
    }

    @Override
    public boolean updateCoupon(ACoupon coupon) {
        return couponDao.updateCoupon(coupon);
    }

    @Override
    public List<Product> getProductByCouponId(Integer id) {
        return couponDao.getProductByCouponId(id);
    }
}
