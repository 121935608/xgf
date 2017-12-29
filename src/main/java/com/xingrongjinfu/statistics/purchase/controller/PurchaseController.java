package com.xingrongjinfu.statistics.purchase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.shiro.service.MailSenderService;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.service.IRoleService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.statistics.purchase.common.PurchaseConstant;
import com.xingrongjinfu.system.user.common.UserConstant;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.model.UserRole;
import com.xingrongjinfu.system.user.service.IUserService;

/**
 *  业务处理
 * 
 * @author 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class PurchaseController extends BaseController
{

    /**
     * 跳转列表界面
     */
    @RequestMapping(PurchaseConstant.PURCHASE_URL)//PURCHASE_URL = "purchaseView"
    public ModelAndView loadSystemUser()
    {
        return this.getModelAndView(PurchaseConstant.PURCHASE_PAGE);//PURCHASE_PAGE = "statistics/purchase-list"
    }

}
