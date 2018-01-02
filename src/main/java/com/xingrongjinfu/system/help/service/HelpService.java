/**
 * Copyright (C), 2017
 * FileName: HelpService
 * Author:   zxuser
 * Date:     2017/12/29 17:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.help.service;

import com.xingrongjinfu.system.help.dao.IHelpDao;
import com.xingrongjinfu.system.help.model.Help;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
@Service("helpService")
public class HelpService implements IHelpService{

    @Autowired
    private IHelpDao helpDao;

    @Override
    public List<Help> HelpPageInfoQuery(PageUtilEntity pageUtilEntity) {
        return helpDao.HelpPageInfoQuery(pageUtilEntity);
    }

    @Override
    public Help getUnreply(String feedBackId) {
        return helpDao.getUnreply(feedBackId);
    }

    @Override
    public List<Help> getReply(String userId) {
        return helpDao.getReply(userId);
    }

    @Override
    public int updateHelpInfo(Help help) {
        return helpDao.updateHelpInfo(help);
    }
}