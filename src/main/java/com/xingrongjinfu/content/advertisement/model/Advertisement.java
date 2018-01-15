package com.xingrongjinfu.content.advertisement.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_advertisement
 * 
 * @author
 */
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;

	private String advertisementId;
	private String advertisementImg;
	private String advertisementName;
	private String advertisementUrl;
	private Date createTime;
	private Date updateTime;
	private int status;
	private int advertisementNum;
	
	public int getAdvertisementNum() {
		return advertisementNum;
	}
	public void setAdvertisementNum(int advertisementNum) {
		this.advertisementNum = advertisementNum;
	}
	public String getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getAdvertisementImg() {
		return advertisementImg;
	}
	public void setAdvertisementImg(String advertisementImg) {
		this.advertisementImg = advertisementImg;
	}
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public String getAdvertisementUrl() {
		return advertisementUrl;
	}
	public void setAdvertisementUrl(String advertisementUrl) {
		this.advertisementUrl = advertisementUrl;
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
	@Override
	public String toString() {
		return "Advertisement [advertisementId=" + advertisementId + ", advertisementImg=" + advertisementImg
				+ ", advertisementName=" + advertisementName + ", advertisementUrl=" + advertisementUrl
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", status=" + status + "]";
	}
	
}
