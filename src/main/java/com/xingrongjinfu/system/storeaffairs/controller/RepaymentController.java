package com.xingrongjinfu.system.storeaffairs.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingrongjinfu.system.financial.common.FinancialConstant;
import com.xingrongjinfu.utils.ExportExcel;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.storeaffairs.commom.StoreaffairConstant;
import com.xingrongjinfu.system.storeaffairs.model.Repay;
import com.xingrongjinfu.system.storeaffairs.model.RepayDetail;
import com.xingrongjinfu.system.storeaffairs.service.IRepaymentService;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.UuidUtil;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 认证申请  处理
 * 
 * @author cj
 */
@Controller
@RequestMapping(SystemConstant.MERCHANT_URL)

class RepaymentController extends BaseController {
	@Autowired
    private IRepaymentService repaymentService;

	public String repayUrl;

    @Value("${repayUrl}")
    public void setRepayUrl(String repayUrl) {
        this.repayUrl = repayUrl;
    }
	

    /**
     * 跳转还款界面
     */
    @RequestMapping(StoreaffairConstant.REPAYMENT_URL)
    public ModelAndView repaymentPage()
    { 
        ModelAndView modelAndView = this.getModelAndView(StoreaffairConstant.REPAYMENT_PAGE);
        String type = (String) SessionUtils.getSession().getAttribute("type");
        modelAndView.addObject("type", type);
        return modelAndView;
    }
    /**
     * 跳转还款对账界面
     */
    @RequestMapping(StoreaffairConstant.TO_REPAY_MODIFY_URL)
    public ModelAndView toRepayModify(String repayId)
    { 
        ModelAndView modelAndView = this.getModelAndView(StoreaffairConstant.REPAY_MODIFY_PAGE);
        Repay repay = repaymentService.getByRepayId(repayId);
        modelAndView.addObject("repay", repay);
        return modelAndView;
    }
    /**
     * 查询还款列表
     */
    @RequestMapping(StoreaffairConstant.REPAYMENT_LIST)
    public ModelAndView modifyRepay()
    {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        String type = (String) SessionUtils.getSession().getAttribute("type");
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        pageUtilEntity.getRelationMap().put("type", type);
        String fuzzyCondition=pageUtilEntity.getRelationMap().get("fuzzyCondition");
        if(fuzzyCondition!=null&&!fuzzyCondition.equals("")){
        	try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }
        List<TableDataInfo> tableDataInfo = repaymentService.repaymentListQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    @RequestMapping(FinancialConstant.DOWNLOAD_FINANCIAL_DATA)
    public Message downloadFinancialData(HttpServletRequest request, HttpServletResponse response) {


        User user=this.getCurrentUser();
        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        String type = (String) SessionUtils.getSession().getAttribute("type");
        param.put("storeId", storeId);
        param.put("type", type);
        param.put("fuzzyCondition", request.getParameter("fuzzyCondition"));
        param.put("dateType", request.getParameter("dateType"));
        param.put("status", request.getParameter("status"));


        List<Map> data = repaymentService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            String[][] headers = new String[][]{{"还款单号", "创建时间", "订单号", "商铺名称", "还款金额（元）", "已还金额（元）", "结余金额（元）"},
                                        {"repayNo", "addTime", "orderNumber", "storeName", "planTotal", "repayMoney", "withholdMoney"}};

            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("还款计划表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }
//    /**
//     * 对账保存
//     */
//    @RequestMapping(StoreaffairConstant.REPAY_MODIFY)
//    public @ResponseBody Message modifyRepay(Repay repay,Integer type) {
//        User user = getCurrentUser();
//        repay.setRepayMoney(repay.getDueFee().multiply(new BigDecimal(100)).add(repay.getRepayMoney().multiply(new BigDecimal(100))));
//        repay.setRepayDate(new Date());
//        repay.setUpdateTime(new Date());
//        if(type == 1){
//            repay.setStatus(1);
//            repay.setRepayType(2);
//        }else if(type == 0){
//            repay.setStatus(0);
//            repay.setRepayType(2);
//        }
//
//        RepayDetail repayDetail = new RepayDetail();
//        repayDetail.setRepayDetailId(UuidUtil.get32UUID());
//        repayDetail.setRepayId(repay.getRepayId());
//        if(null != repay.getRemark())
//            repayDetail.setRemark(repay.getRemark());
//        repayDetail.setRepayMoney(repay.getDueFee().multiply(new BigDecimal(100)));
//        repayDetail.setUserId(user.getUserId());
//        repayDetail.setRepayType(2);
//        repayDetail.setRepayTime(new Date());
//        int n = repaymentService.updateRepay(repay,repayDetail);
//        return new Message(n);
//    }


    /**
     * 对账保存
     */
    @RequestMapping(StoreaffairConstant.REPAY_MODIFY)
    public @ResponseBody Message modifyRepay(Repay repay,Integer type)throws Exception {
        User user = getCurrentUser();
        String userId=repay.getUserId();
        Repay repay1=repaymentService.getByRepayId(repay.getRepayId());
        String orderNumber=repaymentService.getOrderNumber(repay.getRepayId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String payTime=sdf.format(new Date());
        BigDecimal surplusMoney=repay.getPlanTotal().multiply(new BigDecimal(100));
        HashMap map=new HashMap();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("orderNumber",orderNumber);
        jsonObject.put("payTime",payTime);
        jsonObject.put("repayMoney",repay.getPlanTotal().multiply(new BigDecimal(100)));
        jsonObject.put("restMoney",surplusMoney);
        map.put("params",jsonObject);
        String result1= HttpClientUtil.httpPostRequest(repayUrl,map);
        JSONObject jsonObject1= net.sf.json.JSONObject.fromObject(result1);
        String code=jsonObject1.getString("code");
        String msg=jsonObject1.getString("msg");
        if("0000".equals(code)){
//        System.out.println(result1);
        repay.setRepayMoney(repay.getPlanTotal().multiply(new BigDecimal(100)));
        repay.setWithholdMoney(surplusMoney);
        repay.setRepayDate(new Date());
        repay.setUpdateTime(new Date());
        repay.setStatus(1);
        repay.setRepayType(2);

        RepayDetail repayDetail = new RepayDetail();
        repayDetail.setRepayDetailId(UuidUtil.get32UUID());
        repayDetail.setRepayId(repay.getRepayId());
        if(null != repay.getRemark())
        repayDetail.setRemark(repay.getRemark());
        repayDetail.setRepayMoney(repay.getPlanTotal().multiply(new BigDecimal(100)));
        repayDetail.setUserId(user.getUserId());
        repayDetail.setRepayType(2);
        repayDetail.setRepayTime(new Date());
        int n = repaymentService.updateRepay(repay,repayDetail);
        return new Message(n);
        }else{
            return new Message(code,msg);
        }
    }


    /**
     * 还款明细
     */
    @RequestMapping(StoreaffairConstant.REPAY_DETAIL)
    public @ResponseBody List<RepayDetail> getRepayDetail(String repayId) {
        List<RepayDetail> repayDetail = repaymentService.getRepayDetail(repayId);
        return repayDetail;
    }
    
}
