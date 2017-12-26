package org.apache.shiro.web.filter.jcaptcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.ShiroConstants;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.jcaptcha.JCaptcha;

/**
 * 验证码过滤器
 * 
 * @author y
 */
public class JCaptchaValidateFilter extends AccessControlFilter
{

    private boolean jcaptchaEbabled = true;

    private String jcaptchaParam = "jcaptchaCode";

    /**
     * 是否开启jcaptcha
     *
     * @param jcaptchaEbabled
     */
    public void setJcaptchaEbabled(boolean jcaptchaEbabled)
    {
        this.jcaptchaEbabled = jcaptchaEbabled;
    }

    /**
     * 前台传入的验证码
     *
     * @param jcaptchaParam
     */
    public void setJcaptchaParam(String jcaptchaParam)
    {
        this.jcaptchaParam = jcaptchaParam;
    }

    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        request.setAttribute("jcaptchaEbabled", jcaptchaEbabled);
        return super.onPreHandle(request, response, mappedValue);
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 验证码禁用 或不是表单提交 允许访问
        if (jcaptchaEbabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase()))
        {
            return true;
        }
        return JCaptcha.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        request.setAttribute(ShiroConstants.CURRENT_JCAPTCHA, ShiroConstants.JCAPTCHA_ERROR);
        return true;
    }
}
