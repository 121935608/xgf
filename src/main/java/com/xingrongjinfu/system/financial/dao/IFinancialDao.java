/**
 * Copyright (C), 2018
 * FileName: IFinancialsDao
 * Author:   zxuser
 * Date:     2018/1/3 14:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.model.FinancialDetail;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public interface IFinancialDao {

    /**
     * 查询财务信息
     * @param pageUtilEntity
     * @return
     */
    List<TableDataInfo>pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 更新账户信息
     */
    int updateAmountInfo(Financial financial);
    
    Financial getByNum(String id);
    
    List<FinancialDetail> getDetail(String id);
    
    int updateAmount(Financial financial);
    
    int addAmountDetail(FinancialDetail financialDetail);
}