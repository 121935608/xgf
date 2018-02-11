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

import java.util.Date;
import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.financial.common.FinancialConstant;
import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.model.FinancialDetail;
import com.xingrongjinfu.system.financial.service.IFinancialService;
import com.xingrongjinfu.utils.UuidUtil;

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

    @Autowired
    private IFinancialService financialService;

    /**
     * Description:跳转结算页面 <br/>
     *
     * @author huYL
     * @return
     */
    @RequestMapping(FinancialConstant.FINANCIAL_URL)
    public ModelAndView loadFinancial(){
        return this.getModelAndView(FinancialConstant.FINANCIAL_PAGE);
    }
    /**
     * Description:跳转对账页面 <br/>
     *
     * @author huYL
     * @param amountNum
     * @return
     */
    @RequestMapping(FinancialConstant.FINANCIAL_MODIFY_URL)
    public ModelAndView toFinancialModify(String amountId){
        ModelAndView modelAndView = this.getModelAndView(FinancialConstant.FINANCIAL_MODIFY_PAGE);
        Financial financial = financialService.getByNum(amountId);
        modelAndView.addObject("financial", financial);
        return modelAndView;
    }


    /**
     * Description: 结算列表查询<br/>
     *
     * @author huYL
     * @return
     */
    @RequestMapping(FinancialConstant.FINANCIAL_LIST_URL)
    public ModelAndView findFinancial(){
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        String type = (String) SessionUtils.getSession().getAttribute("type");
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        pageUtilEntity.getRelationMap().put("type", type);
        List<TableDataInfo>tableDataInfo=financialService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }
    /**
     * 结算明细
     */
    @RequestMapping(FinancialConstant.GET_DETAIL)
    public @ResponseBody List<FinancialDetail> getDetail(String amountId) {
        List<FinancialDetail> list = financialService.getDetail(amountId);
        return list;
    }
    /**
     * 结算对账保存
     */
    @RequestMapping(FinancialConstant.FINANCIAL_MODIFY)
    public @ResponseBody Message modifyFinancial(Financial financial,Integer type) {
        financial.setCloseMoney(financial.getFee().add(financial.getCloseMoney()));
        financial.setUpdateTime(new Date());
        financial.setStatus(1);
        if(type == 1){
            financial.setAmountStatus(0);
        }else if(type == 0){
            financial.setAmountStatus(1);
        }
        FinancialDetail financialDetail = new FinancialDetail();
        financialDetail.setAmountId(financial.getAmountId());
        financialDetail.setAmountDetailId(UuidUtil.get32UUID());
        financialDetail.setMoney(financial.getFee());
        financialDetail.setAddTime(new Date());
        financialDetail.setUserId(getCurrentUser().getUserId());
        if(financial.getRemark() != null){
            financialDetail.setRemark(financial.getRemark());
        }
        int n = financialService.updateAmount(financial, financialDetail);
        return new Message(n);
    }
}