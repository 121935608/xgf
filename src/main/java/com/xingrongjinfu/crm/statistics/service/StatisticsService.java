package com.xingrongjinfu.crm.statistics.service;

import com.xingrongjinfu.crm.statistics.dao.IStatisticsDao;
import com.xingrongjinfu.crm.statistics.model.Statistics;
import com.xingrongjinfu.system.order.dao.IOrderDao;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
	public Statistics statisticsQuery() {
		Statistics statistics = new Statistics();
		return  statistics;
		//查出所有的订单信息列表
//		List<Order> orderList = orderDao.orderAllList();
//		if(orderList != null && orderList.size() > 0){
//			//判断符合该时间段的统计
//			for (Order order : orderList) {
//				//今日累计下单额
//				if(order.getOrderTime().getTime() <= DateUtil.firstMomentOfDay(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.lastMomentOfDay(order.getOrderTime()).getTime()) {
//					statistics.setTodayAccumulativeAmount(order.getTotalPrice().toString() + statistics.getTodayAccumulativeAmount());
//				}
//
//				//昨日累计下单额
//				if(order.getOrderTime().getTime() <= DateUtil.getStarTimeYesterday(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.getEndTimeYesterday(order.getOrderTime()).getTime()) {
//					statistics.setYesterdayAccumulativeAmount(order.getTotalPrice().toString() + statistics.getYesterdayAccumulativeAmount());
//				}
//
//				//周累计下单额
//				if(order.getOrderTime().getTime() <= DateUtil.getFirstDayOfCurrentWeek(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.getLastDayOfCurrentWeek(order.getOrderTime()).getTime()) {
//					statistics.setWeeklyAccumulativeAmount(order.getTotalPrice().toString() + statistics.getWeeklyAccumulativeAmount());
//				}
//
//				//月累计下单额
//				if(order.getOrderTime().getTime() <= DateUtil.getTimesMonthmorning(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.getTimesMonthnight(order.getOrderTime()).getTime()) {
//					statistics.setMonthlyAccumulativeAmount(order.getTotalPrice().toString() + statistics.getMonthlyAccumulativeAmount());
//				}
//
//				//今日累计成交单量
//				if(order.getOrderTime().getTime() <= DateUtil.firstMomentOfDay(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.lastMomentOfDay(order.getOrderTime()).getTime()) {
//					statistics.setTodayNumberOfTransactions(statistics.getTodayNumberOfTransactions() + 1);
//				}
//
//				//昨日累计成交单量
//				if(order.getOrderTime().getTime() <= DateUtil.getStarTimeYesterday(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.getEndTimeYesterday(order.getOrderTime()).getTime()) {
//					statistics.setYesterdayNumberOfTransactions(statistics.getYesterdayNumberOfTransactions() + 1);
//				}
//
//				//周累计成交单量
//				if(order.getOrderTime().getTime() <= DateUtil.getFirstDayOfCurrentWeek(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.getLastDayOfCurrentWeek(order.getOrderTime()).getTime()) {
//					statistics.setWeeklyNumberOfTransactions(statistics.getWeeklyNumberOfTransactions() + 1);
//				}
//
//				//月累计成交单量
//				if(order.getOrderTime().getTime() <= DateUtil.getTimesMonthmorning(order.getOrderTime()).getTime()
//						&& order.getOrderTime().getTime() >= DateUtil.getTimesMonthnight(order.getOrderTime()).getTime()) {
//					statistics.setMonthlyNumberOfTransactions(statistics.getMonthlyNumberOfTransactions() + 1);
//				}
//			}
//		}
//		return statistics;
	}

}
