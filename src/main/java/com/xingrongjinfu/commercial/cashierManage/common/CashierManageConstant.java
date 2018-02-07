package com.xingrongjinfu.commercial.cashierManage.common;

/**
 * 收银员管理 常量信息
 * 
 * @author 
 */
public class CashierManageConstant
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
     * 请求地址：跳转至查询列表
     */
    public final static String CASHIER_URL = "cashierManageView";
    
    /**
     * 请求地址：跳转至添加界面
     */
    public final static String TO_ADD_URL = "toCashierManageAdd";
    /**
     * 请求地址：跳转至修改界面
     */
    public final static String TO_MODIFY_URL = "toCashierManageModify";

    /**
     * 请求地址：列表查询
     */
    public final static String CASHIER_LIST_URL = "cashierManageList";
    
    /**
     * 请求地址：保存&修改 菜单信息
     */
    public final static String SAVE_CASHIER_URL = "saveCashierManage";
    
    /**
     * 请求地址：启动/停用 操作
     */
    public final static String CHANGE_STATUS_URL = "changeCashierManageStatus";
    /**
     * 请求地址：修改收银员
     */
    public final static String MODIFY_URL = "modifyCashierManage";
    
    /**
     * 逻辑视图名：列表界面
     */
    public final static String CASHIER_PAGE = "commercial/cashierManage-list";
    
    /**
     * 逻辑视图名：添加界面
     */
    public final static String ADD_PAGE = "commercial/cashierManage-add";
    /**
     * 逻辑视图名：修改界面
     */
    public final static String MODIFY_PAGE = "commercial/cashierManage-modify";
    
    /**
     * 请求地址：检查唯一
     */
    public final static String CHECK_NAME_UNIQUE_URL = "checkCashierManageNameUnique";
    
    /** 名称是否唯一的返回结果码 */
    public final static String NAME_UNIQUE = "0";
    public final static String NAME_NOT_UNIQUE = "1";
   
}
