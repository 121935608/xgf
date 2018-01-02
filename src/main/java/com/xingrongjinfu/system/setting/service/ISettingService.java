/**
 * Copyright (C), 2018
 * FileName: ISettingService
 * Author:   zxuser
 * Date:     2018/1/2 18:03
 * Description: 业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.setting.service;

import com.xingrongjinfu.system.setting.model.Setting;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务层〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
public interface ISettingService {

    /**
     * 查询系统设置
     */
    Setting findSetting();

    /**
     * 更新系统设置
     */
    int updateSetting(Setting setting);
}