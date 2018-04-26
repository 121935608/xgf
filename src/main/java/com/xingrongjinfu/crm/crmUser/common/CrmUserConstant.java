package com.xingrongjinfu.crm.crmUser.common;

/**
 * 角色管理 常量信息
 * 
 * @author y
 */
public class CrmUserConstant
{
    /**
     * 请求地址：跳转至用户查询列表
     */
    public final static String USER_URL = "userView";

    /**
     * 请求地址：用户查询列表
     */
    public final static String USER_CRM_URL = "userCRMView";
    /**
     * 请求地址：修改督导员状态
     */
    public final static String USER_CRM_UPDATE_STATUS = "updateSupervistorStatusView";
    /**
     * 请求地址：根据ID查询督导员信息
     */
    public final static String USER_CRM_SELECT_URL = "updateSpervistorInfoView";
    /**
     * 请求地址：根据督导员ID修改督导员信息
     */
    public final static String USER_CRM_UPDATE_URL = "updateSpervistorIDView";
    /**
     * 请求地址：增加督导员信息
     */
    public final static String USER_CRM_ADD_URL = "addSpervistorInfoView";

    /**
     * 请求地址：查询所有部门接口
     */
    public final static String USER_CRM_SELECT_DEPT = "selectDeptInfoView";

    /**
     * 请求地址：增加督导员信息接口
     */

    public final static String USER_CRM_ADD_URL_TWO = "toAddSpervistorInfoView";

    /**
     * 逻辑视图名：跳转至增加督导员界面
     */
    public  final static  String USER_CRM_ADD_PAGE = "xfsxCRM/user-add";

    /**
     * 逻辑视图名：跳转至编辑修改督导员界面
     */
    public  final static  String USER_CRM_UPDATE_PAGE = "xfsxCRM/user-update";
    /**
     * 逻辑视图名：跳转至用户列表界面
     */
    public final static String USER_PAGE = "xfsxCRM/user-list";
}
