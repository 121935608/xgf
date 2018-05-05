package com.xingrongjinfu.crm.crmStore.controller;

import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.crmStore.common.CrmStoreConstant;
import com.xingrongjinfu.crm.crmStore.service.CrmStoreService;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.syscode.model.SysCode;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(CrmConstant.CRM_URL_STORE)
public class CrmStoreController extends BaseController {

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

}
