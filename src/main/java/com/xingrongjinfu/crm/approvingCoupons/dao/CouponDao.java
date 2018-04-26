package com.xingrongjinfu.crm.approvingCoupons.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券管理 数据层处理 
* @date 2018年4月25日
 */
@Repository("couponDao")
public class CouponDao extends DynamicObjectBaseDao implements ICouponDao
{
	 /**
     * 根据条件分页查询优惠券信息
     * 
     * @param page 分页对象
     * @return 优惠券列表
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
    	List<TableDataInfo> couponPageInfo = null;
        try
        {
        	couponPageInfo = (List<TableDataInfo>) this.findForList("CouponMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return couponPageInfo;
	}
}
