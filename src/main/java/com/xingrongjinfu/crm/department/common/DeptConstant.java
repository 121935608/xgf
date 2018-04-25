package com.xingrongjinfu.crm.department.common;

/**
 * 
* @author chenmengzhen    
* @version V1.0  
* @Description: 部门管理 常量信息 
* @date 2018年4月25日
 */
public class DeptConstant
{
    /**
     * 请求地址：跳转至部门查询列表
     */
    public final static String DEPT_URL = "deptView";

    /**
     * 请求地址：跳转至部门添加界面
     */
    public final static String TO_ADD_URL = "toDeptAdd";
    
    /**
     * 请求地址：跳转至部门修改界面
     */
    public final static String TO_UPDATE_URL = "toDeptUpdate";

    /**
     * 请求地址：部门列表查询
     */
    public final static String DEPT_LIST_URL = "deptList";
    
    /**
     * 请求地址：删除部门信息
     */
    public final static String DEL_URL = "deleteDeptById";
    
    /**
     * 请求地址：保存&修改 部门信息
     */
    public final static String SAVE_DEPT_URL = "saveDept";

    /**
     * 逻辑视图名：跳转至部门列表界面
     */
    public final static String DEPT_PAGE = "xfsxCRM/dept-list";

    /**
     * 逻辑视图名：部门添加界面
     */
    public final static String ADD_PAGE = "xfsxCRM/dept-add";
    
    /**
     * 逻辑视图名：部门修改界面
     */
    public final static String UPDATE_PAGE = "xfsxCRM/dept-modify";
    
}
