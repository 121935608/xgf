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

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.memberManage.member.model.Membership;

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

    @Override
    public int addMember(Membership membership) {
        return (int)this.save("MemberMapper.addMember",membership);
    }

    @Override
    public Membership findMembership(Membership membership) {
        return (Membership)this.findForObject("MemberMapper.findMembership",membership);
    }

    @Override
    public int updateMember(Membership membership) {
        return (int)this.update("MemberMapper.updateMember",membership);
    }

    @Override
    public int getByName(Map map) {
        
        return (int) this.findForObject("MemberMapper.getByName", map);
    }
}