package com.xingrongjinfu.system.userlog.service;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.userlog.dao.IUserLogDao;
import com.xingrongjinfu.system.userlog.model.UserLog;

/**
 * 登录日志 服务层处理
 * 
 * @author y
 */
@Service("userLogService")
public class UserLogService implements IUserLogService
{

    @Autowired
    private IUserLogDao userLogDao;

    /**
     * 根据条件分页查询日志对象
     * 
     * @param page 分页对象
     * @return 系统日志对象集合
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return userLogDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    public void addUserlog(UserLog userlog)
    {
        userLogDao.addUserlog(userlog);
    }

}
