/**
 * Copyright (C), 2018
 * FileName: SettingDao
 * Author:   zxuser
 * Date:     2018/1/2 18:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.setting.dao;

import com.xingrongjinfu.system.setting.model.Setting;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
@Repository("settingDao")
public class SettingDao extends DynamicObjectBaseDao implements ISettingDao {

    @Override
    public Setting findSetting() {
        return (Setting) this.findForObject("SettingMapper.findSetting",null);
    }

    @Override
    public int updateSetting(Setting setting) {
        return this.update("SettingMapper.updateSetting",setting);
    }
}