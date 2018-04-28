/**
 * Copyright (C), 2018
 * FileName: FinancialConstant
 * Author:   zxuser
 * Date:     2018/1/3 14:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.common;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public class FinancialConstant {

    /**
     * 请求地址:跳转到财务结算
     */
    public final static String FINANCIAL_URL="financialView";

    /**
     * 逻辑视图名:财务结算界面
     */
    public final static String FINANCIAL_PAGE="dataCount/financial-list";
    /**
     * 逻辑视图名:财务结算对账界面
     */
    public final static String FINANCIAL_MODIFY_PAGE="dataCount/financial-modify";

    /**
     * 请求地址:查询财务信息
     */

    public final static String FINANCIAL_LIST_URL="findFinancialList";
    public final static String DOWNLOAD_FINANCIAL_DATA="downloadFinancialData";
    /**
     * 请求地址:跳转对账页面
     */
    public final static String FINANCIAL_MODIFY_URL="toFinancialModify";
    /**
     * 请求地址:修改财务信息
     */
    public final static String FINANCIAL_MODIFY="modifyFinancial";
    /**
     * 请求地址:查询财务信息明细
     */
    public final static String GET_DETAIL="getDetail";
}