package com.xingrongjinfu.system.operlog.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.operlog.model.OperLog;

/**
 * 操作日志 数据层处理
 * 
 * @author y
 */
@Repository("operLogDao")
public class OperLogDao extends DynamicObjectBaseDao implements IOperLogDao
{

    /**
     * 查询操作日志列表
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> operLogPageInfo = null;
        try
        {
            operLogPageInfo = (List<TableDataInfo>) this.findForList("OperLogMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return operLogPageInfo;
    }

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    public void addOperlog(OperLog operLog)
    {
        this.save("OperLogMapper.addOperlog", operLog);
    }

}
