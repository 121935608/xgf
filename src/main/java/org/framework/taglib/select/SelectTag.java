package org.framework.taglib.select;

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
 * 封装select标签、从数据库表读取初始化数据
 * 
 * @author y
 */
public class SelectTag extends SimpleTagSupport
{
    private String id;

    private String name;

    private Object codeGroup;

    private String selectedValue;

    private boolean disabled;

    private String cssClass;

    private String onChange;

    private String headerKey;

    private String headerValue;

    @Autowired
    private static ISysCodeService sysCodeService;

    private synchronized void injectSysCodeService()
    {
        sysCodeService = (ISysCodeService) SpringUtils.getBean("sysCodeService");
    }

    @SuppressWarnings("unchecked")
    public void doTag() throws JspException, IOException
    {

        if (sysCodeService == null)
        {
            injectSysCodeService();
        }

        List<SysCode> codeList = null;

        if (ObjectUtil.isNotNull(codeGroup) && codeGroup instanceof java.util.List)
        {
            codeList = (List<SysCode>) codeGroup;
        }
        else
        {
            codeList = sysCodeService.getUsysCodeListByCodekey(codeGroup.toString());
        }

        boolean selected = selectedValue != null && selectedValue.length() > 0;
        JspWriter out = getJspContext().getOut();
        StringBuffer sb = new StringBuffer("<span class=\"select-box\">").append("<select autocomplete=\"off\"")
                .append(" name=\"").append(name).append("\"");
        if (id != null)
        {
            sb.append(" id=\"").append(id).append("\"");
        }
        if (cssClass != null)
        {
            sb.append(" class=\"").append(cssClass).append("\"");
        }
        if (onChange != null)
        {
            sb.append(" onChange=\"").append(onChange).append("\"");
        }
        if (disabled)
        {
            sb.append(" disabled");
        }

        sb.append(">");
        if (headerKey != null && headerValue != null)
        {
            sb.append("<option value=\"").append(headerKey).append("\"");
            sb.append(">").append(headerValue).append("</option>");
        }
        for (SysCode code : codeList)
        {
            sb.append("<option value=\"").append(code.getCodeid()).append("\"");
            if (selected && code.getCodeid().equals(selectedValue))
            {
                sb.append(" selected");
            }
            sb.append(">").append(code.getCodevalue()).append("</option>");
        }
        sb.append("</select></span>");
        out.println(sb.toString());
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Object getCodeGroup()
    {
        return codeGroup;
    }

    public void setCodeGroup(Object codeGroup)
    {
        this.codeGroup = codeGroup;
    }

    public String getSelectedValue()
    {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue)
    {
        this.selectedValue = selectedValue;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    public String getCssClass()
    {
        return cssClass;
    }

    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getOnChange()
    {
        return onChange;
    }

    public void setOnChange(String onChange)
    {
        this.onChange = onChange;
    }

    public String getHeaderKey()
    {
        return headerKey;
    }

    public void setHeaderKey(String headerKey)
    {
        this.headerKey = headerKey;
    }

    public String getHeaderValue()
    {
        return headerValue;
    }

    public void setHeaderValue(String headerValue)
    {
        this.headerValue = headerValue;
    }

}