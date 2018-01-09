package com.xingrongjinfu.commodity.label.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_category
 * 
 * @author 
 */
public class Label implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String categoryId;
    private String categoryName;
    private String type;
    private String img;
    private int sort;
    private int status;
    private Date addTime;
	private String country;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", type=" + type + ", img="
				+ img + ", sort=" + sort + ", status=" + status + ", addTime=" + addTime + ", country=" + country + "]";
	}

}
