package com.xingrongjinfu.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroConstants;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xingrongjinfu.system.user.common.UserConstant;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录 业务处理
 * 
 * @author y
 */
@Controller
public class LoginController extends BaseController
{

    @RequestMapping(value = { UserConstant.LOGIN_URL })
    public String loginForm(HttpServletRequest request, ModelMap model)
    {
        // 登录失败了 提取错误消息
        Exception shiroLoginFailureEx = (Exception) request
                .getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if (shiroLoginFailureEx != null)
        {
            model.addAttribute(ShiroConstants.ERROR, shiroLoginFailureEx.getMessage());
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated())
        {
            subject.logout();
        }

        return UserConstant.LOGIN_PAGE;
    }



}
