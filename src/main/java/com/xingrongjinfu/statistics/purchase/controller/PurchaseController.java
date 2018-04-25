package com.xingrongjinfu.statistics.purchase.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.purchase.model.Purchase;
import com.xingrongjinfu.statistics.purchase.model.SPurchaseDto;
import com.xingrongjinfu.system.sale.common.SaleConstant;
import com.xingrongjinfu.system.sale.model.Sale;
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
import com.xingrongjinfu.statistics.purchase.common.PurchaseConstant;
import com.xingrongjinfu.statistics.purchase.service.IPurchaseService;
import com.xingrongjinfu.system.user.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  业务处理   采购统计表（收银端）
 * 
 * @author 
 */
@Controller
@RequestMapping(StatisticsConstant.STATISTICS_URL)
public class PurchaseController extends BaseController
{
	@Autowired
	private IPurchaseService purchaseService;


    /**
     * 跳转列表界面
     */
    @RequestMapping(PurchaseConstant.PURCHASE_URL)
    public ModelAndView loadStatisticsPurchase()
    {
    	ModelAndView modelAndView=this.getModelAndView(PurchaseConstant.PURCHASE_PAGE);
    	return modelAndView;
    }
    
    /**
	 * 查询列表
	 */
	@RequestMapping(PurchaseConstant.PURCHASE_LIST_URL)
	public ModelAndView PurchaseList() {
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
		List<TableDataInfo> tableDataInfo = purchaseService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

	@RequestMapping(PurchaseConstant.PURCHASE_PUR_DATA)
	public Message downloadPurData(HttpServletRequest request, HttpServletResponse response) {

		User user=this.getCurrentUser();
		Map<String, String> param = new HashMap();
		param.put("userId", user.getUserId());
		param.put("beginTime", request.getParameter("beginTime"));
		param.put("endTime", request.getParameter("endTime"));
		param.put("fuzzyCondition", request.getParameter("fuzzyCondition"));

		List<SPurchaseDto> data = purchaseService.infoQuery(param);
		//data.stream().filter(s->s.getSalePrice())
		try {
			ExportExcel<SPurchaseDto> ee = new ExportExcel<>();
			String[] headers = new String[]{"商品名称", "商品条码", "单位", "采购数量", "采购金额"};
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
			OutputStream output = response.getOutputStream();
			ee.exportExcel("采购统计表", headers, data, output,"yyyy-MM-dd");
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(0);
		}
		return new Message(1);
	}

}
