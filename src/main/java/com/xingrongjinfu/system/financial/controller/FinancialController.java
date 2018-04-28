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

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.procurement.common.ProcurementConstant;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ExportExcel;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.cashCount.model.Billing;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.financial.common.FinancialConstant;
import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.model.FinancialDetail;
import com.xingrongjinfu.system.financial.service.IFinancialService;
import com.xingrongjinfu.utils.UuidUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 财务结算（平台端）   收银日结（收银端）
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
    public ModelAndView toFinancialModify(String storeId){
        ModelAndView modelAndView = this.getModelAndView(FinancialConstant.FINANCIAL_MODIFY_PAGE);
        Financial financial = financialService.getByNum(storeId);
        BigDecimal rate = new BigDecimal(1).subtract(financial.getXzfRate().divide(new BigDecimal(Double.toString(100))));
        financial.setOpenMoney((financial.getCloseMoney().multiply(rate)).subtract(financial.getTotalMoney()));
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
        List<Financial> tableDataInfo=financialService.pageInfoQuery(pageUtilEntity);
        BigDecimal rate = null;
        for (Financial financial : tableDataInfo) {
            if(null == rate)
                rate = new BigDecimal(1).subtract(financial.getXzfRate().divide(new BigDecimal(Double.toString(100))));
            financial.setOpenMoney((financial.getCloseMoney().multiply(rate)).subtract(financial.getTotalMoney()));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }


    @RequestMapping(FinancialConstant.DOWNLOAD_FINANCIAL_DATA)
    public Message downloadFinancialData(HttpServletRequest request, HttpServletResponse response) {

        User user=this.getCurrentUser();
        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        String type = (String) SessionUtils.getSession().getAttribute("type");
        param.put("storeId", storeId);
        param.put("type", type);
        param.put("storeName", request.getParameter("storeName"));


        List<Map> data = financialService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            String[][] headers = new String[][]{{"商铺编号", "商铺名称", "收银金额（元）", "支付费率", "待还款金额（元）"},
                    {"amountNum", "commodityNo", "orderNumber", "payTime", "unit", "commodityNum", "totalPrice"}};


            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("采购统计表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
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
        financial.setAmountId(UuidUtil.get32UUID());
        financial.setAmountNum(UuidUtil.getNo("AM"));
        BigDecimal rate = financial.getXzfRate().divide(new BigDecimal(Double.toString(100))).add(new BigDecimal(Double.toString(1)));
        BigDecimal money = financial.getFee().multiply(rate);
        financial.setTotalMoney(money);
        financial.setAmountMoney(financial.getFee());
        financial.setStatus(1);
        financial.setAmountStatus(1);
        financial.setAddTime(new Date());
        
        Billing billing = new Billing();
        billing.setStoreId(financial.getStoreId());
        billing.setUpdateTime(new Date());
        billing.setRemain((financial.getCloseMoney().subtract(money)).multiply(new BigDecimal(Double.toString(100))).intValue());
        return new Message(financialService.addAmount(financial, billing));
    }
}