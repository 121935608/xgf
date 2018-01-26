/**
 * Copyright (C), 2018
 * FileName: IMemberService
 * Author:   zxuser
 * Date:     2018/1/25 14:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.memberManage.member.service;

import com.xingrongjinfu.memberManage.member.model.Membership;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/25
 * @since 1.0.0
 */
public interface IMemberService {

    /**
     *
     * @param pageUtilEntity
     * @return
     */
    List<Membership>pageQueryInfo(PageUtilEntity pageUtilEntity);

    /**
     * 校验用户名
     */
    String checkName(Membership membership);

    /**
     * 添加会员
     */
    int addMember(Membership membership);

    /**
     * 查询会员资料
     */
    Membership findMembership(Membership membership);

    /**
     * 更新会员资料
     */
    int updateMember(Membership membership);
}
