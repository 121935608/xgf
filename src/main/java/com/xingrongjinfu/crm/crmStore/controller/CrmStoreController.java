package com.xingrongjinfu.crm.crmStore.controller;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.crmStore.common.CrmStoreConstant;
import com.xingrongjinfu.crm.crmStore.model.Order;
import com.xingrongjinfu.crm.crmStore.service.CrmStoreService;
import com.xingrongjinfu.crm.information.dao.InformationDao;
import com.xingrongjinfu.crm.information.model.Information;
import com.xingrongjinfu.crm.information.service.InformatioinService;
import com.xingrongjinfu.crm.visit.model.Visit;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.ExportExcelSeedBack;
import com.xingrongjinfu.utils.TimeUtils;
import com.xingrongjinfu.utils.UuidUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(CrmConstant.CRM_URL_STORE)
public class CrmStoreController extends BaseController {

    @Autowired
    private InformatioinService informatioinService;
    @Autowired
    private CrmStoreService crmStoreService;
    /*跳转查询门店界面，并且查询门店信息*/
    @RequestMapping(CrmStoreConstant.CRM_STORE_URL)
    public ModelAndView storeCrm(){
        ModelAndView modelAndView = this.getModelAndView(CrmStoreConstant.CRM_STORE_SELECT_PAGE);
        /*查询所有督导员*/
        //审核下拉框 未认证:WRZ  待审核:0 审核不通过:1 审核通过:2
        modelAndView.addObject("ispass", getCRMStatus());
        //获取select框数据
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Supervisor supervisor : getCRMSupervisorSelection())
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(supervisor.getSupervisorNum());
            sysCode.setCodevalue(supervisor.getName());
            sysCodeList.add(sysCode);
        }
        modelAndView.addObject("supervisorList", sysCodeList);
        return modelAndView;
    }

    /*跳转查询门店界面，并且查询门店信息*/
    @RequestMapping(CrmStoreConstant.CRM_STORE_SELECT_URL)
    public ModelAndView selectStoreSu(){
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = crmStoreService.selectStoreSup(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /*跳转查询门店界面，并且查询门店信息*/
    @RequestMapping(CrmStoreConstant.CRM_STORE_CHECK_URL)
    public ModelAndView checkStoreSu(String storeId){
        ModelAndView modelAndView = this.getModelAndView(CrmStoreConstant.CRM_STORE_CHECK_PAGE);
        //获取商户信息
        //List<Store> storeList=getStoreInfo(this.getRequest().getParameter("storeId"));
        Store s=getCrmStoreInfo(storeId);
        //licensePic,frontStorePic,innerStorePic,contractPic,transactionPic,utilitiesPic
        s.setLicensePic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getLicensePic());
        s.setFrontStorePic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getFrontStorePic());
        s.setInnerStorePic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getInnerStorePic());
        s.setContractPic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getContractPic());
        s.setTransactionPic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getTransactionPic());
        s.setUtilitiesPic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getUtilitiesPic());
        modelAndView.addObject("store",s);
        modelAndView.addObject("bankaccount", getCRMBankAccountInfo(storeId));


        //获取select框数据
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Supervisor supervisor : getCRMSupervisorSelection())
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(supervisor.getSupervisorNum()+"");
            sysCode.setCodevalue(supervisor.getName());
            sysCodeList.add(sysCode);
        }
        modelAndView.addObject("supervisorList", sysCodeList);

        //审核下拉框      未认证:WRZ  待审核:0 审核不通过:1 审核通过:2
        List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
        SysCode sysCode1 = new SysCode();
        sysCode1.setCodeid("NOPASS");
        sysCode1.setCodevalue("审核不通过");
        sysCodeList1.add(sysCode1);

        SysCode sysCode2 = new SysCode();
        sysCode2.setCodeid("PASS");
        sysCode2.setCodevalue("审核通过");
        sysCodeList1.add(sysCode2);
        modelAndView.addObject("ispass", sysCodeList1);

        return modelAndView;
    }

    /**
     * 获取商户信息
     */
    public  Store getCrmStoreInfo(String storeid)
    {
        return crmStoreService.getCRMStoreInfo(storeid);
    }

    /**
     * 获取银行账户信息
     */
    public BankAccount getCRMBankAccountInfo(String storeid)
    {
        return crmStoreService.getCRMBankAccountInfo(storeid);
    }

    /**
     * 获取申请列表页面督导员下拉框数据
     */
    public  List<Supervisor>  getCRMSupervisorSelection()
    {
        return  crmStoreService.getCRMSupervisorList();
    }

    /*跳转到门店分配业务员界面*/
    @RequestMapping(CrmStoreConstant.CRM_STORE_SUPERVISTOR_URL)
    public ModelAndView crmStoreSupervistor(String storeId){
        ModelAndView modelAndView = this.getModelAndView(CrmStoreConstant.CRM_STORE_SUPERVISTOR_PAGE);
        Store s = getCrmStoreInfo(storeId);
        modelAndView.addObject("store",s);
        return  modelAndView;
    }

    /*门店分配业务员接口*/
    @RequestMapping(CrmStoreConstant.CRM_UPDATE_STORE_SUPERVISTOR_URL)
    public @ResponseBody Message updateStoreSupervisor(Store store){
        int result = 0;
        System.out.println(store.getStoreId()+"和"+store.getSupervisorId());
        if(store.getStoreId() != null){
            result = crmStoreService.updateStoreSupervistor(store);
        }
        Information information = new Information();
        information.setInfoId(UuidUtil.get32UUID());
        information.setInfoHeadline("门店更新");
        information.setSupervisorNum(store.getSupervisorId());
        information.setInfoContent("新增一条私海门店!");
        information.setInfoStatus("0");
        int addInfor = informatioinService.addInfor(information);
        return new Message(result);
    }

    /*审核状态存list*/
    public List<SysCode> getCRMStatus(){
        List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
        SysCode sysCode1 = new SysCode();
        sysCode1.setCodeid("WRZ");
        sysCode1.setCodevalue("未认证");
        sysCodeList1.add(sysCode1);

        SysCode sysCode2 = new SysCode();
        sysCode2.setCodeid("APRING");
        sysCode2.setCodevalue("认证审核中");
        sysCodeList1.add(sysCode2);

        SysCode sysCode3 = new SysCode();
        sysCode3.setCodeid("APRNO");
        sysCode3.setCodevalue("认证不通过");
        sysCodeList1.add(sysCode3);

        SysCode sysCode4 = new SysCode();
        sysCode4.setCodeid("APRYES");
        sysCode4.setCodevalue("认证通过");
        sysCodeList1.add(sysCode4);

        SysCode sysCode5 = new SysCode();
        sysCode5.setCodeid("WAITPASS");
        sysCode5.setCodevalue("待审核");
        sysCodeList1.add(sysCode5);

        SysCode sysCode6 = new SysCode();
        sysCode6.setCodeid("NOPASS");
        sysCode6.setCodevalue("审核不通过");
        sysCodeList1.add(sysCode6);

        SysCode sysCode7 = new SysCode();
        sysCode7.setCodeid("PASS");
        sysCode7.setCodevalue("审核通过");
        sysCodeList1.add(sysCode7);
        return sysCodeList1;
    }

    /*将私海门店转入公海门店*/
    @RequestMapping(CrmStoreConstant.CRM_ADD_STORE_PUBLIC_URL)
    public @ResponseBody Message addPublic(Store store){
        int res = 0;
        res = crmStoreService.addPublic(store);
        return new Message(res);
    }

    /*门店导出excel*/
    @RequestMapping(CrmStoreConstant.CRM_STORE_Table_URL)
    public void export(HttpServletRequest request,HttpServletResponse response){
        String supervisorId= request.getParameter("supervisorId");
        String process= request.getParameter("process");
        String beginTime= request.getParameter("beginTime");
        String endTime= request.getParameter("endTime");
        String storeName= request.getParameter("storeName");

        HashMap hashMap = new HashMap();
        hashMap.put("supervisorId",supervisorId);
        hashMap.put("process",process);
        hashMap.put("beginTime",beginTime);
        hashMap.put("endTime",endTime);
        hashMap.put("storeName",storeName);
        //导出文件的标题
        String title = null;
        //设置表格标题行
        String[] headers = null;

        List<Object[]> dataList = null;
        title = "门店信息" + TimeUtils.getNowDayStr() + ".xls";
        headers = new String[]{"序号", "门店名称", "联系人", "手机号",
                "地址", "申请时间", "督导员", "组类", "下单总金额", "近30天下单总金额", "近30天下单次数(次)", "本周拜访次数(次)", "状态"};

        dataList = crmStoreService.queryReport(hashMap);
        //使用流将数据导出
        OutputStream out = null;
        try {
            //防止中文乱码
            String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "ISO8859-1") + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            //ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
            ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);//没有标题
            ex.export(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*跳转到门店详情*/
    @RequestMapping(CrmStoreConstant.CRM_STORE_DETAILS_URL)
    public ModelAndView crmStoreDetails(String storeId) throws  ParseException {
        HashMap param=new HashMap();
        HashMap param1=new HashMap();
        ModelAndView modelAndView = this.getModelAndView(CrmStoreConstant.CRM_STORE_DETAILS_PAGE);
        HashMap store= crmStoreService.findStoreDetails(storeId);
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //门店详情
        if (store !=null) {
            store.put("addTime",dateformat.format(store.get("addTime")));
            param.put("userId",store.get("userId"));
            Order order=crmStoreService.recentOrder(param);
            //最近一次下单时间
            if (order==null ){
                store.put("recentOrderTime","--");
                store.put("returnRate","--");
                store.put("consumptionFrequency","--");
            }else {
                store.put("recentOrderTime",dateformat.format(order.getOrderTime()));

                //补券率
                Double coupon =Double.parseDouble(store.get("couponNum").toString());
                Integer orderNum=Integer.parseInt(store.get("totalNum").toString());
                if (coupon==0 || orderNum==0){
                    store.put("returnRate","0%");
                }else {
                    store.put("returnRate",String.format("%.2f", ((coupon/orderNum)*100))+"%");
                }

                //消费频次
                java.text.SimpleDateFormat   FormatDate = new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date  add  =  FormatDate.parse(String.valueOf(store.get("addTime")));//转成Date
                Date now=new Date();
                double days =( now.getTime() - add.getTime()) / (24 * 60 * 60 * 1000);
                if (days==0 && orderNum!=0){
                    store.put("consumptionFrequency",String.format("%.2f", 1d/ orderNum) + "");
                }else if (orderNum==0 || (days==0 && orderNum==0)){
                    store.put("consumptionFrequency","0.0");
                }else {
                    store.put("consumptionFrequency",String.format("%.2f", days / orderNum)+ "");
                }
            }
            //门店详情
            modelAndView.addObject("store", store);
            Visit visit =crmStoreService.recentVisitRecord(storeId);
            //门店最近拜访记录
            modelAndView.addObject("visit", visit);
        }
        return  modelAndView;
    }

    /**
     * 查询订单列表
     */
    @RequestMapping(CrmStoreConstant.CRM_STORE_ORDER_DETAILS_URL)
    public ModelAndView findStoreDetailsOrder() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = crmStoreService.findStoreDetailsOrder(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 查询拜访列表
     */
    @RequestMapping(CrmStoreConstant.CRM_STORE_VISIT_DETAILS_URL)
    public ModelAndView storeVisitRecord() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = crmStoreService.storeVisitRecord(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 查询补券列表
     */
    @RequestMapping(CrmStoreConstant.CRM_STORE_COUPON_DETAILS_URL)
    public ModelAndView findStoreDetailsCoupon() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = crmStoreService.findStoreDetailsCoupon(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
}
