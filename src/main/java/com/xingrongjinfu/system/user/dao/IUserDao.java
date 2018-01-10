package com.xingrongjinfu.system.user.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.model.User;

/**
 * 用户 数据层
 * 
 * @author y
 */
public interface IUserDao
{

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User findByUserName(String userName);

    /**
     * 通过用户名ID用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public User findByUserId(String userId);

    /**
     * 根据用户ID查询角色信息
     * 
     * @param userId
     * @return 角色对象
     */
    public Role findRoleByUserId(String userId);

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 通过用户名查询所属角色和权限
     * 
     * @param userName
     * @return 用户对象信息
     */
    public User findByUserPermission(String userName);

    /**
     * 根据用户名查询权限表
     * 
     * @param username 用户名
     * @return 用户对象信息
     */
    public List<Permission> findPermsListByUserId(String username);

    /**
     * 根据用户名查询角色表
     * 
     * @param username 用户名
     * @return 用户对象信息
     */
    public List<Role> findRoleListByUserId(String username);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int addUserInfo(User user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserInfo(User user);

    /**
     * 删除用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int deleteUserInfo(User user);

    /**
     * 删除用户和角色关联信息
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleInfo(String userId);

    /**
     * 确认用户
     */
    int confirmUser(User user);

    /**
     * 修改B端用户信息
     */
    int updateInfo(User user);
}
