package org.apache.shiro.service;

import org.apache.shiro.common.UserConstants;
import org.apache.shiro.common.utils.MessageUtils;
import org.apache.shiro.common.utils.SystemLogUtils;
import org.apache.shiro.exception.JCaptchaException;
import org.apache.shiro.exception.RoleBlockedException;
import org.apache.shiro.exception.UserBlockedException;
import org.apache.shiro.exception.UserNotExistsException;
import org.apache.shiro.exception.UserPasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.service.IUserService;

/**
 * 登录校验方法
 * 
 * @author y
 */
@Component
public class LoginService
{

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    private String LOGIN_SUCCESS = "loginSuccess", LOGIN_FAIL = "loginError";

    /**
     * 登录
     */
    public User login(String username, String password, String jcaptcha)
    {

        if (!StringUtils.isEmpty(jcaptcha))
        {
            SystemLogUtils.log(username, LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
            throw new JCaptchaException();
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            SystemLogUtils.log(username, LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 肯定错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            SystemLogUtils.log(username, LOGIN_FAIL, MessageUtils.message("user.password.not.match"));

            throw new UserPasswordNotMatchException();
        }

        User user = null;

        // 此处需要走代理对象，目的是能走缓存切面
        if (maybeUsername(username))
        {
            user = userService.findByUserName(username);
        }

        if (user == null)
        {
            SystemLogUtils.log(username, LOGIN_FAIL, MessageUtils.message("user.not.exists"));

            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (UserConstants.blocked.equals(user.getLocked()))
        {
            SystemLogUtils.log(username, LOGIN_FAIL, MessageUtils.message("user.blocked", user.getDescription()));
            throw new UserBlockedException(user.getDescription());
        }

        Role role = userService.findRoleByUserId(user.getUserId());

        if (UserConstants.blocked.equals(role.getStatus()))
        {
            SystemLogUtils.log(username, LOGIN_FAIL, MessageUtils.message("role.blocked", user.getDescription()));
            throw new RoleBlockedException(role.getDescription());
        }
        
        SystemLogUtils.log(username, LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        return user;
    }

    private boolean maybeUsername(String username)
    {
        // 如果用户名不在指定范围内也是错误的
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            return false;
        }

        return true;
    }
}
