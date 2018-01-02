/**
 * Copyright (C), 2018
 * FileName: ISettingDao
 * Author:   zxuser
 * Date:     2018/1/2 18:02
 * Description: 持久层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.setting.dao;

import com.xingrongjinfu.system.setting.model.Setting;

/**
 * 〈一句话功能简述〉<br> 
 * 〈持久层〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
public interface ISettingDao {

    Setting findSetting();

    /**
     * 更新系统设置
     */
    int updateSetting(Setting setting);
}