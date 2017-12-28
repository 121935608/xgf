package com.xingrongjinfu.system.user.service;

import java.util.List;
import org.apache.shiro.common.UserConstants;
import org.apache.shiro.service.PasswordService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.dao.IUserDao;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 用户 业务层处理
 * 
 * @author y
 */
@Service("userService")
public class UserService implements IUserService
{

    @Autowired
    private IUserDao userDao;

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User findByUserName(String userName)
    {
        return userDao.findByUserName(userName);
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名
     * @return
     */
    @Override
    public String checkNameUnique(User user)
    {
        if (user.getUserId() == null)
        {
            user.setUserId("-1");
        }
        String username = user.getUserName();
        String userId = user.getUserId();
        user = new User();
        user.setUserName(username);
        User newUser = userDao.findByUserName(username);
        if (ObjectUtil.isNotNull(newUser) && newUser.getUserId() != userId)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }

    /**
     * 通过用户名ID用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User findByUserId(String userId)
    {
        return userDao.findByUserId(userId);
    }

    /**
     * 根据用户ID查询角色信息
     * 
     * @param userId
     * @return 角色对象
     */
    @Override
    public Role findRoleByUserId(String userId)
    {
        return userDao.findRoleByUserId(userId);
    }

    /**
     * 根据条件分页查询用户对象
     * 
     * 
     * @param page 分页对象
     * @return 用户信息集合信息
     */
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return userDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过用户名查询所属角色和权限
     * 
     * @param userName
     * @return 用户对象信息
     */
    @Override
    public User findByUserPermission(String username)
    {
        return userDao.findByUserPermission(username);
    }

    /**
     * 根据用户名查询权限表
     * 
     * @param username 用户名
     * @return 用户对象信息
     */
    @Override
    public List<Permission> findPermsListByUserId(String username)
    {
        return userDao.findPermsListByUserId(username);
    }

    /**
     * 根据用户名查询角色表
     * 
     * @param username 用户名
     * @return 用户对象信息
     */
    @Override
    public List<Role> findRoleListByUserId(String username)
    {
        return userDao.findRoleListByUserId(username);
    }

    /**
     * 修改用户状态
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int changeUserStatus(User user)
    {
        return updateUserInfo(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int changePassword(User user)
    {
        String password = new PasswordService().encryptPassword(user.getUserName(), user.getPassword(), "");
        user.setPassword(password);
        return updateUserInfo(user);
    }

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(User user)
    {
        return userDao.updateUserInfo(user);
    }

    /**
     * 删除用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int deleteUserByInfo(User user)
    {
        userDao.deleteUserRoleInfo(user.getUserId());
        return userDao.deleteUserInfo(user);
    }

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int addUser(User user)
    {
        String password = new PasswordService().encryptPassword(user.getUserName(), user.getPassword(), "");
        user.setPassword(password);
        return userDao.addUserInfo(user);
    }

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userDao.updateUserInfo(user);
    }

}
