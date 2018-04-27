package com.xingrongjinfu.statistics.pays.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

/**
 *  业务层
 * 
 * @author 
 */
public interface IPayService
{

	/**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);


    List<Map> infoQuery(Map<String, String> param);
}
