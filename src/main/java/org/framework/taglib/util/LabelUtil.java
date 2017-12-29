package org.framework.taglib.util;

import java.util.List;

import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.syscode.service.ISysCodeService;
import com.xingrongjinfu.utils.ObjectUtil;
import com.xingrongjinfu.utils.SpringUtils;

/**
 * 标签工具处理方法
 * 
 * @author y
 */
public class LabelUtil
{

    /**
     * 处理href属性
     */
    public static String toColInnerHtml(String colInnerHtml)
    {
        String colInnerNewHtml = "";
        String colInnerHtmlTemp = colInnerHtml.replace("\"", "\\\"").replace("$T.", "\"+row.");
        int ycount = getCount(colInnerHtmlTemp, "row.");
        if (ycount == 0)
        {
            colInnerNewHtml = colInnerHtml;
        }
        else if (ycount > 1)
        {
            String colInnerOldHtml = colInnerHtmlTemp.replace(")\\\"", "+\")\\\"");
            colInnerNewHtml = colInnerOldHtml.replace(",\"+", "+\",\"+");
        }
        else
        {
            colInnerNewHtml = colInnerHtmlTemp.replace(")\\\"", "+\")\\\"");
        }
        return colInnerNewHtml;
    }

    /**
     * 处理codeGroup属性
     */
    public static String getCodekey(String data, String codekey)
    {
        StringBuffer sb = new StringBuffer();
        ISysCodeService sysCodeService = (ISysCodeService) SpringUtils.getBean("sysCodeService");
        List<SysCode> sysCodeList = sysCodeService.getUsysCodeListByCodekey(codekey);
        for (int i = 0; i < sysCodeList.size(); i++)
        {
            SysCode sysCode = sysCodeList.get(i);
            String conditions = i == 0 ? "if" : "else if";
            sb.append(conditions + "(data == " + sysCode.getCodeid() + ") {");
            sb.append("return \"" + sysCode.getCodevalue() + "\";");
            sb.append("}");
        }
        sb.append(sysCodeList.size() > 0 ? "else { return \"无匹配\"; }" : "return \"无匹配\";");
        return sb.toString();
    }

    /**
     * 处理checkbox属性
     */
    public static String getCheckbox(String rowId)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"data\" : \"").append(rowId).append("\",");
        sb.append("\"title\" : \"").append("<input type='checkbox' id='defaultCheckbox'>").append("\",");
        sb.append("\"sClass\" : \"").append("td-status text-c").append("\",");
        sb.append("\"width\" : \"").append("5%").append("\",");
        sb.append("\"orderable\" : ").append(false).append(",");
        sb.append("\"render\" : function(data, type, row) {");
        if (ObjectUtil.isNotEmpty(rowId))
        {
            sb.append("return \"<input type='checkbox' name='rowId' value='\" + data + \"'>\";");
        }
        else
        {
            sb.append("return \"未匹配\";");
        }
        sb.append("}");
        sb.append("},");
        return sb.toString();
    }

    public static int getCount(String str, String sub)
    {
        int index = 0;
        int count = 0;
        while ((index = str.indexOf(sub, index)) != -1)
        {

            index = index + sub.length();
            count++;
        }
        return count;
    }

}
