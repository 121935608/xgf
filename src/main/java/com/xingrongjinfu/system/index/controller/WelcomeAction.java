package com.xingrongjinfu.system.index.controller;

import java.util.Collection;
import org.apache.shiro.common.utils.SessionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xingrongjinfu.system.index.common.WelcomeConstant;

/**
 * 欢迎界面 业务处理
 * 
 * @author y
 */
@Controller
public class WelcomeAction
{
    @Autowired
    private SessionDAO sessionDAO;

    // 欢迎页面
    @RequestMapping(WelcomeConstant.OPERLOG_URL)
    public String welcome(Model model) throws Exception
    {
        Collection<Session> sessiondao = sessionDAO.getActiveSessions(); // 获取所有活跃会话集合
        model.addAttribute("sessions", sessiondao.size());
        model.addAttribute("ipaddress", SessionUtils.getSession().getHost());
        return WelcomeConstant.WELCOME_PAGE;
    }
}
