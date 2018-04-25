package com.xingrongjinfu.content.carousel.common;

/**
 * 内容管理 常量信息
 * 
 * @author
 */
public class CarouselConstant
{

    /**
     * 请求地址：跳转至查询列表
     */
    public final static String CAROUSEL_URL = "carouselView";

    /**
     * 请求地址：跳转至添加界面
     */
    public final static String TO_ADD_URL = "toCarouselAdd";

    /**
     * 请求地址：商品树
     */
    public final static String PRODUCTTREE_URL="getProductTree";

    /**
     * 请求地址：商品树
     */
    public final static String COMMODITY_URL="getCommodityTree";
    
    /**
     * 请求地址：跳转至修改界面
     */
    public final static String TO_MODIFY_URL = "toCarouselModify";

    /**
     * 请求地址：列表查询
     */
    public final static String CAROUSEL_LIST_URL = "carouselList";
 
    /**
     * 请求地址：保存&修改
     */
    public final static String SAVE_CAROUSEL_URL = "saveCarousel";
    
    /**
     * 请求地址：启动/停用 操作
     */
    public final static String CHANGE_STATUS_URL = "changeCarouselStatus";
    
    /**
     * 请求地址：删除信息
     */
    public final static String DEL_URL = "deleteCarouselById";


    /**
     * 逻辑视图名：跳转至列表界面
     */
    public final static String CAROUSEL_PAGE = "content/carousel-list";

    /**
     * 逻辑视图名：添加界面
     */
    public final static String ADD_PAGE = "content/carousel-add";
    
    /**
     * 逻辑视图名：修改界面
     */
    public final static String MODIFY_PAGE = "content/carousel-modify";
    
    /**
     * 请求地址：检查唯一
     */
    public final static String CHECK_NAME_UNIQUE_URL = "checkCarouselNameUnique";
    
    /** 名称是否唯一的返回结果码 */
    public final static String NAME_UNIQUE = "0";
    public final static String NAME_NOT_UNIQUE = "1";
    
}
