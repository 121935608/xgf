package com.xingrongjinfu.system.userlog.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.userlog.model.UserLog;

/**
 * 登录日志 数据层处理
 * 
 * @author y
 */
@Repository("userLogDao")
public class UserLogDao extends DynamicObjectBaseDao implements IUserLogDao
{

    /**
     * 查询操作日志列表
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<TableDataInfo> tableDataInfo = null;
        try
        {
            tableDataInfo = (List<TableDataInfo>) this.findForList("UserLogMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tableDataInfo;
    }

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    public void addUserlog(UserLog userLog)
    {
        this.save("UserLogMapper.addUserlog", userLog);
    }

}
