package com.xingrongjinfu.statistics.pays.controller;

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
import com.xingrongjinfu.statistics.pays.common.PaysConstant;
import com.xingrongjinfu.statistics.pays.service.IPayService;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class PayController extends BaseController {
	@Autowired
	private IPayService paysService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(PaysConstant.PAYS_URL)
	public ModelAndView loadStatisticsPays()

	{
		ModelAndView modelAndView = this.getModelAndView(PaysConstant.PAYS_PAGE);

		// 支付类型1支付宝2微信支付3银联4京东白条
		List<SysCode> sysCodeList = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("支付宝支付");
		sysCodeList.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("2");
		sysCode2.setCodevalue("微信支付");
		sysCodeList.add(sysCode2);

		SysCode sysCode3 = new SysCode();
		sysCode3.setCodeid("3");
		sysCode3.setCodevalue("银联支付");
		sysCodeList.add(sysCode3);

		SysCode sysCode4 = new SysCode();
		sysCode4.setCodeid("4");
		sysCode4.setCodevalue("京东白条支付");
		sysCodeList.add(sysCode4);

		modelAndView.addObject("payTypeList", sysCodeList);
		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(PaysConstant.PAYS_LIST_URL)
	public ModelAndView PaysList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<TableDataInfo> tableDataInfo = paysService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

}
