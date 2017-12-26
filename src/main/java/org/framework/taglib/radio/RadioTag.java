package org.framework.taglib.radio;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.syscode.service.ISysCodeService;
import com.xingrongjinfu.utils.ObjectUtil;
import com.xingrongjinfu.utils.SpringUtils;

/**
 * 封装radio标签、从数据库表读取初始化数据
 * 
 * @author y
 */
public class RadioTag extends SimpleTagSupport
{

    private String name;

    private String selectedValue;

    private String codeGroup;

    @Autowired
    private static ISysCodeService sysCodeService;

    private synchronized void injectSysCodeService()
    {
        sysCodeService = (ISysCodeService) SpringUtils.getBean("sysCodeService");
    }

    public void doTag() throws JspException, IOException
    {

        if (sysCodeService == null)
        {
            injectSysCodeService();
        }

        List<SysCode> codeList = sysCodeService.getUsysCodeListByCodekey(codeGroup);
        JspWriter out = getJspContext().getOut();
        StringBuffer sb = new StringBuffer("");

        for (SysCode code : codeList)
        {
            sb.append("<div class=\"radio-box\">\n");
            sb.append("<input");
            sb.append(" type=\"").append("radio").append("\"");
            sb.append(" id=\"radio-").append(code.getCodeid()).append("\"");
            sb.append(" name=\"").append(name).append("\"");
            sb.append(" value=\"").append(code.getCodeid()).append("\"");
            if (ObjectUtil.isNotEmpty(selectedValue))
            {
                if (selectedValue.equals(code.getCodeid()))
                {
                    sb.append(" checked ");
                }
            }
            sb.append(">\n");
            sb.append("<label for=\"radio-").append(code.getCodeid()).append("\">");
            sb.append(code.getCodevalue()).append("</label>\n");
            sb.append("</div>\n\n");
        }
        out.println(sb.toString());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSelectedValue()
    {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue)
    {
        this.selectedValue = selectedValue;
    }

    public String getCodeGroup()
    {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup)
    {
        this.codeGroup = codeGroup;
    }

    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer("");

        sb.append("<div class=\"radio-box\">");
        sb.append("<input");
        sb.append(" type=\"").append("radio").append("\"");
        sb.append(" id=\"radio-").append("1").append("\"");
        sb.append(" name=\"").append("name").append("\"");

        if (ObjectUtil.isNotEmpty("123"))
        {
            if (true)
            {
                sb.append(" checked");
            }
        }

        sb.append(">");
        sb.append("<label for=\"radio-").append("1").append(">");
        sb.append("禁用").append("</label>");
        sb.append("</div>");

        System.out.println(sb.toString());

    }

}
