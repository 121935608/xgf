package com.xingrongjinfu.activity.conpusmanage.service;

import com.xingrongjinfu.activity.conpusmanage.dao.IACouponDao;
import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.model.ACouponCommodity;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.utils.DateUtil;
import com.xingrongjinfu.utils.StringUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void addCoupon(ACoupon coupon,String commodityNos) {


        coupon.setCreateTime(DateUtil.toSimpleDateString());
        couponDao.addCoupon(coupon);
        if(StringUtil.isEmpty(commodityNos)){
            return ;
        }
        List<ACouponCommodity> list = getList(coupon.getId(), commodityNos);
        couponDao.addCouponCommoditys(list);
    }

    @Override
    public ACoupon findById(int id) {
        return couponDao.findById(id);
    }

    @Override
    public boolean updateCoupon(ACoupon coupon,String commodityNos) {
        couponDao.updateCoupon(coupon);
        couponDao.deletCouponCommoditys(coupon.getId());
        if(StringUtil.isEmpty(commodityNos)){
            return true;
        }
        List<ACouponCommodity> list = getList(coupon.getId(), commodityNos);
        couponDao.addCouponCommoditys(list);
        return true;
    }

    private List<ACouponCommodity> getList(Integer couponid, String commodityNos) {
        List<ACouponCommodity> list = new ArrayList<>();
        String[] Nos = commodityNos.split(",");
        for (String no : Nos) {
            ACouponCommodity aCouponCommodity = new ACouponCommodity();
            aCouponCommodity.setCommodityNo(no);
            aCouponCommodity.setCouponId(couponid);
            list.add(aCouponCommodity);
        }
        return list;
    }


    @Override
    public List<Product> getProductByCouponId(Integer id) {
        return couponDao.getProductByCouponId(id);
    }
}
