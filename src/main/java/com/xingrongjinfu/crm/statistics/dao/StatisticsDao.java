package com.xingrongjinfu.crm.statistics.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.system.order.model.Order;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 统计数据查询管理  数据层
* @date 2018年4月27日
 */
@Repository("statisticsDao")
public class StatisticsDao extends DynamicObjectBaseDao implements IStatisticsDao
{

	@Override
	public String customerConversionRate(Date timeBegin, Date timeEnd) {
		HashMap map = new HashMap();
		map.put("timeBegin", timeBegin);
		map.put("timeEnd", timeEnd);
		return  (String) this.findForObject("CrmStatisticsMapper.customerConversionRate",map);
	}

	@Override
	public HashMap storeCouponRate(HashMap map) {
		return (HashMap) this.findForObject("CrmStatisticsMapper.storeCouponRate",map);
	}
}
