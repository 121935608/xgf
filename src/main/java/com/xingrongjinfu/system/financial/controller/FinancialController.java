/**
 * Copyright (C), 2018
 * FileName: FinancialController
 * Author:   zxuser
 * Date:     2018/1/3 14:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.financial.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.financial.common.FinancialConstant;
import org.framework.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class FinancialController extends BaseController {


    @RequestMapping(FinancialConstant.FINANCIAL_URL)
    public ModelAndView loadFinancial(){
        return this.getModelAndView(FinancialConstant.FINANCIAL_PAGE);
    }
}