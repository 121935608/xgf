package com.xingrongjinfu.system.user.service;

import java.util.HashMap;
import java.util.List;

import com.xingrongjinfu.system.user.common.UserConstant;
import com.xingrongjinfu.utils.*;
import org.apache.shiro.common.UserConstants;
import org.apache.shiro.common.utils.Md5Utils;
import org.apache.shiro.service.PasswordService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.dao.IUserDao;
import com.xingrongjinfu.system.user.model.User;

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
     * @param
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

    @Override
    public String checkName(User user) {

        if (user.getUserId() == null)
        {
            user.setUserId("-1");
        }
        String username = user.getUserName();
        String userId = user.getUserId();
        user = new User();
        user.setUserName(username);
        user.setUserId(userId);
        User newUser = userDao.findByUser(user);
        if (ObjectUtil.isNotNull(newUser))
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
     * @param
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
     * @param
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
        int fail=0;
        if (user.getType().equalsIgnoreCase("B")) {
            String password= Md5Utils.hash(user.getUserName()+user.getPassword());
            String param="{\"username\":\""+user.getUserName()+"\",\"password\":\""+password+"\"}";
            String url= UserConstant.USER_SERVICE_URL+"/uaa/auth/modify?q="+param;
            String returnMsg= HttpClientUtil.sendGet(url);

            if(StringUtil.nullOrBlank(returnMsg)){
                return fail;
            }
            //转出json字符串
            HashMap returnMap= null;
            try {
                returnMap = DesUtils.stringToMap(returnMsg);
            } catch (DecryptExcption decryptExcption) {
                decryptExcption.printStackTrace();
            }

            String code=String.valueOf(returnMap.get("code"));
            if(code.equals("-1")){
                return fail;
            }
        }
        String newpassword = new PasswordService().encryptPassword(user.getUserName(), user.getPassword(), "");
        user.setPassword(newpassword);
        return userDao.updateUserPassword(user);
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

    @Override
    public int confirmUser(User user) {
        return userDao.confirmUser(user);
    }

    @Override
    public int updatePassword(User user) {
        int fail=0;
        String password= Md5Utils.hash(user.getUserName()+user.getPassword());
        String newPassword = new PasswordService().encryptPassword(user.getUserName(), user.getPassword(), "");
        user.setPassword(newPassword);
        String param="{\"username\":\""+user.getUserName()+"\",\"password\":\""+password+"\"}";
        String url= UserConstant.USER_SERVICE_URL+"/uaa/auth/modify?q="+param;
        String returnMsg= HttpClientUtil.sendGet(url);
        if(StringUtil.nullOrBlank(returnMsg)){
            return fail;
        }
        //转出json字符串
        HashMap returnMap= null;
        try {
            returnMap = DesUtils.stringToMap(returnMsg);
        } catch (DecryptExcption decryptExcption) {
            decryptExcption.printStackTrace();
        }

        String code=String.valueOf(returnMap.get("code"));
        System.out.println("code:"+code);
        if(code.equals("-1")){
            return fail;
        }
        return userDao.updatePassword(user);
    }

    @Override
    public String checkOldPassword(User user) {
        String notIdentical="1";
        String identical="0";
        if (user.getUserId() !=null) {
            User oldUser=userDao.findByUserId(user.getUserId());
            String oldPassword = new PasswordService().encryptPassword(oldUser.getUserName(), user.getOldPassword(), "");
           if (oldPassword.equals(oldUser.getPassword())){
                return identical;
           }
        }
        return notIdentical;
    }

    @Override
    public int findAllMerchant() {
        return userDao.findAllMerchant();
    }
    @Override
    public int findAllCount() {
        return userDao.findAllCount();
    }
    @Override
    public int findAllOrders() {
        return userDao.findAllOrders();
    }
}
