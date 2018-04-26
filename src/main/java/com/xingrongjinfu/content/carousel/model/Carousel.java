package com.xingrongjinfu.content.carousel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_carousel
 * 
 * @author
 */
public class Carousel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String carouselId;
	private String carouselName;
	private int sort;
	private String carouselImg;
	private int visitNum;
	private Date createTime;
	private Date updateTime;
	private int status;
	private String url;
	private String carouselNum;
	private String type;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCarouselNum() {
		return carouselNum;
	}
	public void setCarouselNum(String carouselNum) {
		this.carouselNum = carouselNum;
	}
	public String getCarouselId() {
		return carouselId;
	}
	public void setCarouselId(String carouselId) {
		this.carouselId = carouselId;
	}
	public String getCarouselName() {
		return carouselName;
	}
	public void setCarouselName(String carouselName) {
		this.carouselName = carouselName;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getCarouselImg() {
		return carouselImg;
	}
	public void setCarouselImg(String carouselImg) {
		this.carouselImg = carouselImg;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Carousel [carouselId=" + carouselId + ", carouselName=" + carouselName + ", sort=" + sort
				+ ", carouselImg=" + carouselImg + ", visitNum=" + visitNum + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", status=" + status + ", url=" + url + ", carouselNum=" + carouselNum
				+ "]";
	}
	
}
