package com.xingrongjinfu.statistics.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
import com.xingrongjinfu.commodity.fenlei.service.IFenleiService;
import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.stock.common.StockConstant;
import com.xingrongjinfu.statistics.stock.service.IStockService;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * Description: 库存查询（收银端）
 * date: 2018年3月12日 下午6:07:17 <br/>
 *
 * @author huYL
 * @version 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class StockController {
    @Autowired
    private IStockService stockService;
    @Autowired
    private IFenleiService fenleiService;
    /**
     * Description: 跳转库存查询页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(StockConstant.TO_STOCK_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(StockConstant.STOCK_LIST_PAGE);
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        modelAndView.addObject("fenlei", getFL(storeId));
        return modelAndView;
    }
    /**
     * Description: 获取分类<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    public List<SysCode> getFL(String storeId) throws Exception{
        List<Fenlei> fenleiList = fenleiService.getAll(storeId);
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Fenlei fenlei:fenleiList){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(fenlei.getCategoryId());
            sysCode.setCodevalue(fenlei.getCategoryName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
}
