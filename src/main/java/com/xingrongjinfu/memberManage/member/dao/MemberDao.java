/**
 * Copyright (C), 2018
 * FileName: MemberDao
 * Author:   zxuser
 * Date:     2018/1/25 14:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.memberManage.member.dao;

import com.xingrongjinfu.memberManage.member.model.Membership;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/25
 * @since 1.0.0
 */
@Repository("memberDao")
public class MemberDao extends DynamicObjectBaseDao implements IMemberDao {


    @Override
    public List<Membership> pageQueryInfo(PageUtilEntity pageUtilEntity) {
        List<Membership> pageQueryInfo=null;
        try{
           pageQueryInfo=(List<Membership>)this.findForList("MemberMapper.pageInfoQuery",pageUtilEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageQueryInfo;
    }

    @Override
    public Membership checkName(Membership membership) {
        return (Membership) this.findForObject("MemberMapper.checkName",membership);
    }
}