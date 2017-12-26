package com.xingrongjinfu.system.syscode.model;

import java.io.Serializable;

/**
 * 数据字典对象 sys_code
 * 
 * @author y
 */
public class SysCode implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String codekey;
    private String codeid;
    private String codevalue;
    private String codedesc;
    private String servicekey;

    public String getServicekey()
    {
        return this.servicekey;
    }

    public void setServicekey(String servicekey)
    {
        this.servicekey = servicekey;
    }

    public String getCodevalue()
    {
        return this.codevalue;
    }

    public void setCodevalue(String codevalue)
    {
        this.codevalue = codevalue;
    }

    public String getCodedesc()
    {
        return this.codedesc;
    }

    public void setCodedesc(String codedesc)
    {
        this.codedesc = codedesc;
    }

    public String getCodekey()
    {
        return codekey;
    }

    public void setCodekey(String codekey)
    {
        this.codekey = codekey;
    }

    public String getCodeid()
    {
        return codeid;
    }

    public void setCodeid(String codeid)
    {
        this.codeid = codeid;
    }

    public void setDefaultValues()
    {
        if (this.servicekey != null)
            return;
        setServicekey("0");
    }
}