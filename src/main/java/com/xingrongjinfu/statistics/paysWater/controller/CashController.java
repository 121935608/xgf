package com.xingrongjinfu.statistics.paysWater.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.pays.common.PaysConstant;
import com.xingrongjinfu.statistics.pays.model.PaysDto;
import com.xingrongjinfu.statistics.paysWater.model.CashFlowDto;
import com.xingrongjinfu.utils.ExportExcel;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.paysWater.common.CashConstant;
import com.xingrongjinfu.statistics.paysWater.model.CashFlow;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 收银流水（收银端）
 * date: 2018年3月12日 下午6:05:05 <br/>
 *
 * @author huYL
 * @version 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class CashController extends BaseController{
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转收银流水页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(CashConstant.TO_CASH_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(CashConstant.CASH_LIST_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转收银明细页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(CashConstant.TO_CASH_DETAIL_URL)
    public ModelAndView toRegisterAdd(String cashId) throws Exception{
        ModelAndView modelAndView = new ModelAndView(CashConstant.CASH_DETAIL_PAGE);
        CashFlow cashFlow = cashService.getById(cashId);
        modelAndView.addObject("cashFlow", cashFlow);
        return modelAndView;
    }
    /**
     * 查询收银流水列表
     */
    @RequestMapping(CashConstant.CASH_LIST_URL)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = cashService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     *
     *
     * @author fengqian
     * @date 2018/4/26 11:18
     * @param request
     * @param response
     * @return org.framework.core.model.Message
     */
    @RequestMapping(CashConstant.DOWNLOAD_CASH_DATA)
    public Message downloadCashData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        param.put("storeId", storeId);
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("commodityNo", request.getParameter("commodityNo"));

        List<CashFlowDto> data = cashService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            ExportExcel<CashFlowDto> ee = new ExportExcel<>();
            String[] headers = new String[]{"流水号", "收银机", "日期", "类型", "收银员", "会员", "商品数量", "商品价格", "折后价格",};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ee.exportExcel("银行支付流水表", headers, data, output,"yyyy-MM-dd HH:mm:ss");
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }
}
