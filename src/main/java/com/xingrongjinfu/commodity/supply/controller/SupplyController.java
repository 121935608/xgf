package com.xingrongjinfu.commodity.supply.controller;

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

import com.xingrongjinfu.commodity.CommodityConstant;
import com.xingrongjinfu.commodity.supply.common.SupplyConstant;
import com.xingrongjinfu.commodity.supply.model.Supply;
import com.xingrongjinfu.commodity.supply.service.ISupplyService;
import com.xingrongjinfu.utils.StringUtil;
import com.xingrongjinfu.utils.UuidUtil;

@Controller
@RequestMapping(CommodityConstant.COMMODITY_URL)
public class SupplyController extends BaseController{
    @Autowired
    ISupplyService supplyService;
    /**
     * Description: 跳转供应商页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(SupplyConstant.TO_SUPPLY_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(SupplyConstant.COMMODITY_SUPPLY_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转供应商修改页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(SupplyConstant.TO_REGISTER_MODIFY)
    public ModelAndView toSupplyModify(String supplierCode) throws Exception{
        ModelAndView modelAndView = new ModelAndView(SupplyConstant.SUPPLY_MODIFY_PAGE);
        Supply supply = supplyService.getByCode(supplierCode);
        modelAndView.addObject("supply", supply);
        return modelAndView;
    }
    /**
     * Description: 跳转供应商添加页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(SupplyConstant.TO_REGISTER_ADD)
    public ModelAndView toSupplyAdd(String supplyCode) throws Exception{
        ModelAndView modelAndView = new ModelAndView(SupplyConstant.SUPPLY_ADD_PAGE);
        return modelAndView;
    }
    
    /**
     * 查询供应商列表
     */
    @RequestMapping(SupplyConstant.SUPPLY_LIST)
    public ModelAndView supplyList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = supplyService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    /**
     * 修改供应商信息
     */
    @RequestMapping(SupplyConstant.SUPPLY_MODIFY)
    public @ResponseBody Message modifySupply(Supply supply) {
        String phone = supply.getPhone();
        if(!StringUtil.checkPhone(phone)){
            return new Message(false,"请输入正确的手机号!");
        }
        int n = supplyService.updateSupply(supply);
        
        return new Message(n);
    }
    /**
     * 供应商信息保存
     */
    @RequestMapping(SupplyConstant.SUPPLY_SAVE)
    public @ResponseBody Message saveSupply(Supply supply) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        String phone = supply.getPhone();
        if(!StringUtil.checkPhone(phone)){
            return new Message(false,"请输入正确的手机号!");
        }
        supply.setSupplierId(UuidUtil.get32UUID());
        supply.setStoreId(storeId);
        supply.setSupplierCode(StringUtil.getNo("S"));
        int n = supplyService.addSupply(supply);
        return new Message(n);
    }
    /**
     * 修改供应商状态
     */
    @RequestMapping(SupplyConstant.CHANGE_STATUS)
    public @ResponseBody Message changeSupplyStatus(String supplierCode,Integer status) {
        Supply supply = new Supply();
        supply.setSupplierCode(supplierCode);
        supply.setStatus(status);
        int n = supplyService.changeSupplyStatus(supply);
        
        return new Message(n);
    }
    /**
     * 供应商名是否存在
     */
    @RequestMapping(SupplyConstant.CHECK_SUPPLY_NAME)
    public @ResponseBody int checkSupplyName(String supplierName,String supplierCode) {
        Map map = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        map.put("supplierName", supplierName);
        map.put("storeId", storeId);
        if(null != supplierCode){
            map.put("supplierCode", supplierCode);
        }
        int n = supplyService.getByName(map);
        return n;
    }
}
