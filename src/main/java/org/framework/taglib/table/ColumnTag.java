package org.framework.taglib.table;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.framework.taglib.TagConstant;

/**
 * 封装表格处理
 * 
 * @author y
 */
public class ColumnTag extends BodyTagSupport
{
    private static final long serialVersionUID = 1L;

    private String title; // 设置列的标题

    private String name; // 设置单元格里的值

    private boolean visible = true; // 设置列是否显示

    private boolean orderable = false; // 设置列是否排序

    private String width; // 定义列的宽度

    private String css; // 定义列的样式

    private String colType; // 列类型

    private String format; // 定义列时间格式化

    private String colInnerHtml; // 列 html标签

    private String codekey; // 字段表标识键

    public int doEndTag() throws JspException
    {
        return EVAL_PAGE;
    }

    public int doStartTag() throws JspException
    {
        DataTable param = new DataTable();
        Tag parent = this.getParent();
        param.setTitle(title);
        param.setData(name);
        param.setWidth(width);
        param.setClassName(css);
        param.setVisible(visible);
        param.setOrderable(orderable);
        param.setColType(colType);
        if (TagConstant.COLTYPE_HREF.equals(this.colType))
        {
            param.setColInnerHtml(colInnerHtml);
        }
        else if (TagConstant.COLTYPE_DATE.equals(this.colType))
        {
            param.setFormat(format);
        }
        else if (TagConstant.COLTYPE_CODEGROUP.equals(this.colType))
        {
            param.setCodekey(codekey);
        }
        // 完成父子标签之间的通信.
        TableTag parentObj = (TableTag) parent;
        parentObj.addColModel(param);
        return SKIP_BODY;
    }

    public void release()
    {
        this.title = null;
        this.name = null;
        this.visible = true;
        this.orderable = false;
        this.width = null;
        this.css = null;
        this.format = null;
        this.colType = null;
        this.colInnerHtml = null;
        this.codekey = null;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getCss()
    {
        return css;
    }

    public void setCss(String css)
    {
        this.css = css;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public String getColType()
    {
        return colType;
    }

    public void setColType(String colType)
    {
        this.colType = colType;
    }

    public String getColInnerHtml()
    {
        return colInnerHtml;
    }

    public void setColInnerHtml(String colInnerHtml)
    {
        this.colInnerHtml = colInnerHtml;
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