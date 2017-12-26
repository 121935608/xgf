package org.framework.core.model;

/**
 * 返回到界面提示信息
 * 
 * @author y
 */
public class Message
{
    private boolean s;

    private String m;

    private int t;

    private boolean timeout;

    public Message(int r)
    {
        if (r > 0)
        {
            s = true;
            this.m = "操作成功!";
        }
        else
        {
            s = false;
            this.m = "操作失败!";
        }
    }

    public Message()
    {
        this.s = true;
        this.m = "操作成功!";
    }

    public Message(boolean s)
    {
        this.s = s;
        this.m = "操作失败!";
    }

    public Message(boolean s, String m)
    {
        this.s = s;
        this.m = m;
    }

    /**
     * 
     * @param s 消息成功标识
     * @param t 操作类型
     * @param name 操作名称
     */
    public Message(boolean s, int t, String m)
    {
        this.s = s;
        this.t = t;
        this.m = m;
    }

    public Message(boolean s, String m, boolean timeout)
    {
        this.s = s;
        this.m = m;
        this.timeout = timeout;
    }

    public boolean isS()
    {
        return s;
    }

    public void setS(boolean s)
    {
        this.s = s;
    }

    public String getM()
    {
        return m;
    }

    public void setM(String m)
    {
        this.m = m;
    }

    public int getT()
    {
        return t;
    }

    public void setT(int t)
    {
        this.t = t;
    }

    public boolean isTimeout()
    {
        return timeout;
    }

    public void setTimeout(boolean timeout)
    {
        this.timeout = timeout;
    }
}