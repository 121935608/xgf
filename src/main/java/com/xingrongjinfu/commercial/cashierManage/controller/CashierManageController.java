package com.xingrongjinfu.commercial.cashierManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.commercial.CommercialConstant;
import com.xingrongjinfu.commercial.cashierManage.common.CashierManageConstant;
import com.xingrongjinfu.commercial.cashierManage.model.CashierManage;
import com.xingrongjinfu.commercial.cashierManage.service.ICashierManageService;
import com.xingrongjinfu.commodity.label.common.LabelConstant;
import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.system.role.common.RoleConstant;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.common.UserConstant;
import com.xingrongjinfu.system.user.model.User;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(CommercialConstant.COMMERCIAL_URL)
public class CashierManageController extends BaseController {

	@Autowired
	private ICashierManageService cashierManageService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(CashierManageConstant.CASHIER_URL)
	public ModelAndView loadCommercialCashierManage() {
		ModelAndView modelAndView = this.getModelAndView(CashierManageConstant.CASHIER_PAGE);
		return modelAndView;
	}

	/**
	 * 跳转新增界面
	 */
	@RequestMapping(CashierManageConstant.TO_ADD_URL)
	public ModelAndView toCashierManageAdd(String roleId) {
		ModelAndView modelAndView = this.getModelAndView(CashierManageConstant.ADD_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("0");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("1");
		sysCode2.setCodevalue("禁用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);

		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(CashierManageConstant.CASHIER_LIST_URL)
	public ModelAndView CashierManageList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		User user = this.getCurrentUser();
		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageUtilEntity.getRelationMap().put("userId", user.getUserId());
		List<TableDataInfo> tableDataInfo = cashierManageService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "收银员管理", action = "收银员管理-保存收银员", isSaveRequestData = true)
	@RequestMapping(CashierManageConstant.SAVE_CASHIER_URL)
	public @ResponseBody Message saveCashierManage(CashierManage cashierManage) {
		int result = 0;
		String cashierId = cashierManage.getCashierId();

		if (cashierId == null) {
			cashierManage.setUserId(this.getCurrentUser().getUserId());
			result = cashierManageService.addCashierManageInfo(cashierManage);
		}

		return new Message(result);
	}
	
	
	/**
	 * 启动/停用 操作
	 */
	@ActionControllerLog(title = "收银员管理", action = "收银员管理-启用/停用", isSaveRequestData = true)
	@RequestMapping(CashierManageConstant.CHANGE_STATUS_URL)
	public @ResponseBody Message changeCashierManageStatus(CashierManage cashierManage) {
		int result = 0;
		String id = cashierManage.getCashierId();
		if (id != null) {
			result = cashierManageService.changeCashierManageStatus(cashierManage);
		}
		return new Message(result);
	}
}
