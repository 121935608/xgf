package com.xingrongjinfu.statistics.cashCount.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.paysWater.common.CashConstant;
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
import com.xingrongjinfu.statistics.cashCount.common.CashCountConstant;
import com.xingrongjinfu.statistics.paysWater.service.ICashService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class CashCountController extends BaseController {
    @Autowired
    private ICashService cashService;
    /**
     * Description: 跳转收银统计页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(CashCountConstant.TO_CASHCOUNT_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(CashCountConstant.CASH_LIST_PAGE);
        return modelAndView;
    }
    /**
     * 查询收银统计列表
     */
    @RequestMapping(CashCountConstant.CASHCOUNT_LIST)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = cashService.cashCountpageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     *  导出收银统计数据Excel
     *
     * @author fengqian
     * @date 2018/4/26 11:18
     * @param request
     * @param response
     * @return org.framework.core.model.Message
     */
    @RequestMapping(CashCountConstant.DOWNLOAD_CASHCOUNT_DATA)
    public Message downloadCashCountData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        param.put("storeId", storeId);
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("cashName", request.getParameter("cashName"));

        List<Map> data = cashService.cashCountInfoQuery(param);
        try {
            String[][] headers = new String[][]{
                    {"收银员", "支付宝（元）", "微信（元）", "银联（元）","现金（元）"},
                    {"cashName", "zhifubao", "weixin", "yinlian","xianjin"}
            };
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("收银统计表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }
}
