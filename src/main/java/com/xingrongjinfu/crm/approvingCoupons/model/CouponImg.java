package com.xingrongjinfu.crm.approvingCoupons.model;


/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 优惠券图片管理对象
* @date 2018年4月25日
 */
public class CouponImg
{
	private String imgId;
    private String couponId;
    private String imgUrl;
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
    
}
