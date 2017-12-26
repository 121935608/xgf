package org.apache.shiro.exception;

import org.apache.shiro.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author y
 */
public class UserException extends BaseException
{

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }

}
