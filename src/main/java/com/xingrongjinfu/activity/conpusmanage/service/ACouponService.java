package com.xingrongjinfu.activity.conpusmanage.service;

import com.xingrongjinfu.activity.conpusmanage.dao.IACouponDao;
import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
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
    public boolean addCoupon(ACoupon coupon) {
        return couponDao.addCoupon(coupon);
    }

    @Override
    public ACoupon findById(int id) {
        return couponDao.findById(id);
    }

    @Override
    public boolean updateCoupon(ACoupon coupon) {
        return couponDao.updateCoupon(coupon);
    }


}
