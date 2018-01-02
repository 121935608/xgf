/**
 * Copyright (C), 2018
 * FileName: SettingController
 * Author:   zxuser
 * Date:     2018/1/2 18:01
 * Description: 控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.setting.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.setting.common.SettingConstant;
import com.xingrongjinfu.system.setting.model.Setting;
import com.xingrongjinfu.system.setting.service.ISettingService;
import com.xingrongjinfu.system.user.model.User;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈一句话功能简述〉<br> 
 * 〈控制层〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class SettingController extends BaseController {

    @Autowired
    private ISettingService settingService;

    @RequestMapping(SettingConstant.SERTTING_URL)
    public ModelAndView loadSettingPage(){
        ModelAndView modelAndView=this.getModelAndView(SettingConstant.SETTING_PAGE);
        Setting setting=settingService.findSetting();
        modelAndView.addObject("setting",setting);
        return modelAndView;
    }

    @RequestMapping(SettingConstant.SETTING_MODIFY)
    public @ResponseBody
    Message updateSetting(Setting setting){
       int result=0;
       User user=this.getCurrentUser();
       setting.setCreator(user.getUserName());
       result=settingService.updateSetting(setting);
       return new Message(result);
    }
}