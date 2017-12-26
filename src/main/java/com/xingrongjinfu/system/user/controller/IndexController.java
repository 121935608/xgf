package com.xingrongjinfu.system.user.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.user.common.UserConstant;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.service.IUserService;
import com.xingrongjinfu.utils.TreeUtil;

/**
 * 首页 业务处理
 * 
 * @author y
 */
@Controller
public class IndexController extends BaseController
{

    @Autowired
    private IUserService userService;

    // 系统首页
    @RequestMapping(UserConstant.INDEX_URL)
    public String index(Model model, HttpSession session) throws Exception
    {
        // 从shiro的session中取activeUser
        // 取身份信息
        User currentUser = getCurrentUser();

        // 根据用户id取出菜单
        List<Permission> permissions = userService.findPermsListByUserId(currentUser.getUserName());


        // 通过model传到页面
        session.setAttribute("permissions", TreeUtil.getChildPerms(permissions, 0));
        return UserConstant.INDEX_PAGE;
    }

}
