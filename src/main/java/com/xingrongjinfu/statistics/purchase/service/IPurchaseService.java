package com.xingrongjinfu.statistics.purchase.service;

import com.xingrongjinfu.statistics.purchase.model.SPurchaseDto;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 *  业务层
 * 
 * @author 
 */
public interface IPurchaseService
{

	/**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    List<SPurchaseDto> infoQuery(Map<String, String> param);
}
