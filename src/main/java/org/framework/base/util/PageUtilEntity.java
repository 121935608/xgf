package org.framework.base.util;

import java.util.Map;

import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 分页对象 & 请求参数
 * 
 * @author y
 */
public class PageUtilEntity
{

    private int totalPage; // 总页数
    private int page; // 当前记录起始索引
    private int size; // 每页显示记录数
    private String orderByColumn; // 排序列
    private String isAsc; // 排序的方向 "desc" 或者 "asc".
    protected String orderCond; // 排序

    private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性

    private int totalResult; // 总记录数
    protected Map<String, String> relationMap; // 请求参数

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        this.isAsc = isAsc;
    }

    public boolean isEntityOrField()
    {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField)
    {
        this.entityOrField = entityOrField;
    }

    public int getTotalResult()
    {
        return totalResult;
    }

    public void setTotalResult(int totalResult)
    {
        this.totalResult = totalResult;
    }

    public String getOrderCond()
    {
        String orderCond = "";
        if (ObjectUtil.isNotNull(orderByColumn) && ObjectUtil.isNotNull(isAsc))
        {
            orderCond = "ORDER BY " + this.orderByColumn + " " + this.isAsc;
        }
        return orderCond;
    }

    public void setOrderCond(String orderCond)
    {
        this.orderCond = orderCond;
    }

    public Map<String, String> getRelationMap()
    {
        return relationMap;
    }

    public void setRelationMap(Map<String, String> relationMap)
    {
        this.relationMap = relationMap;
    }

}
