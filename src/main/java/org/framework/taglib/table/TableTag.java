package org.framework.taglib.table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.framework.taglib.TagConstant;
import org.framework.taglib.util.LabelUtil;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 表格逻辑封装处理
 * 
 * @author y
 */
public class TableTag extends BodyTagSupport
{
    private static final long serialVersionUID = 1L;

    private PageContext pageContext;

    /** 加载url */
    private String url;

    /** 标题 */
    private String title;

    /** ID */
    private String id;

    /** 宽度 */
    private String width = "100%";

    /** 高度 */
    private String height;

    /** 排序列名 */
    private String sortName;

    /** 排序方向 */
    private String sortOrder = "asc";

    /** 每页数量 */
    private int pageLength = 10;

    /** 是否显示复选框 */
    private boolean checkbox = false;

    /** 复选框value列名 */
    private String rowId = "";

    /** 列集合对象 */
    private List<DataTable> colModel = new ArrayList<DataTable>();

    /** 列Map列表 */
    private Map<String, Integer> colMap = new HashMap<String, Integer>();

    // 增加参数
    public void addColModel(DataTable dataTable)
    {
        this.colModel.add(dataTable);
    }

    // 获得列字符串
    public String getColumn()
    {
        StringBuilder column = new StringBuilder();

        int col_length = colModel.size();

        if (this.checkbox)
        {
            column.append(LabelUtil.getCheckbox(this.rowId));
        }
        for (int i = 0; i < col_length; i++)
        {
            DataTable dataTable = colModel.get(i);
            StringBuilder sb = new StringBuilder();
            String colType = dataTable.getColType();
            String data = dataTable.getData();

            sb.append("{");
            sb.append("\"className\" : \"").append(dataTable.getClassName()).append("\",");
            sb.append("\"width\" : \"").append(dataTable.getWidth()).append("\",");
            sb.append("\"title\" : \"").append(dataTable.getTitle()).append("\",");
            sb.append("\"visible\" : ").append(dataTable.isVisible()).append(",");
            sb.append("\"orderable\" : ").append(dataTable.isOrderable()).append(",");

            if (TagConstant.COLTYPE_HREF.equals(colType))
            {
                String colHtml = dataTable.getColInnerHtml();
                sb.append("\"searchable\" : \"").append(false).append("\","); // 定义列是否允许搜索
                sb.append("\"render\" : function(data, type, row) {");
                sb.append("return \"" + LabelUtil.toColInnerHtml(colHtml) + "\";");
                sb.append("}");
            }
            else if (TagConstant.COLTYPE_DATE.equals(colType))
            {
                sb.append("\"data\" : \"").append(data).append("\",");
                sb.append("\"searchable\" : \"").append(false).append("\",");
                sb.append("\"render\" : function(data, type, row) {");
                sb.append("return formatDate(data,\"" + dataTable.getFormat() + "\");");
                sb.append("}");
            }
            else if (TagConstant.COLTYPE_CODEGROUP.equals(colType))
            {
                sb.append("\"data\" : \"").append(data).append("\",");
                sb.append("\"searchable\" : \"").append(false).append("\",");
                sb.append("\"render\" : function(data, type, row) {");
                sb.append(LabelUtil.getCodekey(data, dataTable.getCodekey()));
                sb.append("}");
            }
            else
            {
                sb.append("\"data\" : \"").append(data).append("\",");
            }

            sb.append("},");
            column.append(sb);
            colMap.put(data, i);
        }
        colModel.clear();// 参数要清空
        return column.toString();

    }

    // 开始标签时的操作
    public int doStartTag() throws JspException
    {
        return EVAL_BODY_INCLUDE;
    }

    // 结束标签时的操作
    public int doEndTag() throws JspException
    {
        JspWriter out = pageContext.getOut();
        StringBuilder sb = new StringBuilder();
        try
        {
            sb.append("<script type=\"text/javascript\">");
            sb.append("var pageTable;");
            sb.append("$(document).ready(function() { ");
            sb.append("var aoColumns = [" + getColumn() + "];");
            sb.append("var url = \"" + getUrl() + "\";");
            if (ObjectUtil.isNotNull(colMap.get(sortName)))
            {
                sb.append("pageTable = _Datatable_Init(pageTable, aoColumns, url," + pageLength + ","
                        + colMap.get(sortName) + ",\"" + sortOrder + "\")");
            }
            else
            {
                sb.append("pageTable = _Datatable_Init(pageTable, aoColumns, url," + pageLength + ")");
            }
            sb.append("});");
            sb.append("</script>");
            sb.append("<table width=\"" + width
                    + "\" class=\"table table-border table-bordered table-hover table-bg table-sort\" id=\"" + id
                    + "\"></table>");
            out.print(sb.toString());
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    // 此方法用来释放程序占用的资源
    public void release()
    {
        this.url = null;
        this.title = null;
        this.width = null;
        this.height = null;
        this.colModel = null;
        this.id = null;
        this.sortName = null;
        this.sortOrder = null;
        this.rowId = null;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setPageContext(final PageContext pageContext)
    {
        this.pageContext = pageContext;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getSortName()
    {
        return sortName;
    }

    public void setSortName(String sortName)
    {
        this.sortName = sortName;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public int getPageLength()
    {
        return pageLength;
    }

    public void setPageLength(int pageLength)
    {
        this.pageLength = pageLength;
    }

    public boolean isCheckbox()
    {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox)
    {
        this.checkbox = checkbox;
    }

    public String getRowId()
    {
        return rowId;
    }

    public void setRowId(String rowId)
    {
        this.rowId = rowId;
    }

}