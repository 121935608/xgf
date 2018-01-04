package com.xingrongjinfu.system.storeaffairs.service;
 
import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
import com.xingrongjinfu.system.storeaffairs.dao.IRepaymentDao; 

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
  
	 
}
