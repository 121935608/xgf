package com.xingrongjinfu.system.user.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.model.User;

/**
 * 用户 数据层处理
 * 
 * @author y
 */
@Repository("userDao")
public class UserDao extends DynamicObjectBaseDao implements IUserDao
{

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User findByUserName(String username)
    {
        return (User) this.findForObject("SystemUserMapper.findByUserName", username);
    }

    @Override
    public User findByUser(User user) {
        return (User) this.findForObject("SystemUserMapper.findByUser", user);
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
        return (User) this.findForObject("SystemUserMapper.findByUserId", userId);
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
        return (Role) this.findForObject("SystemUserMapper.findRoleByUserId", userId);
    }

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户对象信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> userPageInfo = null;
        try
        {
            userPageInfo = (List<TableDataInfo>) this.findForList("SystemUserMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userPageInfo;

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
        return (User) this.findForObject("SystemUserMapper.findByUserPermission", username);
    }

    /**
     * 根据用户名查询权限表
     * 
     * @param username 用户名
     * @return 用户对象信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> findPermsListByUserId(String username)
    {
        List<Permission> permsList = null;
        try
        {
            permsList = (List<Permission>) this.findForList("SystemUserMapper.findPermsListByUserId", username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    /**
     * 根据用户名查询角色表
     * 
     * @param username 用户名
     * @return 用户对象信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findRoleListByUserId(String username)
    {
        List<Role> roleist = null;
        try
        {
            roleist = (List<Role>) this.findForList("SystemUserMapper.findRoleListByUserId", username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roleist;
    }

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int addUserInfo(User user)
    {
        return (int) this.save("SystemUserMapper.addUserInfo", user);
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
        return this.update("SystemUserMapper.updateUserInfo", user);
    }

    @Override
    public int updateUserPassword(User user) {
        return this.update("SystemUserMapper.updateUserPassword",user);
    }

    /**
     * 删除用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int deleteUserInfo(User user)
    {
        return this.update("SystemUserMapper.deleteUserInfo", user);
    }

    /**
     * 删除用户和角色信息
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserRoleInfo(String userId)
    {
        return this.update("SystemUserMapper.deleteUserRoleInfo", userId);
    }

    @Override
    public int confirmUser(User user) {
        return (int)this.findForObject("SystemUserMapper.confirmUser",user);
    }

    @Override
    public int updatePassword(User user) {
        return this.update("SystemUserMapper.updatePassword",user);
    }

    @Override
    public int findAllMerchant() {
        return (int)this.findForObject("SystemUserMapper.findAllMerchant",null);
    }

    @Override
    public int findAllCount() {
        return (int)this.findForObject("SystemUserMapper.findAllCount",null);
    }
    @Override
    public int findAllOrders() {
        return (int)this.findForObject("SystemUserMapper.findAllOrders",null);
    }

    @Override
    public String findStoredIdByUserId(String userId) {
        return (String) this.findForObject("SystemUserMapper.findStoredIdByUserId",userId);
    }
}
