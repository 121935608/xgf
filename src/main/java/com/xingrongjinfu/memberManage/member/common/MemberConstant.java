/**
 * Copyright (C), 2018
 * FileName: MemberConstant
 * Author:   zxuser
 * Date:     2018/1/25 14:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.memberManage.member.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/25
 * @since 1.0.0
 */
public class MemberConstant {

    /**
     * 请求地址:跳转到会员资料
     */
    public final static String MEMBERSHIP_URL="membershipView";

    /**
     * 逻辑视图名:会员资料界面
     */
    public final static String MEMBERSHIP_PAGE="member/membership-list";

    /**
     * 请求地址:加载会员资料信息
     */
    public final static String MEMBERSHIP_LIST_URL="membershipList";

    /**
     * 请求地址:添加会员资料
     */
    public final static String MEMBERSHIP_ADD_URL="toMmeberAdd";

    /**
     * 逻辑视图名:添加会员资料界面
     */
    public final static String MEMBERSHIP_ADD_PAGE="member/membership-add";
    /**
     * 请求地址:校验会员名是否唯一
     */
    public final static String MEMBERSHIP_CHECK_NAME_URL="checkNameUnique";

    /**
     * 请求地址:更新会员信息
     */
    public final static String MEMBERSHIP_SAVE_URL="saveMember";
}