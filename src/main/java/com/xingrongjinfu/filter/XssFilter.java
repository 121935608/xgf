package com.xingrongjinfu.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对界面js脚本注入处理过滤器
 * 
 * @author y
 */
public class XssFilter implements Filter
{

    protected FilterConfig filterConfig;
    private String errorURL;

    public XssFilter()
    {
        filterConfig = null;
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        errorURL = filterConfig.getInitParameter("redirectURL");
    }

    public void destroy()
    {
    }

    /**
     * filter判断
     */
    @SuppressWarnings("rawtypes")
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements())
        {
            String name = (String) en.nextElement();
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++)
            {
                System.out.println("[" + name + "]:[" + values[i] + "]");
                if (isScript(values[i]))
                {
                    System.out.println("发现特殊字符:[" + name + "]:[" + values[i] + "]");
                    if (!isAjaxRequest(request))
                    {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().write("{ \"s\": \"false\", \"m\": \"发现特殊字符:[" + values[i] + "]\" }");
                        return;
                    }
                    else
                    {
                        response.sendRedirect(request.getContextPath() + errorURL);
                        return;
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        return;

    }

    /**
     * Script注入判断
     * 
     * @param content 待检测内容
     * @return true 发现不合法内容 false 未存在不合法内容
     */
    public static boolean isScript(String content)
    {
        if (content == null || content.length() <= 0)
        {
            return false;
        }
        // 包含script关键字不需要拦截
        if ("description".equals(content))
        {
            return false;
        }
        if (content.indexOf("&") >= 0 || content.indexOf("<") >= 0 || content.indexOf(">") >= 0
                || content.indexOf("'") >= 0)
        {
            return true;
        }
        content = content.toLowerCase();
        if (content.indexOf("alert") >= 0 || content.indexOf("script") >= 0)
        {
            return true;
        }
        return false;
    }

    private boolean isAjaxRequest(HttpServletRequest request)
    {
        return request.getParameter("ajax") != null;
    }

}
