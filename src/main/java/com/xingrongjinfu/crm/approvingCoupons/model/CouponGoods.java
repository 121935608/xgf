package com.xingrongjinfu.crm.approvingCoupons.model;


/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 补券商品管理对象
* @date 2018年5月23日
 */
public class CouponGoods
{
	private Integer couponGoodsId;
    private String couponId;
    private String commodityName;
    private String subjectivePrice;
    private String weight;
    private String couponTotal;
	public Integer getCouponGoodsId() {
		return couponGoodsId;
	}
	public void setCouponGoodsId(Integer couponGoodsId) {
		this.couponGoodsId = couponGoodsId;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getSubjectivePrice() {
		return subjectivePrice;
	}
	public void setSubjectivePrice(String subjectivePrice) {
		this.subjectivePrice = subjectivePrice;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCouponTotal() {
		return couponTotal;
	}
	public void setCouponTotal(String couponTotal) {
		this.couponTotal = couponTotal;
	}
    
}
