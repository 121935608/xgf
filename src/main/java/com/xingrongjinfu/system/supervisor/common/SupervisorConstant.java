/**
 * Copyright (C), 2017
 * FileName: SupervisorConstant
 * Author:   zxuser
 * Date:     2017/12/29 10:14
 * Description: 督导员管理常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.supervisor.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈督导员管理常量〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
public class SupervisorConstant {

    /**
     * 请求地址:跳转到督导员管理
     */
    public final static String SUPERVISOR_URL="supervisorView";

    /**
     * 逻辑视图名:跳转到督导员管理界面
     */
    public final static String SUPERVISOR_PAGE="system/supervisor-list";

    /**
     * 请求地址:查询督导员信息
     */
    public final static String SUPERVISOR_LIST_URL="supervisorList";

    /**
     * 请求地址:校验手机号唯一
     */
    public final static String  CHECK_PHONE_URL="checkPhoneUnique";

    /**
     * 请求地址:编辑督导员界面地址
     */
    public final static String SUPERVISOR_MODIFY_URL="toSupervisorModify";

    /**
     * 请求地址:编辑督导员地址
     */
    public final static String SUPERVISOR_MODIFY="supervisorModify";
    /**
     * 逻辑视图名:编辑图片的界面
     */
    public final static String SUPERVISOR_MODIFY_PAGE="system/supervisor-modify";

    /**
     * 请求地址:添加督导员地址
     */
    public final static String SUPERVISOR_ADD_URL="toSupervisorAdd";

    /**
     * 逻辑视图名:添加督导员界面
     */
    public final static String SUPERVISOR_ADD_PAGE="system/supervisor-add";

    /**
     * 请求地址:添加督导员
     */
    public final static String SUPERVISOR_ADD="supervisorAdd";

    /**
     * 请求地址:督导员的启用,停用
     */
    public final static String SUPERVISOR_STATUS_CHANGE="changeSupervisorStatus";
}