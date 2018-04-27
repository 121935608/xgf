package com.xingrongjinfu.statistics.procurement.service;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 *  业务层
 * 
 * @author 
 */
public interface IProcurementService
{

	/**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);


    List<Map> infoQuery(Map<String, String> param);
}
