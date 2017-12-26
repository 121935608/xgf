package com.xingrongjinfu.system.operlog.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.operlog.dao.IOperLogDao;
import com.xingrongjinfu.system.operlog.model.OperLog;

/**
 * 操作日志 服务层处理
 * 
 * @author y
 */
@Service("operLogService")
public class OperLogService implements IOperLogService
{

    @Autowired
    private IOperLogDao operLogDao;

    /**
     * 根据条件分页查询日志对象
     * 
     * @param page 分页对象
     * @return 系统日志对象集合
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return operLogDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    public void addOperlog(OperLog operLog)
    {
        operLogDao.addOperlog(operLog);
    }

}
