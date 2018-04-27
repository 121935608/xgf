package com.xingrongjinfu.statistics.procurement.dao;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

/**
 *  数据层
 * 
 * @author 
 */
public interface IProcurementDao
{

	/**
     * 根据条件分页查询
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    List<Map> infoQuery(Map<String, String> param);
}
