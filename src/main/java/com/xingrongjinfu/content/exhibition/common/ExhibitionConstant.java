package com.xingrongjinfu.content.exhibition.common;

/**
 * 内容管理 常量信息
 * 
 * @author
 */
public class ExhibitionConstant
{

    /**
     * 请求地址：跳转至查询列表
     */
    public final static String EXHIBITION_URL = "exhibitionView";

    /**
     * 请求地址：跳转至添加界面
     */
    public final static String TO_ADD_URL = "toExhibitionAdd";
    
    /**
     * 请求地址：跳转至修改界面
     */
    public final static String TO_MODIFY_URL = "toExhibitionModify";

    /**
     * 请求地址：列表查询
     */
    public final static String EXHIBITION_LIST_URL = "exhibitionList";
 
    /**
     * 请求地址：保存&修改
     */
    public final static String SAVE_EXHIBITION_URL = "saveExhibition";
    
    /**
     * 请求地址：删除信息
     */
    public final static String DEL_URL = "deleteExhibitionById";
    
    /**
     * 逻辑视图名：跳转至列表界面
     */
    public final static String EXHIBITION_PAGE = "content/exhibition-list";

    /**
     * 逻辑视图名：添加界面
     */
    public final static String ADD_PAGE = "content/exhibition-add";
    
    /**
     * 逻辑视图名：修改界面
     */
    public final static String MODIFY_PAGE = "content/exhibition-modify";
    
    /**
     * 请求地址：检查唯一
     */
    public final static String CHECK_NAME_UNIQUE_URL = "checkExhibitionNameUnique";
    
    /** 名称是否唯一的返回结果码 */
    public final static String NAME_UNIQUE = "0";
    public final static String NAME_NOT_UNIQUE = "1";
    
    
}
