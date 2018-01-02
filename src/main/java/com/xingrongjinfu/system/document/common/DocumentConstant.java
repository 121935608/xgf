/**
 * Copyright (C), 2018
 * FileName: DocumentConstant
 * Author:   zxuser
 * Date:     2018/1/2 10:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.document.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
public class DocumentConstant {

    /**
     * 请求地址:跳转到文件管理地址
     */
    public final static String DOCUMENT_URL="documentView";

    /**
     * 逻辑视图名:文件管理界面
     */
    public final static String DOCUMENT_PAGE="system/document-list";

    /**
     * 请求地址:查询所有公文列表
     */
    public final static String DOCUMENT_LIST_URL="documentList";

    /**
     * 请求地址:跳转到添加公文地址
     */
    public final static String DOCUMENT_ADD_URL="toDocumentAdd";

    /**
     * 逻辑视图名:添加文件管理的界面
     */
    public final static String DOCUMENT_ADD_PAGE="system/document-add";

    /**
     * 请求地址:保存文件
     */
    public final static String DOCUMENT_ADD="saveDocument";

    /**
     * 请求地址:跳转到文件修改地址
     */
    public final static String DOCUMENT_MODIFY_URL="toDocumentModify";

    /**
     * 逻辑视图名:修改界面
     */
    public final static String DOCUMENT_MODIFY_PAGE="system/document-modify";

    /**
     *请求地址:修改文件信息
     */
    public final static String DOCUMENT_MODIFY="modifyDocument";
    /**
     * 请求地址:文件的启用与禁用
     */
    public final static String DOCUMENT_CHANGESTATUS_URL="changeDocumentStatus";

    /**
     * 常量:图片地址
     */
    public final static String URL="xrjf/xgf/";
}