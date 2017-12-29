package org.framework.core.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroConstants;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.web.DatatablesSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.system.user.model.User;

/**
 * 业务通用处理方法
 * 
 * @author y
 * 
 */
public class BaseController
{

    /**
     * 得到PageUtilEntity
     */
    public PageUtilEntity getPageUtilEntity()
    {
        PageUtilEntity pageRequest = DatatablesSupport.buildPageRequest(this.getRequest());
        return pageRequest;
    }

    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView()
    {
        return new ModelAndView();
    }

    /**
     * 得到ModelAndJsonView
     */
    public ModelAndView getModelAndJsonView()
    {
        return new ModelAndView("jsonView");
    }

    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView(String url)
    {
        return new ModelAndView(url);
    }

    /**
     * 返回数据到表格
     */
    public ModelAndView buildDataTable(int totalResult, List<TableDataInfo> tableDataInfo)
    {
        ModelAndView modelAndJsonView = this.getModelAndJsonView();
        modelAndJsonView.addObject("recordsTotal", totalResult);
        modelAndJsonView.addObject("recordsFiltered", totalResult);
        modelAndJsonView.addObject("data", tableDataInfo);
        return modelAndJsonView;
    }

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        return request;
    }

    /**
     * 得到session用户对象
     */
    public User getCurrentUser()
    {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        User user = (User) session.getAttribute(ShiroConstants.CURRENT_USER);
        return user;
    }

    protected HttpSession getSession()
    {
        return getRequest().getSession();
    }

}
