/**
 * Copyright (C), 2017
 * FileName: HelpController
 * Author:   zxuser
 * Date:     2017/12/29 17:13
 * Description: 帮助与反馈的控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.help.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.help.common.HelpConstant;
import com.xingrongjinfu.system.help.model.Help;
import com.xingrongjinfu.system.help.service.HelpService;
import com.xingrongjinfu.system.help.service.IHelpService;
import com.xingrongjinfu.system.user.model.User;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈帮助与反馈的控制层〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class HelpController extends BaseController{

    @Autowired
    private IHelpService helpService;

    /**
     * 跳转到帮助与反馈的界面
     * @return
     */
    @RequestMapping(HelpConstant.HELP_URL)
    public ModelAndView loadHelp(){return this.getModelAndView(HelpConstant.HELP_PAGE);}

    /**
     * 帮助与反馈信息查询
     */
    @RequestMapping(HelpConstant.HELP_LIST_URL)
    public ModelAndView helpList()
    {
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<Help>tableDataInfo=helpService.HelpPageInfoQuery(pageUtilEntity);
        Integer i=1;
        for (Help help :tableDataInfo){
            help.setNumber(pageUtilEntity.getPage()+(i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 跳转到回复界面
     */
    @RequestMapping(HelpConstant.HELP_REPLY_URL)
    public ModelAndView loadReply(String feedBackId,String userId)
    {
        ModelAndView modelAndView=this.getModelAndView(HelpConstant.HELP_REPLY_PAGE);
        Help unHelpInfo=helpService.getUnreply(feedBackId);
        List<Help> helpInfo=helpService.getReply(userId);
        modelAndView.addObject("unHelpInfo",unHelpInfo);
        modelAndView.addObject("helpInfo",helpInfo);
        return modelAndView;
    }

    /**
     * 回复反馈信息
     */
    @RequestMapping(HelpConstant.HELP_REPLY)
    public @ResponseBody
    Message toReply(Help help,String feedBackId){
        int result = 0;
        User user=this.getCurrentUser();
        help.setAnswer(user.getUserName());
        help.setFeedBackId(feedBackId);
        result= helpService.updateHelpInfo(help);
        return new Message(result);
    }
}