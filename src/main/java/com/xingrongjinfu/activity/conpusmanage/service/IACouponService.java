package com.xingrongjinfu.activity.conpusmanage.service;

import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:19
 * @Description:
 */
public interface IACouponService {


    List<TableDataInfo>   pageInfoQuery(PageUtilEntity pageUtilEntity);

    boolean addCoupon(ACoupon coupon,String commodityNos);


    ACoupon findById(int id);

    boolean updateCoupon(ACoupon coupon);
}
