package com.xingrongjinfu.statistics.procurement.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.commercial.repayment.common.RepaymentedConstant;
import com.xingrongjinfu.commercial.repayment.model.Repayment;
import com.xingrongjinfu.commercial.repayment.service.IRepaymentedService;
import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.procurement.common.ProcurementConstant;
import com.xingrongjinfu.statistics.procurement.service.IProcurementService;

/**
 *  业务处理
 * 
 * @author 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class ProcurementController extends BaseController
{
	@Autowired
	private IProcurementService procurementService;

    /**
     * 跳转列表界面
     */
    @RequestMapping(ProcurementConstant.PROCUREMENT_URL)
    public ModelAndView loadStatisticsProcurement()
    {
        return this.getModelAndView(ProcurementConstant.PROCUREMENT_PAGE);
    }
    
    /**
	 * 查询列表
	 */
	@RequestMapping(ProcurementConstant.PROCUREMENT_LIST_URL)
	public ModelAndView ProcurementList() {
		
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
        	try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }
		List<TableDataInfo> tableDataInfo = procurementService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

}
