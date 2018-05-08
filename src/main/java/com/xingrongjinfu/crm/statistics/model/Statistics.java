package com.xingrongjinfu.crm.statistics.model;


/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 统计数据管理对象
* @date 2018年4月27日
 */
public class Statistics
{
	private Double todayAccumulativeAmount = 0.00;  //今日累计下单额
	
	private Double yesterdayAccumulativeAmount = 0.00;  //昨日累计下单额
	
	private Double weeklyAccumulativeAmount = 0.00;  //周累计下单额
	
	private Double monthlyAccumulativeAmount = 0.00;  //月累计下单额
	
	private Integer todayNumberOfTransactions = 0;  //今日累计成交单量
	
	private Integer yesterdayNumberOfTransactions = 0; //昨日累计成交单量
	
	private Integer weeklyNumberOfTransactions = 0;  //周累计成交单量
	
	private Integer monthlyNumberOfTransactions = 0;  //月累计成交单量
	
	private String todayCustomerConversionRate;  //今日下单客户转化率
	
	private String yesterdayCustomerConversionRate;  //昨日下单客户转化率
	
	private String weeklyCustomerConversionRate;  //周下单客户转化率
	
	private String monthlyCustomerConversionRate;  //月下单客户转化率
	
	private String todayStoreCouponRate;  //今日门店补券率
	
	private String yesterdayStoreCouponRate;  //昨日门店补券
	
	private String weeklyStoreCouponRate;  //周门店补券率
	
	private String monthlyStoreCouponRate;  //月门店补券率

	

	public Double getTodayAccumulativeAmount() {
		return todayAccumulativeAmount;
	}

	public void setTodayAccumulativeAmount(Double todayAccumulativeAmount) {
		this.todayAccumulativeAmount = todayAccumulativeAmount;
	}

	public Double getYesterdayAccumulativeAmount() {
		return yesterdayAccumulativeAmount;
	}

	public void setYesterdayAccumulativeAmount(Double yesterdayAccumulativeAmount) {
		this.yesterdayAccumulativeAmount = yesterdayAccumulativeAmount;
	}

	public Double getWeeklyAccumulativeAmount() {
		return weeklyAccumulativeAmount;
	}

	public void setWeeklyAccumulativeAmount(Double weeklyAccumulativeAmount) {
		this.weeklyAccumulativeAmount = weeklyAccumulativeAmount;
	}

	public Double getMonthlyAccumulativeAmount() {
		return monthlyAccumulativeAmount;
	}

	public void setMonthlyAccumulativeAmount(Double monthlyAccumulativeAmount) {
		this.monthlyAccumulativeAmount = monthlyAccumulativeAmount;
	}

	public Integer getTodayNumberOfTransactions() {
		return todayNumberOfTransactions;
	}

	public void setTodayNumberOfTransactions(Integer todayNumberOfTransactions) {
		this.todayNumberOfTransactions = todayNumberOfTransactions;
	}

	public Integer getYesterdayNumberOfTransactions() {
		return yesterdayNumberOfTransactions;
	}

	public void setYesterdayNumberOfTransactions(
			Integer yesterdayNumberOfTransactions) {
		this.yesterdayNumberOfTransactions = yesterdayNumberOfTransactions;
	}

	public Integer getWeeklyNumberOfTransactions() {
		return weeklyNumberOfTransactions;
	}

	public void setWeeklyNumberOfTransactions(Integer weeklyNumberOfTransactions) {
		this.weeklyNumberOfTransactions = weeklyNumberOfTransactions;
	}

	public Integer getMonthlyNumberOfTransactions() {
		return monthlyNumberOfTransactions;
	}

	public void setMonthlyNumberOfTransactions(Integer monthlyNumberOfTransactions) {
		this.monthlyNumberOfTransactions = monthlyNumberOfTransactions;
	}

	public String getTodayCustomerConversionRate() {
		return todayCustomerConversionRate;
	}

	public void setTodayCustomerConversionRate(String todayCustomerConversionRate) {
		this.todayCustomerConversionRate = todayCustomerConversionRate;
	}

	public String getYesterdayCustomerConversionRate() {
		return yesterdayCustomerConversionRate;
	}

	public void setYesterdayCustomerConversionRate(
			String yesterdayCustomerConversionRate) {
		this.yesterdayCustomerConversionRate = yesterdayCustomerConversionRate;
	}

	public String getWeeklyCustomerConversionRate() {
		return weeklyCustomerConversionRate;
	}

	public void setWeeklyCustomerConversionRate(String weeklyCustomerConversionRate) {
		this.weeklyCustomerConversionRate = weeklyCustomerConversionRate;
	}

	public String getMonthlyCustomerConversionRate() {
		return monthlyCustomerConversionRate;
	}

	public void setMonthlyCustomerConversionRate(
			String monthlyCustomerConversionRate) {
		this.monthlyCustomerConversionRate = monthlyCustomerConversionRate;
	}

	public String getTodayStoreCouponRate() {
		return todayStoreCouponRate;
	}

	public void setTodayStoreCouponRate(String todayStoreCouponRate) {
		this.todayStoreCouponRate = todayStoreCouponRate;
	}

	public String getYesterdayStoreCouponRate() {
		return yesterdayStoreCouponRate;
	}

	public void setYesterdayStoreCouponRate(String yesterdayStoreCouponRate) {
		this.yesterdayStoreCouponRate = yesterdayStoreCouponRate;
	}

	public String getWeeklyStoreCouponRate() {
		return weeklyStoreCouponRate;
	}

	public void setWeeklyStoreCouponRate(String weeklyStoreCouponRate) {
		this.weeklyStoreCouponRate = weeklyStoreCouponRate;
	}

	public String getMonthlyStoreCouponRate() {
		return monthlyStoreCouponRate;
	}

	public void setMonthlyStoreCouponRate(String monthlyStoreCouponRate) {
		this.monthlyStoreCouponRate = monthlyStoreCouponRate;
	}
	
	
}
