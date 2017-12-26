package org.apache.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.common.utils.Md5Utils;
import org.apache.shiro.common.utils.SystemLogUtils;
import org.apache.shiro.exception.UserPasswordNotMatchException;
import org.apache.shiro.exception.UserPasswordRetryLimitExceedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xingrongjinfu.system.user.model.User;

/**
 * 登录密码方法
 * 
 * @author y
 */
@Component
public class PasswordService
{

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount = "10";

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache("loginRecordCache");
    }

    public void validate(User user, String password)
    {
        String username = user.getUserName();

        AtomicInteger retryCount = loginRecordCache.get(username);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            SystemLogUtils.log(username, "passwordError",
                    "password error, retry limit exceed! password: {},max retry count {}", password, maxRetryCount);
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            // retryCount.getAndIncrement();
            loginRecordCache.put(username, retryCount);
            SystemLogUtils.log(username, "passwordError", "password error! password: {} retry count: {}", password,
                    retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(User user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getUserName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }

    public static void main(String[] args)
    {
        System.out.println(new PasswordService().encryptPassword("admin", "admin123", ""));
    }
}
