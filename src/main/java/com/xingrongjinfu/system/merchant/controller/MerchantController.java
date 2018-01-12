/**
 * Copyright (C), 2017
 * FileName: MerchantController
 * Author:   zxuser
 * Date:     2017/12/26 16:30
 * Description: 商品管理控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.merchant.common.MerchantConstant;
import com.xingrongjinfu.system.merchant.model.AccountInfo;
import com.xingrongjinfu.system.merchant.model.Merchant;
import com.xingrongjinfu.system.merchant.service.IMerchantService;
import com.xingrongjinfu.system.user.model.User;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品管理控制层〉
 *
 * @author zhaoyunfei
 * @create 2017/12/26
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class MerchantController extends BaseController {

    @Autowired
    private IMerchantService merchantService;

    /**
     * 跳转到商户管理界面
     * @return
     */
    @RequestMapping(MerchantConstant.MERCHANT_URL)
    public ModelAndView loadMerchant(){return this.getModelAndView(MerchantConstant.MERCHANT_PAGE);}

    /**
     * 商户列表信息
     * @return
     */
    @RequestMapping(MerchantConstant.MERCHANT_LIST)
    public ModelAndView merchantList(){
        PageUtilEntity pageUtilEntity =this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=merchantService.pageInfoQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 跳转到产看商户界面
     * @param
     * @return
     */
    @RequestMapping(MerchantConstant.MERCHANT_QUERY_URL)
    public ModelAndView merchantQuery(Merchant merchant)
    {
        ModelAndView modelAndView=this.getModelAndView(MerchantConstant.MERCHANT_QUERY_PAGE);
        AccountInfo accountInfo= merchantService.getUserAccount(merchant);
        if (accountInfo !=null) {
            accountInfo.setFrontPic(accountInfo.getFrontPic() == null ? "" : (MerchantConstant.ALIYUN_URL + accountInfo.getFrontPic()));
            accountInfo.setBackPic(accountInfo.getBackPic() == null ? "" : (MerchantConstant.ALIYUN_URL + accountInfo.getBackPic()));
            accountInfo.setLicensePic(accountInfo.getLicensePic() == null ? "" : (MerchantConstant.ALIYUN_URL + accountInfo.getLicensePic()));
            accountInfo.setFrontStorePic(accountInfo.getFrontStorePic() == null ? "" : (MerchantConstant.ALIYUN_URL + accountInfo.getFrontStorePic()));
            accountInfo.setInnerStorePic(accountInfo.getInnerStorePic() == null ? "" : (MerchantConstant.ALIYUN_URL + accountInfo.getInnerStorePic()));
            modelAndView.addObject("accountInfo", accountInfo);
        }
        return modelAndView;
    }

    /**
     * 商户的禁用与启用
     */
    @RequestMapping(MerchantConstant.CHANGE_MERCHANT_STATUS)
    public @ResponseBody
    Message changeMerchantStatus(User user)
    {
        int result = 0;
        String id = user.getUserId();
        if (id != null)
        {
            result = merchantService.changeMerchantStatus(user);
        }
        return new Message(result);
    }

    /**
     * 查看账户余额
     * @param merchant
     * @return
     */
    @RequestMapping(MerchantConstant.MERCHANT_BALANCE_URL)
    public ModelAndView toAccountBalance(Merchant merchant)
    {
        ModelAndView modelAndView=this.getModelAndView(MerchantConstant.MERCHANT_BALANCE_PAGE);
        Merchant merchants = merchantService.getMerchantInfo(merchant);
        modelAndView.addObject("merchant",merchants);
        return modelAndView;

    }

    @RequestMapping(MerchantConstant.MERCHANT_BALANCE_LIST)
    public ModelAndView accountInfoList(){
        PageUtilEntity pageUtilEntity =this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=merchantService.AccountPageInfoQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

}