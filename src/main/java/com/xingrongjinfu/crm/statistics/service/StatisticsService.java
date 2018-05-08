package com.xingrongjinfu.crm.statistics.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.crm.statistics.dao.IStatisticsDao;
import com.xingrongjinfu.crm.statistics.model.Statistics;
import com.xingrongjinfu.system.order.dao.IOrderDao;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.utils.DateUtils;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 统计数据查询管理  业务层处理 
* @date 2018年4月27日
 */
@Service("statisticsService")
public class StatisticsService implements IStatisticsService
{

    @Autowired
    private IStatisticsDao statisticsDao;

    @Autowired
    private IOrderDao orderDao;
	
    /**
	 * 统计数据查询
	 * 
	 * @return: Statistics      
	 */
	public Statistics statisticsQuery() {
		Statistics statistics = new Statistics();
		
		//时间段
		Date todayBegin = DateUtils.firstMomentOfDay(new Date());
		Date todayEnd = DateUtils.lastMomentOfDay(new Date());
		Date yesterdayBegin = DateUtils.getStarTimeYesterday(new Date());
		Date yesterdayEnd = DateUtils.getEndTimeYesterday(new Date());
		Date weeklyBegin = DateUtils.getFirstDayOfCurrentWeek(new Date());
		Date weeklyEnd = DateUtils.getLastDayOfCurrentWeek(new Date());
		Date monthlyBegin = DateUtils.getTimesMonthmorning(new Date());
		Date monthlyEnd = DateUtils.getTimesMonthnight(new Date());
		
		
		//查出所有的订单信息列表
		List<Order> orderList = orderDao.orderAllList();
		if(orderList != null && orderList.size() > 0){
			//判断符合该时间段的统计
			for (Order order : orderList) {
				if(order.getOrderTime().after(todayBegin) && order.getOrderTime().before(todayEnd) ) {
					//今日累计下单额
					statistics.setTodayAccumulativeAmount(order.getTotalPrice() + statistics.getTodayAccumulativeAmount());
					//今日累计成交单量
					statistics.setTodayNumberOfTransactions(statistics.getTodayNumberOfTransactions() + 1);
				}
				
				if(order.getOrderTime().after(yesterdayBegin) && order.getOrderTime().before(yesterdayEnd) ) {
					//昨日累计下单额
					statistics.setYesterdayAccumulativeAmount(order.getTotalPrice() + statistics.getYesterdayAccumulativeAmount());
					//昨日累计成交单量
					statistics.setYesterdayNumberOfTransactions(statistics.getYesterdayNumberOfTransactions() + 1);
				}
				
				if(order.getOrderTime().after(weeklyBegin) && order.getOrderTime().before(weeklyEnd) ) {
					//周累计下单额
					statistics.setWeeklyAccumulativeAmount(order.getTotalPrice() + statistics.getWeeklyAccumulativeAmount());
					//周累计成交单量
					statistics.setWeeklyNumberOfTransactions(statistics.getWeeklyNumberOfTransactions() + 1);
				}
				
				if(order.getOrderTime().after(monthlyBegin) && order.getOrderTime().before(monthlyEnd) ){
					//月累计下单额
					statistics.setMonthlyAccumulativeAmount(order.getTotalPrice() + statistics.getMonthlyAccumulativeAmount());
					//月累计成交单量
					statistics.setMonthlyNumberOfTransactions(statistics.getMonthlyNumberOfTransactions() + 1);
				}
			}
		}
		
		
		//今日下单客户转化率
		statistics.setTodayCustomerConversionRate(statisticsDao.customerConversionRate(todayBegin,todayEnd));
		//昨日下单客户转化率
		statistics.setYesterdayCustomerConversionRate(statisticsDao.customerConversionRate(yesterdayBegin,yesterdayEnd));
		//周下单客户转化率
		statistics.setWeeklyCustomerConversionRate(statisticsDao.customerConversionRate(weeklyBegin,weeklyEnd));
		//月下单客户转化率
		statistics.setMonthlyCustomerConversionRate(statisticsDao.customerConversionRate(monthlyBegin,monthlyEnd));
		
		//门店补券率
		HashMap map = new HashMap();
		map.put("timeBegin", todayBegin);
		map.put("timeEnd", todayEnd);
		map.put("timeBegin1", yesterdayBegin);
		map.put("timeEnd1", yesterdayEnd);
		map.put("timeBegin2", weeklyBegin);
		map.put("timeEnd2", weeklyEnd);
		map.put("timeBegin3", monthlyBegin);
		map.put("timeEnd3", monthlyEnd);
		
		HashMap storeCouponRate = statisticsDao.storeCouponRate(map);
		
		statistics.setTodayStoreCouponRate(storeCouponRate.get("today").toString());
		statistics.setYesterdayStoreCouponRate(storeCouponRate.get("yesterday").toString());
		statistics.setWeeklyStoreCouponRate(storeCouponRate.get("weekly").toString());
		statistics.setMonthlyStoreCouponRate(storeCouponRate.get("monthly").toString());
		
		
		return statistics;
	}

}
