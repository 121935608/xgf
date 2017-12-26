package com.xingrongjinfu.common.framework.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 封装系统文件标签
 * 
 * @author y
 */
public class SystemPropertyTag extends SimpleTagSupport
{

    private String key;

    public void doTag() throws JspException, IOException
    {
        JspWriter out = getJspContext().getOut();
        StringBuffer sb = new StringBuffer(System.getProperty(key));
        out.println(sb.toString());
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }
}
