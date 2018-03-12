package com.xingrongjinfu.system.storeaffairs.service;
 
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.system.storeaffairs.dao.IRepaymentDao;
import com.xingrongjinfu.system.storeaffairs.model.Repay;
import com.xingrongjinfu.system.storeaffairs.model.RepayDetail; 

/**
 * 角色管理 业务层处理
 * 
 * @author y
 */
@Service("repaymentService")
public class RepaymentService implements IRepaymentService
{

    @Autowired
    private IRepaymentDao repaymentDao;

     
    
	/**
     * 根据条件分页查询还款计划表
     * 
     * @param page 分页对象
     * @return  
     */
    public List<TableDataInfo> repaymentListQuery(PageUtilEntity pageUtilEntity)
    {
        return repaymentDao.repaymentListQuery(pageUtilEntity);
    }



    @Override
    public Repay getByRepayId(String id) {
        
        return repaymentDao.getByRepayId(id);
    }
    @Override
    public String getOrderNumber(String id) {

        return repaymentDao.getOrderNumber(id);
    }

    @Override
    public int updateRepay(Repay repay,RepayDetail repayDetail) {
        int n = repaymentDao.updateRepay(repay);
        repaymentDao.addRepayDetail(repayDetail);
        return n;
    }



    @Override
    public List<RepayDetail> getRepayDetail(String id) {
        
        return repaymentDao.getRepayDetail(id);
    }
	 
}
