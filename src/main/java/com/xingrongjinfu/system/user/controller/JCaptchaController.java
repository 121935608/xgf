package com.xingrongjinfu.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.jcaptcha.JCaptcha;
import org.apache.shiro.web.jcaptcha.ValidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xingrongjinfu.system.user.common.UserConstant;

/**
 * 验证码 验证
 * 
 * @author y
 */
@Controller
public class JCaptchaController
{

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(UserConstant.JCAPTCHA_URL)
    @ResponseBody
    public Object jqueryValidationEngineValidate(HttpServletRequest request,
            @RequestParam(value = "fieldId", required = false) String fieldId,
            @RequestParam(value = "fieldValue", required = false) String fieldValue)
    {

        ValidateResponse response = ValidateResponse.newInstance();

        if (JCaptcha.hasCaptcha(request, fieldValue) == false)
        {
            response.validateFail(fieldId, messageSource.getMessage("jcaptcha.validate.error", null, null));
        }
        else
        {
            response.validateSuccess(fieldId, messageSource.getMessage("jcaptcha.validate.success", null, null));
        }

        return response.result();
    }
}
