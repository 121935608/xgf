package org.framework.taglib.table;

/**
 * DataTable 表格数据参数
 * 
 * @author y
 */
public class DataTable
{

    private String width = "20%"; // 设置列宽

    private String title; // 给列设置名称

    private String data; // 设置单元格里的值

    private boolean visible = true; // 设置列是否显示

    private boolean orderable = false; // 设置列是否排序

    private String className = "text-c"; // 指定单元格样式

    private String colType = "text"; // 列类型

    private String colInnerHtml = ""; // 列 html标签

    private String format = ""; // 设置列时间格式化

    private String codekey = ""; // 字段表标识键

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean isOrderable()
    {
        return orderable;
    }

    public void setOrderable(boolean orderable)
    {
        this.orderable = orderable;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        if (null != className)
        {
            this.className = className;
        }
    }

    public String getColType()
    {
        return colType;
    }

    public void setColType(String colType)
    {
        if (null != colType)
        {
            this.colType = colType;
        }
    }

    public String getColInnerHtml()
    {
        return colInnerHtml;
    }

    public void setColInnerHtml(String colInnerHtml)
    {
        this.colInnerHtml = colInnerHtml;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public String getCodekey()
    {
        return codekey;
    }

    public void setCodekey(String codekey)
    {
        this.codekey = codekey;
    }

}
