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

import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Integer i=1;
        for (Help help :tableDataInfo){
            help.setReplyTimes(help.getReplyTime()==null ? "":sdf.format(help.getReplyTime()));
            help.setNumber(pageUtilEntity.getPage()+(i++));
           /* //首次提问的信息
            Help firstInfo=helpService.getFirstHelp(help);
            //获取所有的已回复的追问信息
            List<Help> helpInfo=helpService.getReply(help);
            //获取未回复的追问信息
            Help unHelpInfo=helpService.getUnreply(help);

            if(unHelpInfo ==null && firstInfo.getReply()==null && helpInfo.size()==0){
                //第一次提问
                help.setToReply("0");
            } else if (unHelpInfo ==null && firstInfo.getReply() !=null && helpInfo.size()==0) {
                //一问一答
                help.setToReply("1");
            }else if (unHelpInfo ==null && firstInfo.getReply() !=null && helpInfo.size()>0) {
                //一问多答
                help.setToReply("1");
            }
            else {
                help.setToReply("0");
            }*/
        }
       return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 跳转到回复界面
     */
    @RequestMapping(HelpConstant.HELP_REPLY_URL)
    public ModelAndView loadReply(Help help)
    {
        ModelAndView modelAndView=this.getModelAndView(HelpConstant.HELP_REPLY_PAGE);
        //调整回显时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //首次提问的信息
        Help firstInfo=helpService.getFirstHelp(help);
        firstInfo.setAddTimes(firstInfo.getAddTime()==null?"":sdf.format(firstInfo.getAddTime()));
        firstInfo.setReplyTimes(firstInfo.getReplyTime()==null ?"":sdf.format(firstInfo.getReplyTime()));
        //获取所有的已回复的追问信息
        List<Help> helpInfo=helpService.getReply(help);
        //获取未回复的追问信息
        Help unHelpInfo=helpService.getUnreply(help);

        for (Help helps:helpInfo){
            helps.setAddTimes(helps.getAddTime()==null?"":sdf.format(helps.getAddTime()));
            helps.setReplyTimes(helps.getReplyTime()==null ? "":sdf.format(helps.getReplyTime()));
        }
        //把追问信息回显
        modelAndView.addObject("helpInfo",helpInfo);
        if(unHelpInfo ==null && firstInfo.getReply()==null && helpInfo.size()==0){
            //第一次提问
            modelAndView.addObject("unHelpInfo", firstInfo);
            modelAndView.addObject("firstInfo",null);
        } else if (unHelpInfo ==null && firstInfo.getReply()!=null && helpInfo.size()>0) {
            //一问一答
            modelAndView.addObject("firstInfo",firstInfo);
            modelAndView.addObject("unHelpInfo", null);
        }else {
            //有追问有回复的
            modelAndView.addObject("firstInfo",firstInfo);
            modelAndView.addObject("unHelpInfo", unHelpInfo);
        }
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