package com.xingrongjinfu.system.index.controller;

import java.util.Collection;
import java.util.List;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.order.service.IOrderService;
import com.xingrongjinfu.system.pays.common.PaysConstant;
import com.xingrongjinfu.system.pays.model.Pays;
import com.xingrongjinfu.system.pays.service.IPaysService;
import com.xingrongjinfu.system.storeaffairs.service.ICertificationService;
import com.xingrongjinfu.system.user.service.IUserService;
import com.xingrongjinfu.system.user.service.UserService;
import org.apache.shiro.common.utils.SessionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xingrongjinfu.system.index.common.WelcomeConstant;
import org.springframework.web.servlet.ModelAndView;

/**
 * 欢迎界面 业务处理
 * 
 * @author y
 */
@Controller
public class WelcomeAction extends BaseController
{
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private IPaysService paysService;
   @Autowired
    private IUserService userService;
   @Autowired
   private IOrderService orderService;
   @Autowired
   private ICertificationService certificationService;

   /* // 欢迎页面
    @RequestMapping(WelcomeConstant.OPERLOG_URL)
    public String welcome(Model model) throws Exception
    {
        Collection<Session> sessiondao = sessionDAO.getActiveSessions(); // 获取所有活跃会话集合
        model.addAttribute("sessions", sessiondao.size());
        model.addAttribute("ipaddress", SessionUtils.getSession().getHost());
        List<Pays> payss=paysService.firstPageInfoQuery();
        Integer i=1;
        for (Pays pays :payss){
            pays.setCashNum(i++);
        }
        model.addAttribute("pays",payss);
        return WelcomeConstant.WELCOME_PAGE;
    }*/

    //欢迎界面
    @RequestMapping(WelcomeConstant.OPERLOG_URL)
    public ModelAndView loadFirst(){
        ModelAndView modelAndView=this.getModelAndView(WelcomeConstant.WELCOME_PAGE);
        //查询注册人数
        Integer count= userService.findAllMerchant();
        //查询认证申请数量
        Integer certificationCount=userService.findAllCount();
        //查询贷款申请数量
        Integer orderCount=userService.findAllOrders();
        //封装交易数据
        List<Pays> payss=paysService.firstPageInfoQuery();
        /*Integer i=1;
        for (Pays pays :payss){
            pays.setCashNum(i++);
        }*/
        modelAndView.addObject("count",count);
        modelAndView.addObject("orderCount",orderCount);
        modelAndView.addObject("certificationCount",certificationCount);
        modelAndView.addObject("pays",payss);
        return modelAndView;
    }

}
