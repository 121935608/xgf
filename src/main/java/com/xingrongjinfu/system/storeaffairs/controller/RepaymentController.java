package com.xingrongjinfu.system.storeaffairs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant; 
import com.xingrongjinfu.system.storeaffairs.commom.StoreaffairConstant;  
import com.xingrongjinfu.system.storeaffairs.service.ICertificationService;
import com.xingrongjinfu.system.storeaffairs.service.IRepaymentService;
import com.xingrongjinfu.system.syscode.model.SysCode; 


/**
 * 认证申请  处理
 * 
 * @author cj
 */
@Controller
@RequestMapping(SystemConstant.MERCHANT_URL)

class RepaymentController extends BaseController {
	@Autowired
    private IRepaymentService repaymentService;
	

    /**
     * 跳转认证申请列表界面
     */
    @RequestMapping(StoreaffairConstant.REPAYMENT_URL)
    public ModelAndView repaymentPage()
    { 
        ModelAndView modelAndView = this.getModelAndView(StoreaffairConstant.REPAYMENT_PAGE);

      //（1:待支付；2:待发货；3:待收货；4:待还款；5:已还款）
        List<SysCode> sysCodeList1 = new ArrayList<SysCode>(); 
            SysCode sysCode1 = new SysCode();
            sysCode1.setCodeid("0"); 
            sysCode1.setCodevalue("待还款");
            sysCodeList1.add(sysCode1);
             
            SysCode sysCode2 = new SysCode();
            sysCode2.setCodeid("1");
            sysCode2.setCodevalue("已还款");
            sysCodeList1.add(sysCode2); 
             
        modelAndView.addObject("repaymentStatusList", sysCodeList1);
        
        List<SysCode> dateTypeList = new ArrayList<SysCode>(); 
            SysCode sysCode3 = new SysCode();
            sysCode3.setCodeid("addTime"); 
            sysCode3.setCodevalue("创建时间");
            dateTypeList.add(sysCode3);
             
            SysCode sysCode4 = new SysCode();
            sysCode4.setCodeid("repayDate");
            sysCode4.setCodevalue("还款日期");
            dateTypeList.add(sysCode4); 
             
        modelAndView.addObject("dateTypeList", dateTypeList);
         
        return modelAndView;
    }

   
    /**
     * 查询认证申请列表
     */
    @RequestMapping(StoreaffairConstant.REPAYMENT_LIST)
    public ModelAndView repaymentList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
        if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
        	try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }
        List<TableDataInfo> tableDataInfo = repaymentService.repaymentListQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
 
    
}
