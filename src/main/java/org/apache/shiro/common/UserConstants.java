package org.apache.shiro.common;

/**
 * 用户常量信息
 * 
 * @author y
 */
public class UserConstants
{
    /** 正常状态 */
    public static final String normal = "0";

    /** 封禁状态 */
    public static final String blocked = "1";

    /** 异常状态 */
    public static final String exception = "1";

    /** 名称是否唯一的返回结果码 */
    public final static String NAME_UNIQUE = "0";
    public final static String NAME_NOT_UNIQUE = "1";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 5;
    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

}
