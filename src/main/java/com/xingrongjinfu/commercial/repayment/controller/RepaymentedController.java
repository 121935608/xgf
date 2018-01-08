package com.xingrongjinfu.commercial.repayment.controller;

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

import com.xingrongjinfu.commercial.CommercialConstant;
import com.xingrongjinfu.commercial.repayment.common.RepaymentedConstant;
import com.xingrongjinfu.commercial.repayment.model.Repayment;
import com.xingrongjinfu.commercial.repayment.service.IRepaymentedService;

import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.model.User;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(CommercialConstant.COMMERCIAL_URL)
public class RepaymentedController extends BaseController {

	@Autowired
	private IRepaymentedService repaymentedService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(RepaymentedConstant.REPAYMENT_URL)
	public ModelAndView loadCommercialRepayment() {
		ModelAndView modelAndView = this.getModelAndView(RepaymentedConstant.REPAYMENT_PAGE);

		// 0 待还款 1 已还款 
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
	 * 查询列表
	 */
	@RequestMapping(RepaymentedConstant.REPAYMENT_LIST_URL)
	public ModelAndView RepaymentList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		
		String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
        if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
        	try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }

		List<TableDataInfo> tableDataInfo = repaymentedService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}
}
