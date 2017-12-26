package org.apache.shiro.realm;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.ShiroConstants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.common.utils.SessionUtils;
import org.apache.shiro.exception.JCaptchaException;
import org.apache.shiro.exception.RoleBlockedException;
import org.apache.shiro.exception.UserBlockedException;
import org.apache.shiro.exception.UserException;
import org.apache.shiro.exception.UserNotExistsException;
import org.apache.shiro.exception.UserPasswordNotMatchException;
import org.apache.shiro.exception.UserPasswordRetryLimitExceedException;
import org.apache.shiro.service.LoginService;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.service.IUserService;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author y
 */
public class UserRealm extends AuthorizingRealm
{

    @Autowired
    private IUserService userService;

    @Autowired
    private LoginService loginService;

    private static final Logger log = LoggerFactory.getLogger("sys-error");

    /**
     * 为当限前登录的用户授予角色和权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String username = (String) principals.getPrimaryPrincipal();

        User user = userService.findByUserPermission(username);

        // 单独定一个集合对象
        List<String> permissions = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();

        if (user != null)
        {
            for (Role role : user.getRoles())
            {
                roles.add(role.getRoleName()); // 将数据库中的角色存入集合
                for (Permission permission : role.getPermissions())
                {
                    permissions.add(permission.getPermsKey()); // 将数据库中的权限标签 符放入集合
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        String jcaptcha = (String) SessionUtils.getRequestAttribute(ShiroConstants.CURRENT_JCAPTCHA);
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try
        {
            user = loginService.login(username, password, jcaptcha);
        }
        catch (JCaptchaException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (RoleBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            log.error("login error", e);
            throw new AuthenticationException(new UserException("user.unknown.error", null));
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), password.toCharArray(),
                getName());
        return info;
    }

}
