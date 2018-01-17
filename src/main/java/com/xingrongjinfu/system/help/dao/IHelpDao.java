/**
 * Copyright (C), 2017
 * FileName: IHelpDao
 * Author:   zxuser
 * Date:     2017/12/29 17:14
 * Description: 帮助与反馈的持久层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.help.dao;

import com.xingrongjinfu.system.help.model.Help;
import org.framework.base.util.PageUtilEntity;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈帮助与反馈的持久层〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
public interface IHelpDao {

    /**
     * 帮助与反馈信息查询
     * @param pageUtilEntity
     * @return
     */
    List<Help>HelpPageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 查询未回复的反馈信息
     */
    Help getUnreply(Help help);

    /**
     * 查询用户的所有反馈信息
     */
    List<Help> getReply(Help help);

    /**
     * 回复信息
     * @param help
     * @return
     */
    int updateHelpInfo(Help help);

    /**
     * 查询第一次提问信息
     */
    Help getFirstHelp(Help help);
}