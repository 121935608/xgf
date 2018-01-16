package com.xingrongjinfu.content.commodityAd.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_commodityAd
 * 
 * @author
 */
public class CommodityAd implements Serializable {
	private static final long serialVersionUID = 1L;

	private String commodityAdId;
	private String commodityAdName;
	private String commodityId;
	private String commodityAdImg;
	private String url;
	private int type;
	private Date createTime;
	private Date updateTime;
	private int status;
	private String commodityDes;
	private int floor;
	private int sort;
	private String categoryId;
	private String categoryName;
	private String commodityName;
	
	
	public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCommodityAdId() {
		return commodityAdId;
	}
	public void setCommodityAdId(String commodityAdId) {
		this.commodityAdId = commodityAdId;
	}
	public String getCommodityAdName() {
		return commodityAdName;
	}
	public void setCommodityAdName(String commodityAdName) {
		this.commodityAdName = commodityAdName;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityAdImg() {
		return commodityAdImg;
	}
	public void setCommodityAdImg(String commodityAdImg) {
		this.commodityAdImg = commodityAdImg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCommodityDes() {
		return commodityDes;
	}
	public void setCommodityDes(String commodityDes) {
		this.commodityDes = commodityDes;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "CommodityAd [commodityAdId=" + commodityAdId + ", commodityAdName=" + commodityAdName + ", commodityId="
				+ commodityId + ", commodityAdImg=" + commodityAdImg + ", url=" + url + ", type=" + type
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", status=" + status + ", commodityDes="
				+ commodityDes  + ", sort=" + sort + "]";
	}	
}
