package com.xingrongjinfu.system.storeaffairs.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.system.storeaffairs.model.Repay;
import com.xingrongjinfu.system.storeaffairs.model.RepayDetail;

/**
 * 申请审核 数据层处理
 * 
 * @author cj
 */
@Repository("repaymentDao")
public class RepaymentDao extends DynamicObjectBaseDao implements IRepaymentDao
{

     

	/**
     * 根据条件分页查询还款计划表
     * 
     * @param page 分页对象
     * @return  
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> repaymentListQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("StoreAffairMapper.repaymentpageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;
    }

    @Override
    public Repay getByRepayId(String id) {
        
        return (Repay) this.findForObject("StoreAffairMapper.getByRepayId", id);
    }

    @Override
    public String getOrderNumber(String id) {

        return (String) this.findForObject("StoreAffairMapper.getOrderNumber", id);
    }

    @Override
    public int updateRepay(Repay repay) {
        
        return this.update("StoreAffairMapper.updateRepay", repay);
    }

    @Override
    public int addRepayDetail(RepayDetail repayDetail) {
       
        return this.save("StoreAffairMapper.addRepayDetail", repayDetail);
    }

    @Override
    public List<RepayDetail> getRepayDetail(String id) {
        List<RepayDetail> userPageInfo = null;
        try {
            userPageInfo =  (List<RepayDetail>) this.findForList("StoreAffairMapper.getRepayDetail", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userPageInfo;
    }
 
	 
}
