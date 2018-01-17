package com.xingrongjinfu.commodity.label.common;

/**
 * 商品管理 常量信息
 * 
 * @author 
 */
public class LabelConstant
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
    public final static String LABEL_URL = "labelView";

    /**
     * 请求地址：跳转至添加界面
     */
    public final static String TO_ADD_URL = "toLabelAdd";

    /**
     * 请求地址：跳转至修改界面
     */
    public final static String TO_MODIFY_URL = "toLabelModify";  

    /**
     * 请求地址：列表查询
     */
    public final static String LABEL_LIST_URL = "labelList";

    /**
     * 请求地址：保存&修改 信息
     */
    public final static String SAVE_URL = "saveLabel";
    
    /**
     * 请求地址：删除信息
     */
    public final static String DEL_URL = "deleteLabelById";
    
    /**
     * 请求地址：检查唯一
     */
    public final static String CHECK_NAME_UNIQUE_URL = "checkNamesUnique";

    /**
     * 请求地址：启动/停用 操作
     */
    public final static String CHANGE_STATUS_URL = "changeLabelStatus";


    /**
     * 逻辑视图名：列表界面
     */
    public final static String LABEL_PAGE = "commodity/label-list";


    /**
     * 逻辑视图名：添加界面
     */
    public final static String ADD_PAGE = "commodity/label-add";

    /**
     * 逻辑视图名：修改界面
     */
    public final static String MODIFY_PAGE = "commodity/label-modify";
    
    /** 名称是否唯一的返回结果码 */
    public final static String NAME_UNIQUE = "0";
    public final static String NAME_NOT_UNIQUE = "1";

}
