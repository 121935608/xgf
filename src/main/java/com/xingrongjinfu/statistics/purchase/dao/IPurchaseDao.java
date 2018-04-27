package com.xingrongjinfu.statistics.purchase.dao;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 *  数据层
 * 
 * @author 
 */
public interface IPurchaseDao
{
	/**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);


    List<Map> infoQuery(Map<String, String> param);
}
