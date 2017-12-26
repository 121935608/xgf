package com.xingrongjinfu.system.userlog.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.system.userlog.model.UserLog;

/**
 * 登录日志 数据层
 * 
 * @author y
 */
public interface IUserLogDao
{

    /**
     * 根据条件分页查询访问日志对象
     * 
     * @param page 分页对象
     * @return 系统日志对象集合
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 新增访问日志
     * 
     * @param operLog 访问日志对象
     */
    public void addUserlog(UserLog userLog);
}
