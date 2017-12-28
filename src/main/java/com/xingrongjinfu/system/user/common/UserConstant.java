package com.xingrongjinfu.system.user.common;

/**
 * 用户管理 常量信息
 * 
 * @author y
 */
public class UserConstant
{

    /**
     * 请求地址：首页数据处理
     */
    public final static String INDEX_URL = "index";

    /**
     * 逻辑视图名：首页
     */
    public final static String INDEX_PAGE = "index";

    /**
     * 请求地址：校验验证码
     */
    public final static String JCAPTCHA_URL = "jcaptcha-validate";

    /**
     * 请求地址：登录验证
     */
    public final static String LOGIN_URL = "/{login:login;?.*}";
    
    /**
     * 逻辑视图名：登录页
     */
    public final static String LOGIN_PAGE = "login";
    

    /**
     * 请求地址：跳转至用户查询列表
     */
    public final static String USER_URL = "userView";

    /**
     * 请求地址：跳转至用户查询列表
     */
    public final static String VIEW_URL = "userInfoDeail";

    /**
     * 请求地址：跳转至用户添加界面
     */
    public final static String TO_ADD_URL = "toUserAdd";

    /**
     * 请求地址：跳转至用户修改界面
     */
    public final static String TO_MODIFY_URL = "toUserModify";

    /**
     * 请求地址：跳转至修改密码界面
     */
    public final static String TO_CHANGEPWD_URL = "toChangePwd";

    /**
     * 请求地址：用户列表查询
     */
    public final static String USER_LIST_URL = "userList";

    /**
     * 请求地址：删除用户信息
     */
    public final static String DEL_URL = "deleteUserById";

    /**
     * 请求地址：保存&修改 用户信息
     */
    public final static String SAVE_URL = "saveUser";

    /**
     * 请求地址：启动/停用 用户操作
     */
    public final static String CHANGE_STATUS_URL = "changeUserStatus";

    /**
     * 请求地址：修改密码操作
     */
    public final static String CHANGE_PWD_URL = "changeUserPwd";

    /**
     * 请求地址：检查用户名唯一
     */
    public final static String CHECK_NAME_UNIQUE_URL = "checkNameUnique";
    

    /**
     * 逻辑视图名：用户列表界面
     */
    public final static String USER_PAGE = "system/user-list";

    /**
     * 逻辑视图名：用户详细信息界面
     */
    public final static String VIEW_PAGE = "system/userinfo-deail";

    /**
     * 逻辑视图名：用户添加界面
     */
    public final static String ADD_PAGE = "system/user-add";

    /**
     * 逻辑视图名：用户修改界面
     */
    public final static String MODIFY_PAGE = "system/user-modify";

    /**
     * 逻辑视图名：修改密码界面
     */
    public final static String CHANGE_PWD_PAGE = "system/user-changePwd";

    /**
     * 请求地址:修改密码地址
     */
    public final static String MODIFY_PASSWORD="toModifyPassword";

    /**
     * 逻辑视图名：修改密码界面
     */
    public final static String MODIFY_PWD_PAGE = "modifyPwd";


    /**
     * 请求地址:重置密码地址
     */
    public final static String RESET_PASSWORD="resetPWD";
}
