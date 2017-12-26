package org.apache.shiro.common.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.framework.core.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.xingrongjinfu.system.userlog.model.UserLog;
import com.xingrongjinfu.system.userlog.service.IUserLogService;
import com.xingrongjinfu.utils.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 记录用户日志信息
 * 
 * @author y
 */
public class SystemLogUtils
{

    private static final Logger SYS_USER_LOGGER = LoggerFactory.getLogger("sys-user");
    private static final String LOGIN_SUCCESS = "loginSuccess", LOGIN_FAIL = "loginError";
    @Autowired
    private static IUserLogService userLogService;

    private static synchronized void injectUserLogService()
    {
        userLogService = (IUserLogService) SpringUtils.getBean("userLogService");
    }

    /**
     * 记录格式 [ip][用户名][操作][错误消息]
     * <p/>
     * 注意操作如下： loginError 登录失败 loginSuccess 登录成功 passwordError 密码错误 changePassword 修改密码 changeStatus 修改状态
     *
     * @param username
     * @param op
     * @param msg
     * @param args
     */
    public static void log(String username, String op, String msg, Object... args)
    {
        StringBuilder s = new StringBuilder();
        s.append(LogUtils.getBlock(getIp()));
        s.append(LogUtils.getBlock(username));
        s.append(LogUtils.getBlock(op));
        s.append(LogUtils.getBlock(msg));

        SYS_USER_LOGGER.info(s.toString(), args);

        if (LOGIN_SUCCESS.equals(op))
        {
            saveOpLog(username, msg, CommonConstant.SUCCESS);
        }
        else if (LOGIN_FAIL.equals(op))
        {
            saveOpLog(username, msg, CommonConstant.FAIL);
        }
        else
        {
            // 暂不处理
        }

    }

    public static Object getIp()
    {
        return SecurityUtils.getSubject().getSession().getHost();
    }

    /**
     * 得到request对象
     */
    public static HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        return request;
    }

    public static void saveOpLog(String username, String message, String status)
    {
        if (userLogService == null)
        {
            injectUserLogService();
        }

        UserAgent userAgent = UserAgent.parseUserAgentString(getRequest().getHeader("User-Agent"));
        String os = userAgent.getOperatingSystem().getName(); // 获取客户端操作系统
        String browser = userAgent.getBrowser().getName(); // 获取客户端浏览器

        UserLog userLog = new UserLog();
        userLog.setUserName(username);
        userLog.setStatus(status);
        userLog.setLoginIP(String.valueOf(getIp()));
        userLog.setBrowser(browser);
        userLog.setOs(os);
        userLog.setMsg(message);
        userLogService.addUserlog(userLog);
    }
}
