package com.xingrongjinfu.commodity.unit.controller;

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
import com.xingrongjinfu.commodity.unit.common.UnitConstant;
import com.xingrongjinfu.commodity.unit.model.Unit;
import com.xingrongjinfu.commodity.unit.service.IUnitService;
import com.xingrongjinfu.utils.StringUtil;
import com.xingrongjinfu.utils.UuidUtil;

@Controller
@RequestMapping(CommodityConstant.COMMODITY_URL)
public class UnitController extends BaseController{
    @Autowired
    private IUnitService unitService;
    /**
     * Description: 跳转单位管理页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(UnitConstant.TO_UNIT_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(UnitConstant.COMMODITY_UNIT_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转单位管理添加页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(UnitConstant.TO_UNIT_ADD_URL)
    public ModelAndView toSupplyAdd(String supplyCode) throws Exception{
        ModelAndView modelAndView = new ModelAndView(UnitConstant.UNIT_ADD_PAGE);
        return modelAndView;
    }
    /**
     * Description: 跳转单位修改页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(UnitConstant.TO_UNIT_MODIFY_URL)
    public ModelAndView toSupplyModify(String unitCode) throws Exception{
        ModelAndView modelAndView = new ModelAndView(UnitConstant.UNIT_MODIFY_PAGE);
        Unit unit = unitService.getByCode(unitCode);
        modelAndView.addObject("unit", unit);
        return modelAndView;
    }
    /**
     * 查询供应商列表
     */
    @RequestMapping(UnitConstant.UNIT_LIST)
    public ModelAndView unitList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = unitService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    /**
     * 修改单位信息
     */
    @RequestMapping(UnitConstant.MODIFY_UNIT)
    public @ResponseBody Message modifySupply(Unit unit) {
        int n = unitService.updateUnit(unit);
        
        return new Message(n);
    }
    /**
     * 单位信息保存
     */
    @RequestMapping(UnitConstant.SAVE_UNIT)
    public @ResponseBody Message saveUnit(Unit unit) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        unit.setUnitId(UuidUtil.get32UUID());
        unit.setUnitCode(StringUtil.getNo("U"));
        unit.setStoreId(storeId);
        unit.setStatus(1);
        int n = unitService.addUnit(unit);
        return new Message(n);
    }
    /**
     * 单位名是否存在
     */
    @RequestMapping(UnitConstant.CHECK_UNITNAME)
    public @ResponseBody int checkUnitName(String unitName,String unitCode) {
        Map map = new HashMap();
        map.put("unitName", unitName);
        if(null != unitCode){
            map.put("unitCode", unitCode);
        }
        int n = unitService.getByName(map);
        return n;
    }
}
