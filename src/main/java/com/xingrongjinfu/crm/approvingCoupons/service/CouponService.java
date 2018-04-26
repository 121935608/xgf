package com.xingrongjinfu.crm.approvingCoupons.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.crm.approvingCoupons.dao.ICouponDao;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理  业务层处理 
* @date 2018年4月25日
 */
@Service("couponService")
public class CouponService implements ICouponService
{

    @Autowired
    private ICouponDao couponDao;

    /**
     * 根据条件分页查询优惠券对象
     * 
     * @param page 分页对象
     * @return 优惠券列表
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return couponDao.pageInfoQuery(pageUtilEntity);
    }

}
