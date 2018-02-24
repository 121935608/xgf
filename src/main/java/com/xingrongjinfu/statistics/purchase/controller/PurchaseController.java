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
import com.xingrongjinfu.statistics.purchase.common.PurchaseConstant;
import com.xingrongjinfu.statistics.purchase.service.IPurchaseService;
import com.xingrongjinfu.system.user.model.User;

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

}
