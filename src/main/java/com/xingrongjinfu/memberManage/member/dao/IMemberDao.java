/**
 * Copyright (C), 2018
 * FileName: IMemberDao
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

import com.xingrongjinfu.memberManage.member.model.Membership;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/25
 * @since 1.0.0
 */
public interface IMemberDao {

    /**
     * 查询
     * @param pageUtilEntity
     * @return
     */
    List<Membership>pageQueryInfo(PageUtilEntity pageUtilEntity);

    /**
     * 校验用户名
     */
    Membership checkName(Membership membership);

    /**
     * 添加会员
     */
    int addMember(Membership membership);

    /**
     * 查询会员信息
     */
    Membership findMembership(Membership membership);

    /**
     * 更新会员信息
     */
    int updateMember(Membership membership);
    
    int getByName(Map map);
}