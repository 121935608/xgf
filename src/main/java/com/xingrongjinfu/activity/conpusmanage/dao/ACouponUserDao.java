package com.xingrongjinfu.activity.conpusmanage.dao;

import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.model.ACouponCommodity;
import com.xingrongjinfu.system.product.model.Product;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据层处理
 *
 * @author
 */
@Repository
public class ACouponUserDao extends DynamicObjectBaseDao implements IACouponUserDao {


    /**
     * 根据条件分页查询
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {

        List<TableDataInfo> cashierManagePageInfo = null;
        try {
            cashierManagePageInfo = (List<TableDataInfo>) this.findForList("activityConpusUserMapper.pageInfoQuery", pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashierManagePageInfo;

    }

//    @Override
//    public boolean addCoupon(ACoupon coupon) {
//        return this.save("activityConpusMapper.addCoupon", coupon) > 0 ? true : false;
//    }
//
//    @Override
//    public ACoupon findById(int id) {
//        return (ACoupon)this.findForObject("activityConpusMapper.findById",id);
//    }
//
//    @Override
//    public boolean updateCoupon(ACoupon coupon) {
//        return this.update("activityConpusMapper.updateById",coupon)>0;
//    }
//
//
//
//    @Override
//    public List<Product> getProductByCouponId(Integer id) {
//        try {
//            return (List<Product>) this.findForList("activityConpusMapper.getProductByCouponId", id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//
//    }
//
//    @Override
//    public void addCouponCommoditys(List<ACouponCommodity> list) {
//        batchSave("activityConpusMapper.addCouponCommoditys",list);
//    }
//
//
//    @Override
//    public void deletCouponCommoditys(Integer couponid) {
//        delete("activityConpusMapper.deletCouponCommoditys",couponid);
//    }
}