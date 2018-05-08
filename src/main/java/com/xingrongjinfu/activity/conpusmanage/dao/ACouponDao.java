package com.xingrongjinfu.activity.conpusmanage.dao;

import com.xingrongjinfu.activity.conpusmanage.model.ACoupon;
import com.xingrongjinfu.activity.conpusmanage.model.ACouponCommodity;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据层处理
 *
 * @author
 */
@Repository("aCouponDao")
public class ACouponDao extends DynamicObjectBaseDao implements IACouponDao {


    /**
     * 根据条件分页查询
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {

        List<TableDataInfo> cashierManagePageInfo = null;
        try {
            cashierManagePageInfo = (List<TableDataInfo>) this.findForList("activityConpusMapper.pageInfoQuery", pageUtilEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashierManagePageInfo;

    }

    @Override
    public boolean addCoupon(ACoupon coupon) {
        return this.save("activityConpusMapper.addCoupon", coupon) > 0 ? true : false;
    }

    @Override
    public ACoupon findById(int id) {
        return (ACoupon)this.findForObject("activityConpusMapper.findById",id);
    }

    @Override
    public boolean updateCoupon(ACoupon coupon) {
        return this.update("activityConpusMapper.updateById",coupon)>0;
    }

    @Override
    public void addCouponCommodity(ACouponCommodity aCouponCommodity) {
         this.save("activityConpusMapper.addCouponCommodity", aCouponCommodity);
    }
}