package org.apache.shiro.web.jcaptcha;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.xingrongjinfu.utils.ObjectUtil;

import javax.servlet.http.HttpServletRequest;

public class JCaptcha
{
    public static final EsManageableImageCaptchaService captchaService = new EsManageableImageCaptchaService(
            new FastHashMapCaptchaStore(), new GMailEngine(), 180, 100000, 75000);

    public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse)
    {
        if (request.getSession(false) == null)
            return false;

        boolean validated = false;
        try
        {
            String id = request.getRequestedSessionId();
            validated = captchaService.validateResponseForID(id, userCaptchaResponse).booleanValue();
        }
        catch (CaptchaServiceException e)
        {
            e.printStackTrace();
        }
        return validated;
    }

    public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse)
    {
        if (request.getSession(false) == null)
            return false;
        boolean validated = false;
        try
        {
            String id = request.getSession().getId();
            if (ObjectUtil.isEmpty(id))
            {
                validated = false;
            }
            else
            {
                validated = captchaService.hasCapcha(id, userCaptchaResponse);
            }
        }
        catch (CaptchaServiceException e)
        {
            e.printStackTrace();
        }
        return validated;
    }

}
