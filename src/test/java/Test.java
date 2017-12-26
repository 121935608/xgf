import java.util.ArrayList;
import java.util.List;

public class Test
{
    
    private String style = "sstyle";
    private String name = "sname";
    private String idField = "sidField";
    
    List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();// 列表操作显示
    
    public Test()
    {
        DataGridColumn dataGridColumn = new DataGridColumn();
        dataGridColumn.setTitle("test_title");
        dataGridColumn.setField("test_field");
        columnList.add(dataGridColumn);
    }
    

    /**
     * datatables构造方法
     * 
     * @return
     */
    public StringBuffer datatables()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("$(document).ready(function() {");
        sb.append("var oTable = $(\'#userList\').dataTable({");
        // sb.append(
        // "\"sDom\" : \"<\'row\'<\'span6\'l><\'span6\'f>r>t<\'row\'<\'span6\'i><\'span6\'p>>\",");
        sb.append("\"bProcessing\" : true,");// 当datatable获取数据时候是否显示正在处理提示信息"
        sb.append("\"bPaginate\" : true,"); // 是否分页"
        sb.append("\"sPaginationType\" : \"full_numbers\",");// 分页样式full_numbers,"
        sb.append("\"bFilter\" : true,");// 是否使用内置的过滤功能"
        sb.append("\"bSort\" : true, ");// 排序功能"
        sb.append("\"bAutoWidth\" : true,");// 自动宽度"
        sb.append("\"bLengthChange\" : true,");// 是否允许用户自定义每页显示条数"
        sb.append("\"bInfo\" : true,");// 页脚信息"
        sb.append("\"sAjaxSource\" : \"userController.do?test\",");
        sb.append("\"bServerSide\" : true,");// 指定从服务器端获取数据
        sb.append("\"oLanguage\" : {" + "\"sLengthMenu\" : \" _MENU_ 条记录\"," + "\"sZeroRecords\" : \"没有检索到数据\","
                + "\"sInfo\" : \"第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条\"," + "\"sInfoEmtpy\" : \"没有数据\","
                + "\"sProcessing\" : \"正在加载数据...\"," + "\"sSearch\" : \"搜索\"," + "\"oPaginate\" : {"
                + "\"sFirst\" : \"首页\"," + "\"sPrevious\" : \"前页\", " + "\"sNext\" : \"后页\"," + "\"sLast\" : \"尾页\""
                + "}" + "},"); // 汉化
        // 获取数据的处理函数 \"data\" : {_dt_json : JSON.stringify(aoData)},
        sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
        // + "\"data\" : {_dt_json : JSON.stringify(aoData)},"
        sb.append("oSettings.jqXHR = $.ajax({" + "\"dataType\" : \'json\'," + "\"type\" : \"POST\","
                + "\"url\" : sSource," + "\"data\" : aoData," + "\"success\" : fnCallback" + "});},");
        sb.append("\"aoColumns\" : [ ");
        int i = 0;
        for (DataGridColumn column : columnList)
        {
            i++;
            sb.append("{");
            sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
            if (column.getField().equals("opt"))
            {
                sb.append(",\"mData\":\"" + idField + "\"");
                sb.append(",\"sWidth\":\"20%\"");
                sb.append(",\"bSortable\":false");
                sb.append(",\"bSearchable\":false");
                sb.append(",\"mRender\" : function(data, type, rec) {");
                //this.getOptUrl(sb);
                sb.append("}");
            }
            else
            {
                int colwidth = (column.getWidth() == null) ? column.getTitle().length() * 15 : column.getWidth();
                sb.append(",\"sName\":\"" + column.getField() + "\"");
                sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
                sb.append(",\"mData\":\"" + column.getField() + "\"");
                sb.append(",\"sWidth\":\"" + colwidth + "\"");
                sb.append(",\"bSortable\":" + column.isSortable() + "");
                // update-start-Author:zhangguoming Date:20140921 for：TASK #458 列表hidden=false，才是隐藏好像有点问题
                sb.append(",\"bVisible\":" + !column.isHidden() + "");
                // update-end-Author:zhangguoming Date:20140921 for：TASK #458 列表hidden=false，才是隐藏好像有点问题
                sb.append(",\"bSearchable\":" + column.isQuery() + "");
            }
            sb.append("}");
            if (i < columnList.size())
                sb.append(",");
        }

        sb.append("]" + "});" + "});" + "</script>");
        sb.append("<table width=\"100%\"  class=\"" + style + "\" id=\"" + name + "\" toolbar=\"#" + name
                + "tb\"></table>");
        return sb;
    }
    
    
    public static void main(String[] args)
    {
        System.out.println(new Test().datatables());
    }
    

}
