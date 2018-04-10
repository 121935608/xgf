package com.xingrongjinfu.commercial.storeApply.controller;

import java.util.Date;
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

import com.xingrongjinfu.commercial.storeApply.common.StoreApplyConstant;
import com.xingrongjinfu.commercial.storeApply.model.StoreApply;
import com.xingrongjinfu.commercial.storeApply.service.IStoreApplyService;
import com.xingrongjinfu.commercial.supplierApply.common.SupplierApplyConstant;

@Controller
@RequestMapping(SupplierApplyConstant.MERCHANT_URL)
public class StoreApplyController extends BaseController{
    @Autowired
    private IStoreApplyService storeApplyService;
    /**
     * 跳转用户登记界面
     */
    @RequestMapping(StoreApplyConstant.STOREAPPLY_URL)
    public ModelAndView storeApplyView() {
        ModelAndView modelAndView = this.getModelAndView(StoreApplyConstant.STOREAPPLY_PAGE);
        return modelAndView;
    }
    /**
     * 跳转处理界面
     */
    @RequestMapping(StoreApplyConstant.TO_STOREAPPLY_MODIFY)
    public ModelAndView toStoreApplyModify(String applyId) {
        ModelAndView modelAndView = this.getModelAndView(StoreApplyConstant.STOREAPPLY_MODIFY_PAGE);
        modelAndView.addObject("applyId", applyId);
        return modelAndView;
    }
    /**
     * 查询列表
     */
    @RequestMapping(StoreApplyConstant.STOREAPPLY_LIST)
    public ModelAndView CashierManageList() {

        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        
        List<TableDataInfo> tableDataInfo = storeApplyService.pageInfoQuery(pageUtilEntity);

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
    @RequestMapping(StoreApplyConstant.STOREAPPLY_MODIFY)
    public @ResponseBody Message modifySupplierApply(StoreApply storeApply) {
        storeApply.setDealStatus(1);
        storeApply.setDealUser(getCurrentUser().getUserName());
        storeApply.setDealTime(new Date());
        int result = storeApplyService.updateStoreApply(storeApply);
        return new Message(result);
    }
}
