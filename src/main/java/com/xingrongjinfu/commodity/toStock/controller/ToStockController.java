package com.xingrongjinfu.commodity.toStock.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.xingrongjinfu.commodity.register.common.RegisterConstant;
import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.toStock.common.ToStockConstant;
import com.xingrongjinfu.commodity.toStock.service.IToStockService;
import com.xingrongjinfu.commodity.unit.service.IUnitService;
import com.xingrongjinfu.statistics.purchase.model.Purchase;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.model.User;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(RegisterConstant.COMMODITY_URL)
public class ToStockController extends BaseController{
    @Autowired
    private IToStockService toStockService;
    @Autowired
    private IUnitService unitService;
    /**
     * Description: 跳转入库列表页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(ToStockConstant.TOSTOCK_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(ToStockConstant.TOSTOCK_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转入库<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(ToStockConstant.TOSTOCK_MODIFY_URL)
    public ModelAndView toStockModify(String commodityId) throws Exception{
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        ModelAndView modelAndView = new ModelAndView(ToStockConstant.TOSTOCK_MODIFY_PAGE);
        //获取商品信息
        Map map = new HashMap<>();
        map.put("userId", this.getCurrentUser().getUserId());
        map.put("commodityId", commodityId);
        Purchase purchase = toStockService.getCommodityById(map);
        modelAndView.addObject("purchase", purchase);
        //获取单位列表
        List<SysCode> unitList= unitService.getUnitList(storeId);
        JSONArray jaProUnit=JSONArray.fromObject(unitList);
        modelAndView.addObject("unitList",jaProUnit);
        //获取商品名称列表
        List<String> commodityList = toStockService.getAllCommodity(storeId);
        JSONArray jaProCommodity=JSONArray.fromObject(commodityList);
        modelAndView.addObject("commodityList",jaProCommodity);
        return modelAndView;
    }
    
    /**
     * 查询列表
     */
    @RequestMapping(ToStockConstant.TOSTOCK_LIST_URL)
    public ModelAndView toStockList() {
        User user=this.getCurrentUser();
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
        if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
            try {
                pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
            } catch (UnsupportedEncodingException e) { 
                e.printStackTrace();
            }
        }
        pageUtilEntity.getRelationMap().put("userId", user.getUserId());
        List<TableDataInfo> tableDataInfo = toStockService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    /**
     * 通过商品编号加载商品信息
     */
    @RequestMapping(ToStockConstant.LOAD_COMMODITY_URL)
    public @ResponseBody Register loadCommodity(String commodityName) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Map map = new HashMap<>();
        map.put("commodityName", commodityName);
        map.put("storeId", storeId);
        Register register = toStockService.loadCommodity(map);
        return register;
    }
    /**
     * 入库
     */
    @RequestMapping(ToStockConstant.ADD_COMMODITY_URL)
    public @ResponseBody Message addCommodityToStock(String orders,String no) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Map map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("userId", this.getCurrentUser().getUserId());
        map.put("no",no);
        return toStockService.updateCommodity(map,orders);
    }
}
