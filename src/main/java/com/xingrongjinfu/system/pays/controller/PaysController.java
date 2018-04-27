/**
 * Copyright (C), 2018
 * FileName: PaysController
 * Author:   zxuser
 * Date:     2018/1/3 10:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.pays.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.pays.common.PaysConstant;
import com.xingrongjinfu.system.pays.model.Pays;
import com.xingrongjinfu.system.pays.model.PaysDto;
import com.xingrongjinfu.system.pays.service.IPaysService;
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
import java.util.*;

/**
 * 收银支付流水（平台端）
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class PaysController extends BaseController{

    @Autowired
    private IPaysService paysService;

    @RequestMapping(PaysConstant.PAYS_URL)
    public ModelAndView loadPays(){return this.getModelAndView(PaysConstant.PAYS_PAGE);}

    @RequestMapping(PaysConstant.PAYS_LIST_URL)
    public ModelAndView getPaysList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=paysService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }


    @RequestMapping(PaysConstant.DOWNLOAD_DATA)
    public Message downloadData(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = new HashMap();
        param.put("beginTime",request.getParameter("beginTime"));
        param.put("endTime",request.getParameter("endTime"));
        param.put("payType",request.getParameter("payType"));
        param.put("storeName",request.getParameter("storeName"));
        List<Map> data=paysService.payInfoQuery(param);
        try {
            String[][] headers=new String[][]{{"交易号","创建时间","商铺名称","收款金额（元）","支付方式"},
                                             {"tradeCode","addTime","storeName","money","payType"}};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition","attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("商品信息", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }

}

