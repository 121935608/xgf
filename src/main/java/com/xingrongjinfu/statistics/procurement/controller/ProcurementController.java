package com.xingrongjinfu.statistics.procurement.controller;


import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.utils.ExportExcel;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.procurement.common.ProcurementConstant;
import com.xingrongjinfu.statistics.procurement.service.IProcurementService;
import com.xingrongjinfu.system.user.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  业务处理   采购登记表（收银端）
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
    	
    	ModelAndView modelAndView=this.getModelAndView(ProcurementConstant.PROCUREMENT_PAGE);
        return modelAndView;
    }
    
    /**
	 * 查询列表
	 */
	@RequestMapping(ProcurementConstant.PROCUREMENT_LIST_URL)
	public ModelAndView ProcurementList() {
		
		User user=this.getCurrentUser();
		
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

		String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
        	try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }
		
		pageUtilEntity.getRelationMap().put("userId", user.getUserId());
		List<TableDataInfo> tableDataInfo = procurementService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	@RequestMapping(ProcurementConstant.DOWNLOAD_PROCUREMENT_DATA)
	public Message downloadProcurementData(HttpServletRequest request, HttpServletResponse response) {

		User user=this.getCurrentUser();
		Map<String, String> param = new HashMap();
		param.put("userId", user.getUserId());
		param.put("beginTime", request.getParameter("beginTime"));
		param.put("endTime", request.getParameter("endTime"));
		param.put("fuzzyCondition", request.getParameter("fuzzyCondition"));

		List<Map> data = procurementService.infoQuery(param);
		//data.stream().filter(s->s.getSalePrice())
		try {
			String[][] headers = new String[][]{{"商品名称", "商品条码", "订单号", "创建时间", "单位", "采购数量", "采购金额"},
												{"commodityName", "commodityNo", "orderNumber", "payTime", "unit", "commodityNum", "totalPrice"}};
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
			OutputStream output = response.getOutputStream();
			ExportExcel.exportExcel2("采购统计表", headers, data, output);
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(0);
		}
		return new Message(1);
	}

}
