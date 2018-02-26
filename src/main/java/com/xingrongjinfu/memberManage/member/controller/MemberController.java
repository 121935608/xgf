/**
 * Copyright (C), 2018
 * FileName: MemberController
 * Author:   zxuser
 * Date:     2018/1/25 14:37
 * Description: 会员/用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.memberManage.member.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.memberManage.member.common.MemberConstant;
import com.xingrongjinfu.memberManage.member.model.Membership;
import com.xingrongjinfu.memberManage.member.service.IMemberService;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.utils.IdUtil;
import com.xingrongjinfu.utils.StringUtil;

/**
 * 〈一句话功能简述〉<br> 
 * 〈会员/用户管理〉
 *
 * @author zxuser
 * @create 2018/1/25
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.MEMBER_URL)
public class MemberController extends BaseController{

    @Autowired
    private IMemberService memberService;

    /**
     * 加载会员资料界面
     * @return
     */
    @RequestMapping(MemberConstant.MEMBERSHIP_URL)
    public ModelAndView loadPage()
    {
        ModelAndView modelAndView=this.getModelAndView(MemberConstant.MEMBERSHIP_PAGE);
        String storeId=(String)SessionUtils.getSession().getAttribute("storeId");
        if (storeId ==null || storeId ==""){
            storeId="-1";
        }
        System.out.println("storeId:"+storeId);
        modelAndView.addObject("storeId", storeId);
        return modelAndView;
    }

    /**
     * 查询会员信息
     * @return
     */
    @RequestMapping(MemberConstant.MEMBERSHIP_LIST_URL)
    public ModelAndView getMemberInfo(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<Membership> tableDataInfos=memberService.pageQueryInfo(pageUtilEntity);
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Membership membership:tableDataInfos){
            if (membership.getTimeLimit()!=null) {
                int aaa=0;
                try {
                    if (sdf.parse(membership.getTimeLimit()).getTime()-sdf.parse(sdf.format(new Date())).getTime() <0) {
                        membership.setIsLimit("过期");
                    } else {
                        membership.setIsLimit("未过期");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                membership.setIsLimit("");
            }
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfos);
    }

    /**
     * 加载添加会员界面
     */
    @RequestMapping(MemberConstant.MEMBERSHIP_ADD_URL)
    public ModelAndView loadAddMember(){
       ModelAndView modelAndView=this.getModelAndView(MemberConstant.MEMBERSHIP_ADD_PAGE);
        String storeId=(String)SessionUtils.getSession().getAttribute("storeId");
        if (storeId ==null || storeId ==""){
            storeId="-1";
        }
        modelAndView.addObject("storeId", storeId);
        return modelAndView;
    }

    /**
     * 校验会员名是否唯一
     */
    @RequestMapping(MemberConstant.MEMBERSHIP_CHECK_NAME_URL)
    public @ResponseBody int checkName(String memberNo,String memberId){
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Map map = new HashMap();
        map.put("storeId", storeId);
        map.put("memberNo", memberNo);
        if(null != memberId){
            map.put("memberId", memberId);
        }
        int n = memberService.getByName(map);
        return n;
    }

    /**
     * 添加或更新会员信息
     * @throws ParseException 
     */
    @RequestMapping(MemberConstant.MEMBERSHIP_SAVE_URL)
    public @ResponseBody
    Message updateMember(Membership membership,String timeLimi) throws ParseException{
        int result=0;
        String memberId=membership.getMemberId();
        membership.setTimeLimit(timeLimi);
        String phone = membership.getMemberNo();
        if(!StringUtil.checkPhone(phone)){
            return new Message(false,"请输入正确的手机号！");
        }
        if (memberId !=null && memberId !=""){
            //id存在则更新信息
            result=memberService.updateMember(membership);
        }else {
            //不存在则插入数据
           membership.setMemberId(IdUtil.get32UUID());
           result= memberService.addMember(membership);
        }
        return new Message(result);
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping(MemberConstant.MEMBERSHIP_EDIT_URL)
    public ModelAndView loadEditPage(Membership membership){
        ModelAndView modelAndView=this.getModelAndView(MemberConstant.MEMBERSHIP_EDIT_PAGE);
        if(membership.getMemberId() !=null && membership.getMemberId()!=""){
            Membership membershipInfo=memberService.findMembership(membership);
            modelAndView.addObject("membership",membershipInfo);
        }
        return modelAndView;
    }
}