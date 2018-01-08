package com.xingrongjinfu.statistics.purchase.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.statistics.StatisticsConstant;
import com.xingrongjinfu.statistics.procurement.common.ProcurementConstant;
import com.xingrongjinfu.statistics.procurement.service.IProcurementService;
import com.xingrongjinfu.statistics.purchase.common.PurchaseConstant;
import com.xingrongjinfu.statistics.purchase.service.IPurchaseService;

/**
 *  业务处理
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
        return this.getModelAndView(PurchaseConstant.PURCHASE_PAGE);
    }
    
    /**
	 * 查询列表
	 */
	@RequestMapping(PurchaseConstant.PURCHASE_LIST_URL)
	public ModelAndView PurchaseList() {
		
		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
        	try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }

		List<TableDataInfo> tableDataInfo = purchaseService.pageInfoQuery(pageUtilEntity);

		return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}

}
