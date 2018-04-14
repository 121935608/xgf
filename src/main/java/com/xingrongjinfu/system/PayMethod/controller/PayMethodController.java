package com.xingrongjinfu.system.PayMethod.controller;

import com.xingrongjinfu.system.PayMethod.common.PayMethodConstant;
import com.xingrongjinfu.system.PayMethod.model.PayMethod;
import com.xingrongjinfu.system.PayMethod.service.IPayMethodService;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.supervisor.common.SupervisorConstant;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.supervisor.service.ISupervisorService;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.service.IUserService;
import com.xingrongjinfu.system.user.service.UserService;
import com.xingrongjinfu.utils.AccessCodeUtil;
import com.xingrongjinfu.utils.UuidUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 Lelouch
 * @创建时间 $date$
 * @描述
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class PayMethodController extends BaseController{

//    @Autowired
//    private ISupervisorService supervisorService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPayMethodService iPayMethodService;
    /**
     * 跳转到支付方式页面
     * @return
     */
    @RequestMapping(PayMethodConstant.PAY_URL)
    public ModelAndView loadSupervisor(){return this.getModelAndView(PayMethodConstant.PAY_PAGE);}

    /**
     * 查询支付方式列表
     */
    @RequestMapping(PayMethodConstant.PAY_LIST_URL)
    public ModelAndView payList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfoList=iPayMethodService.payList(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(),tableDataInfoList);
    }



    /**
     * 跳转到支付方式添加界面
     * @return
     */
    @RequestMapping(PayMethodConstant.PAYMETHOD_ADD_URL)
    public ModelAndView loadAddPage(){return this.getModelAndView(PayMethodConstant.PAYMETHOD_ADD_PAGE);}

    /**
     * 添加支付方式
     * @param payMethod
     * @return
     */
    @RequestMapping(PayMethodConstant.PAYMETHOD_ADD)
    public @ResponseBody Message addPayMethod(PayMethod payMethod){
        User user=getCurrentUser();
        int result=0;
        if(payMethod.getStatus()==0){
            payMethod.setStatus(1);
        }else{
            payMethod.setStatus(-1);
        }
        payMethod.setPayId(UuidUtil.get32UUID());
        payMethod.setCreator(user.getUserId());
//        payMethod.setCreateTime(new Date());
        result = iPayMethodService.addPayMethod(payMethod);
        return new Message(result);
    }

    /**
     * 督导员的启用停用
     */
    @RequestMapping(PayMethodConstant.PAYMETHOD_STATUS_CHANGE)
    public @ResponseBody Message changeStatus(PayMethod payMethod)
    {
        int result=0;
        String payId=payMethod.getPayId();
        if (payId!=null) {
            result = iPayMethodService.updatePayMethod(payMethod);
        }
        return new Message(result);
    }


}
