package com.xingrongjinfu.activity.conpusmanage.service;

import com.xingrongjinfu.activity.conpusmanage.dao.IACouponUserDao;
import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.model.ACouponUser;
import com.xingrongjinfu.utils.DateUtil;
import com.xingrongjinfu.utils.StringUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: fengqian
 * @Date: 2018/5/4 09:16
 * @Description:
 */
@Service
public class ACouponUserService implements IACouponUserService {

    @Autowired
    IACouponUserDao couponUserDao;










    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return couponUserDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public List<ACoupon> getCoupons() {
        return couponUserDao.getCoupons();
    }

    @Override
    public void addCouponUser(ACouponUser couponUser, String storeIds,String receiveType) {
        if(StringUtil.isEmpty(storeIds)){
            return;
        }

        for(String storeId:storeIds.split(",")){
            couponUser.setStoreId(storeId);
            Map<String, String> map = couponUserDao.getUser(storeId);
            if(map!=null){
                couponUser.setUserId(map.get("userId"));
                couponUser.setUserId(map.get("storeName"));

            }
            if("0".equals(receiveType)) {//立即发放
                couponUser.setGiveTime(DateUtil.toSimpleDateString());
                couponUser.setReviveTime(DateUtil.toSimpleDateString());
                couponUser.setStatus(2);//  1未领取 2领取未使用 3使用 4 过期
            }else{//定时发放
                couponUser.setStatus(1);//  1未领取 2领取未使用 3使用 4 过期
                couponUser.setReviveTime(couponUser.getGiveTime());
            }
//            couponUser.setExpireTime();
            couponUser.setUseStatus(0);//未使用
            couponUser.setExpireStatus(0);//未过期

            couponUserDao.addCouponUser(couponUser);
        }



    }

    //    @Override
//    public void addCoupon(ACoupon coupon,String commodityNos) {
//
//
//        coupon.setCreateTime(DateUtil.toSimpleDateString());
//        couponUserDao.addCoupon(coupon);
//        if(StringUtil.isEmpty(commodityNos)){
//            return ;
//        }
//        List<ACouponCommodity> list = getList(coupon.getId(), commodityNos);
//        couponUserDao.addCouponCommoditys(list);
//    }
//
//    @Override
//    public ACoupon findById(int id) {
//        return couponUserDao.findById(id);
//    }
//
//    @Override
//    public boolean updateCoupon(ACoupon coupon,String commodityNos) {
//        couponUserDao.updateCoupon(coupon);
//        couponUserDao.deletCouponCommoditys(coupon.getId());
//        if(StringUtil.isEmpty(commodityNos)){
//            return true;
//        }
//        List<ACouponCommodity> list = getList(coupon.getId(), commodityNos);
//        couponUserDao.addCouponCommoditys(list);
//        return true;
//    }
//
//    private List<ACouponCommodity> getList(Integer couponid, String commodityNos) {
//        List<ACouponCommodity> list = new ArrayList<>();
//        String[] Nos = commodityNos.split(",");
//        for (String no : Nos) {
//            ACouponCommodity aCouponCommodity = new ACouponCommodity();
//            aCouponCommodity.setCommodityNo(no);
//            aCouponCommodity.setCouponId(couponid);
//            list.add(aCouponCommodity);
//        }
//        return list;
//    }
//
//
//    @Override
//    public List<Product> getProductByCouponId(Integer id) {
//        return couponUserDao.getProductByCouponId(id);
//    }
}
