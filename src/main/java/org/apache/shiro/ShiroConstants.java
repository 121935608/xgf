package org.apache.shiro;

/**
 * Shiro通用常量
 * 
 * @author y
 */
public interface ShiroConstants
{
    /**
     * 当前登录的用户
     */
    public static final String CURRENT_USER = "currentUser";

    /**
     * 用户名
     */
    public static final String CURRENT_USERNAME = "username";

    /**
     * 验证码
     */
    public static final String CURRENT_JCAPTCHA = "jcaptcha";

    /**
     * 消息key
     */
    public static String MESSAGE = "message";

    /**
     * 错误key
     */
    public static String ERROR = "errorMsg";

    /**
     * 验证码错误
     */
    public static final String JCAPTCHA_ERROR = "jcaptchaError";

    /**
     * 编码格式
     */
    public static String ENCODING = "UTF-8";
}
