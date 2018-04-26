package com.xingrongjinfu.statistics.pays.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.pays.model.Pays;
import com.xingrongjinfu.statistics.pays.model.PaysDto;
import com.xingrongjinfu.statistics.procurement.common.ProcurementConstant;
import com.xingrongjinfu.statistics.procurement.model.ProcurementDto;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ExportExcel;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.pays.common.PaysConstant;
import com.xingrongjinfu.statistics.pays.service.IPayService;
import com.xingrongjinfu.system.syscode.model.SysCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 业务处理   收银支付流水（收银端）
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

		// 支付类型下拉框
		List<SysCode> sysCodeList = new ArrayList<SysCode>();
		SysCode sysCode0 = new SysCode();
		sysCode0.setCodeid("0");
		sysCode0.setCodevalue("现金");
		sysCodeList.add(sysCode0);

		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("支付宝支付");
		sysCodeList.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("2");
		sysCode2.setCodevalue("微信支付");
		sysCodeList.add(sysCode2);

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
		String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
		pageUtilEntity.getRelationMap().put("storeId", storeId);
		List<TableDataInfo> tableDataInfo = paysService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	@RequestMapping(PaysConstant.DOWNLOAD_PAY_DATA)
	public Message downloadProcurementData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> param = new HashMap();
		String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
		param.put("storeId", storeId);
		param.put("beginTime", request.getParameter("beginTime"));
		param.put("endTime", request.getParameter("endTime"));
		param.put("payType", request.getParameter("payType"));
		param.put("fuzzyCondition", request.getParameter("fuzzyCondition"));

		List<PaysDto> data = paysService.infoQuery(param);
		//data.stream().filter(s->s.getSalePrice())
		try {
			ExportExcel<PaysDto> ee = new ExportExcel<>();
			String[] headers = new String[]{"交易号", "创建时间", "收款金额", "支付方式", "流水号", "收银员"};
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
			OutputStream output = response.getOutputStream();
			ee.exportExcel("银行支付流水表", headers, data, output,"yyyy-MM-dd");
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(0);
		}
		return new Message(1);
	}
}
