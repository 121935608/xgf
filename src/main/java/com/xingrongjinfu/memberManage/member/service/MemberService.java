/**
 * Copyright (C), 2018
 * FileName: MemberService
 * Author:   zxuser
 * Date:     2018/1/25 14:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.memberManage.member.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.common.UserConstants;
import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.memberManage.member.dao.IMemberDao;
import com.xingrongjinfu.memberManage.member.model.Membership;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/25
 * @since 1.0.0
 */
@Service("memberService")
public class MemberService implements IMemberService {

    @Autowired
    private IMemberDao memberDao;

    @Override
    public List<Membership> pageQueryInfo(PageUtilEntity pageUtilEntity) {
        return memberDao.pageQueryInfo(pageUtilEntity);
    }

    @Override
    public String checkName(Membership membership) {
        Membership newMembership =memberDao.checkName(membership);
        if (ObjectUtil.isNotNull(newMembership)){
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }

    @Override
    public int addMember(Membership membership) {
        return memberDao.addMember(membership);
    }

    @Override
    public Membership findMembership(Membership membership) {
        return memberDao.findMembership(membership);
    }

    @Override
    public int updateMember(Membership membership) {
        return memberDao.updateMember(membership);
    }

    @Override
    public int getByName(Map map) {
        return memberDao.getByName(map);
    }
}