package org.aspectj.system;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroConstants;
import org.apache.shiro.common.UserConstants;
import org.apache.shiro.common.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import com.xingrongjinfu.system.operlog.model.OperLog;
import com.xingrongjinfu.system.operlog.service.IOperLogService;
import com.xingrongjinfu.system.user.model.User;

/**
 * 操作日志记录处理
 * 
 * @author y
 */
@Aspect
@Component
public class SystemLogAspect
{
    // 注入Service用于把日志保存数据库
    @Autowired
    private IOperLogService operLogService;
    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    // Controller层切点
    @Pointcut("@annotation(org.aspectj.lang.annotation.ActionControllerLog)")
    public void controllerAspect()
    {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint)
    {
        handleLog(joinPoint, null);
    }

    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e);
    }

    private void handleLog(JoinPoint joinPoint, Exception e)
    {
        try
        {
            // 获得注解
            ActionControllerLog controllerLog = giveController(joinPoint);
            if (controllerLog == null)
            {
                return;
            }
            // 获取当前的用户
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroConstants.CURRENT_USER);

            // *========数据库日志=========*//
            OperLog operLog = new OperLog();
            operLog.setStatus(UserConstants.normal);
            // 请求的IP
            String ip = IpUtils.getIpAddr(request);
            operLog.setUserIp(ip);
            operLog.setCreateTIme(new Date());
            operLog.setUrl(request.getRequestURI());
            if (currentUser != null)
            {
                operLog.setUserId(currentUser.getUserId());
                operLog.setUserName(currentUser.getUserName());
            }

            if (e != null)
            {
                operLog.setStatus(UserConstants.exception);
                operLog.setErrorMessage(e.getMessage());
            }

            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog, request);
            // 保存数据库
            operLogService.addOperlog(operLog);
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static void getControllerMethodDescription(ActionControllerLog controllerLog, OperLog operLog,
            HttpServletRequest request) throws Exception
    {
        // 设置action动作
        operLog.setAction(controllerLog.action());
        // 设置标题
        operLog.setTitle(controllerLog.title());
        // 设置channel
        operLog.setChannel(controllerLog.channel());
        // 是否需要保存request，参数和值
        if (controllerLog.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operLog, request);
        }
    }

    /**
     * 获取请求的参数，放到log中
     * 
     * @param operLog
     * @param request
     */
    @SuppressWarnings("rawtypes")
    private static void setRequestValue(OperLog operLog, HttpServletRequest request)
    {
        if (operLog == null)
            operLog = new OperLog();
        Map map = request.getParameterMap();
        String params = JSONObject.toJSONString(map);
        operLog.setRequestParam(params);
    }

    /**
     * 是否存在注解，如果存在就记录日志
     * 
     * @param joinPoint
     * @param controllerLog
     * @return
     * @throws Exception
     */
    private static ActionControllerLog giveController(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(ActionControllerLog.class);
        }
        return null;
    }
}