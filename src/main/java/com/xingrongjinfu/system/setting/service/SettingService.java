/**
 * Copyright (C), 2018
 * FileName: SettingService
 * Author:   zxuser
 * Date:     2018/1/2 18:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.setting.service;

import com.xingrongjinfu.system.setting.dao.ISettingDao;
import com.xingrongjinfu.system.setting.model.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
@Service("settingService")
public class SettingService implements ISettingService{

    @Autowired
    private ISettingDao settingDao;

    @Override
    public Setting findSetting() {
        return settingDao.findSetting();
    }

    @Override
    public int updateSetting(Setting setting) {
        return settingDao.updateSetting(setting);
    }
}