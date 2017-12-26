package org.apache.shiro.exception;

/**
 * 验证码错误异常类
 * 
 * @author y
 */
public class JCaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public JCaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
