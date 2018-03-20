package com.xingrongjinfu.commercial.supplierApply.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.commercial.supplierApply.common.SupplierApplyConstant;
import com.xingrongjinfu.commercial.supplierApply.model.SupplierApply;
import com.xingrongjinfu.commercial.supplierApply.service.ISupplierApplyService;

@Controller
@RequestMapping(SupplierApplyConstant.MERCHANT_URL)
public class SupplierApplyController extends BaseController {
    @Autowired
    private ISupplierApplyService supplierApplyService;
    /**
     * 跳转招商入驻申请列表界面
     */
    @RequestMapping(SupplierApplyConstant.SUPPLIERAPPLY_URL)
    public ModelAndView applyView() {
        ModelAndView modelAndView = this.getModelAndView(SupplierApplyConstant.SUPPLIERAPPLY_PAGE);
        return modelAndView;
    }
    /**
     * 跳转处理界面
     */
    @RequestMapping(SupplierApplyConstant.TO_SUPPLIERAPPLY_MODIFY)
    public ModelAndView toSupplierApplyModify(String applyId) {
        ModelAndView modelAndView = this.getModelAndView(SupplierApplyConstant.SUPPLIERAPPLY_MODIFY_PAGE);
        SupplierApply supplierApply = supplierApplyService.getById(applyId);
        modelAndView.addObject("supplierApply", supplierApply);
        return modelAndView;
    }
    /**
     * 查询列表
     */
    @RequestMapping(SupplierApplyConstant.SUPPLIERAPPLY_LIST)
    public ModelAndView CashierManageList() {

        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
        if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
            try {
                pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        List<TableDataInfo> tableDataInfo = supplierApplyService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    /**
     * Description: 处理<br/>
     *
     * @author huYL
     * @param register
     * @param picture
     * @return
     */
    @RequestMapping(SupplierApplyConstant.SUPPLIERAPPLY_MODIFY)
    public @ResponseBody Message modifySupplierApply(SupplierApply supplierApply) {
        supplierApply.setStatus(1);
        int result = supplierApplyService.updateSupplierApply(supplierApply);
        return new Message(result);
    }
}
