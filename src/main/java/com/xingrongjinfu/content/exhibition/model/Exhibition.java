package com.xingrongjinfu.content.exhibition.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_exhibition
 * 
 * @author
 */
public class Exhibition implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer exhibitionNum;
	private String exhibitionId;
	private String categoryName;
	private String categoryId;
	private String exhibitionImg;
	private Date createTime;
	private Date updateTime;
	private int status;
	private String url;	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getExhibitionNum() {
		return exhibitionNum;
	}
	public void setExhibitionNum(Integer exhibitionNum) {
		this.exhibitionNum = exhibitionNum;
	}
	public String getExhibitionId() {
		return exhibitionId;
	}
	public void setExhibitionId(String exhibitionId) {
		this.exhibitionId = exhibitionId;
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
	public String getExhibitionImg() {
		return exhibitionImg;
	}
	public void setExhibitionImg(String exhibitionImg) {
		this.exhibitionImg = exhibitionImg;
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
		return "Exhibition [exhibitionId=" + exhibitionId + ", categoryName=" + categoryName + ", categoryId="
				+ categoryId + ", exhibitionImg=" + exhibitionImg + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", status=" + status + "]";
	}
}
