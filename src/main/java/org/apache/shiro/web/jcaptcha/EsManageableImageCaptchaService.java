package org.apache.shiro.web.jcaptcha;

import com.xingrongjinfu.utils.ObjectUtil;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class EsManageableImageCaptchaService extends DefaultManageableImageCaptchaService
{

    public EsManageableImageCaptchaService(com.octo.captcha.service.captchastore.CaptchaStore captchaStore,
            com.octo.captcha.engine.CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds,
            int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection)
    {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize,
                captchaStoreLoadBeforeGarbageCollection);
    }

    public boolean hasCapcha(String id, String userCaptchaResponse)
    {
        if (ObjectUtil.isEmpty(id))
        {
            return false;
        }
        return store.getCaptcha(id).validateResponse(userCaptchaResponse);
    }
}
