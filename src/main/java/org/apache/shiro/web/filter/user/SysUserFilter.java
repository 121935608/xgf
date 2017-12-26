package org.apache.shiro.web.filter.user;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.ShiroConstants;
import org.apache.shiro.common.UserConstants;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.service.IUserService;

/**
 * 访问请求过滤器
 * 
 * @author y
 */
public class SysUserFilter extends AccessControlFilter
{

    /**
     * 用户锁定后重定向的地址
     */
    private String userBlockedUrl;

    @Autowired
    private IUserService userService;

    public String getUserBlockedUrl()
    {
        return userBlockedUrl;
    }

    public void setUserBlockedUrl(String userBlockedUrl)
    {
        this.userBlockedUrl = userBlockedUrl;
    }

    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        Subject subject = getSubject(request, response);
        if (subject == null)
        {
            return true;
        }

        String username = (String) subject.getPrincipal();
        // 此处注意缓存 防止大量的查询db
        User user = userService.findByUserName(username);
        // 把当前用户放到session中
        subject.getSession().setAttribute(ShiroConstants.CURRENT_USER, user);
        // druid监控需要
        // ((HttpServletRequest) request).getSession().setAttribute(ShiroConstants.CURRENT_USER, username);

        return true;
    }

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception
    {
        User user = (User) request.getAttribute(ShiroConstants.CURRENT_USER);
        if (user == null)
        {
            return true;
        }

        if (UserConstants.blocked.equals(user.getLocked()))
        {
            getSubject(request, response).logout();
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        getSubject(request, response).logout();
        saveRequestAndRedirectToLogin(request, response);
        return true;
    }

    /**
     * 用于处理如登录成功后/重定向到上一个请求：
     */
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException
    {
        WebUtils.issueRedirect(request, response, getUserBlockedUrl());
    }

}
