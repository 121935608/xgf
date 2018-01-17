/**
 * Copyright (C), 2017
 * FileName: HelpDao
 * Author:   zxuser
 * Date:     2017/12/29 17:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.help.dao;

import com.xingrongjinfu.system.help.model.Help;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
@Repository("helpDao")
public class HelpDao extends DynamicObjectBaseDao implements IHelpDao {

    @Override
    public List<Help> HelpPageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<Help> helpPageInfoQuery=null;
        try {
            helpPageInfoQuery=(List<Help>)this.findForList("HelpMapper.HelppageInfoQuery",pageUtilEntity);
        }catch (Exception e)
        {e.printStackTrace();}
        return helpPageInfoQuery;
    }

    @Override
    public Help getUnreply(Help help) {
        return (Help)this.findForObject("HelpMapper.getUnreply",help);
    }

    @Override
    public List<Help> getReply(Help help) {
        List<Help> helpInfo=null;
        try {
            helpInfo=(List<Help>)this.findForList("HelpMapper.getReply",help);
        }catch (Exception e)
        {e.printStackTrace();}
        return helpInfo;
    }

    @Override
    public int updateHelpInfo(Help help) {
        return this.update("HelpMapper.updateHelpInfo",help);
    }

    @Override
    public Help getFirstHelp(Help help) {
        return (Help) this.findForObject("HelpMapper.getFirstHelp",help);
    }
}