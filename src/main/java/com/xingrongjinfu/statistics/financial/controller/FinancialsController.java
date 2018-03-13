package com.xingrongjinfu.statistics.financial.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.financial.common.FinancialConstant;
import com.xingrongjinfu.statistics.financial.service.IFinancialsService;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 业务处理   该模块目前没用
 * 
 * @author
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class FinancialsController extends BaseController {
	@Autowired
	private IFinancialsService financialService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(FinancialConstant.FINANCIAL_URL)
	public ModelAndView loadStatisticsFinancial() {
		ModelAndView modelAndView = this.getModelAndView(FinancialConstant.FINANCIAL_PAGE);

		//0:转账成功；1:转账失败
		List<SysCode> sysCodeList = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("0");
		sysCode1.setCodevalue("转账成功");
		sysCodeList.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("1");
		sysCode2.setCodevalue("转账失败");
		sysCodeList.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList);

		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(FinancialConstant.FINANCIAL_LIST_URL)
	public ModelAndView FinancialList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<TableDataInfo> tableDataInfo = financialService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

}
