package org.apache.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xingrongjinfu.system.user.service.IUserService;

/**
 * 1、onLoginFailure 时 把异常添加到request attribute中 而不是异常类名
 * 
 * 2、登录成功时：成功页面重定向：
 * 
 * @author y
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter
{

    @Autowired
    IUserService userService;

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae)
    {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

    /**
     * 默认的成功地址
     */
    private String defaultSuccessUrl;

    public void setDefaultSuccessUrl(String defaultSuccessUrl)
    {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }

    public String getDefaultSuccessUrl()
    {
        return defaultSuccessUrl;
    }

    /**
     * 登录成功跳转页面
     */
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
            ServletResponse response) throws Exception
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = getDefaultSuccessUrl();
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url); // 页面跳转
        return false;
    }

}
