package com.xingrongjinfu.system.storeaffairs.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.storeaffairs.commom.StoreaffairConstant;
import com.xingrongjinfu.system.storeaffairs.model.PurchaseDto;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.storeaffairs.service.IStoreSaleCountService;
import com.xingrongjinfu.utils.ExportExcel;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class StoreSaleCount extends BaseController {

    @Autowired
    private IStoreSaleCountService storeSaleCountService;

    @RequestMapping(StoreaffairConstant.STORE_SALE_VIEW)
    public ModelAndView loadSalePage(){
        return this.getModelAndView(StoreaffairConstant.STORE_SALE_PAGE);
    }

    @RequestMapping(StoreaffairConstant.STORE_SALE_LIST)
    public ModelAndView storeSaleCountQuery(){
     //   String storeName = (String) SessionUtils.getSession().getAttribute("storeName");
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeName = pageUtilEntity.getRelationMap().get("storeName");
        if(storeName!=null&&!storeName.equals("")){
            try {
                pageUtilEntity.getRelationMap().put("storeName", URLDecoder.decode(storeName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        List<TableDataInfo> tableDataInfo = storeSaleCountService.storeSaleCountQuery(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    @RequestMapping(StoreaffairConstant.DOWNLOAD_PURCHASE_DATA)
    public Message downloadCommodityData(HttpServletRequest request, HttpServletResponse response) {


        Map<String, String> param = new HashMap();
        param.put("beginTime", request.getParameter("beginTime"));
        param.put("endTime", request.getParameter("endTime"));
        param.put("storeName", request.getParameter("storeName"));

        List<PurchaseDto> data = storeSaleCountService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            ExportExcel<PurchaseDto> ee = new ExportExcel<>();
            String[] headers = new String[]{"商品名称", "督导员", "销售总金额", "交易单数量"};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ee.exportExcel("商户采购统计表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }



}
