package com.xingrongjinfu.crm.statistics.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.crm.statistics.model.Statistics;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 统计数据查询管理   业务层
* @date 2018年4月25日
 */
public interface IStatisticsService
{

	/**
	 * 统计数据查询
	 * 
	 * @return: Statistics      
	 */
	Statistics statisticsQuery();
    
}
