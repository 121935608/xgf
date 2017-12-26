package com.xingrongjinfu.system.operlog.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.system.operlog.model.OperLog;

/**
 * 操作日志 数据层
 * 
 * @author y
 */
public interface IOperLogDao
{

    /**
     * 根据条件分页查询日志对象
     * 
     * @param page 分页对象
     * @return 系统日志对象集合
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    public void addOperlog(OperLog operLog);
}
