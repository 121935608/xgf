package com.xingrongjinfu.system.storeaffairs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.system.supervisor.service.ISupervisorService;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.HttpUtils;
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
import com.xingrongjinfu.system.permission.common.PermissionConstant;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.common.RoleConstant;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.storeaffairs.commom.StoreaffairConstant;
import com.xingrongjinfu.system.storeaffairs.model.BankAccount;
import com.xingrongjinfu.system.storeaffairs.model.Store; 
import com.xingrongjinfu.system.storeaffairs.service.ICertificationService;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.syscode.model.SysCode; 


/**
 * 认证申请  处理
 * 
 * @author cj
 */
@Controller
@RequestMapping(SystemConstant.MERCHANT_URL)

class CertificationController extends BaseController {
	@Autowired
    private ICertificationService certificationService;

	@Autowired
    private ISupervisorService supervisorService;

    public String addCustomer;


    public String filePush;

    @Value("${addCustomer}")
    public void setAddCustomer(String addCustomer) {
        this.addCustomer = addCustomer;
    }
    @Value("${filePush}")
    public void setFilePush(String filePush) {
        this.filePush = filePush;
    }

    /**
     * 跳转认证申请列表界面
     */
    @RequestMapping(StoreaffairConstant.CERTIFICATION_URL)
    public ModelAndView certificationPage()
    { 
        ModelAndView modelAndView = this.getModelAndView(StoreaffairConstant.CERTIFICATION_PAGE);

        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Supervisor supervisor : getSupervisorSelection())
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(supervisor.getSupervisorId()+"");
            sysCode.setCodevalue(supervisor.getName());
            sysCodeList.add(sysCode);
        } 
        modelAndView.addObject("supervisorList", sysCodeList);
        
        
        //'未认证:WRZ 审核中:APRING 审核不通过:APRNO 审核通过:APRYES'
        List<SysCode> sysCodeList1 = new ArrayList<SysCode>(); 
            SysCode sysCode1 = new SysCode();
            sysCode1.setCodeid("WRZ"); 
            sysCode1.setCodevalue("未认证");
            sysCodeList1.add(sysCode1);
             
            SysCode sysCode2 = new SysCode();
            sysCode2.setCodeid("APRING");
            sysCode2.setCodevalue("审核中");
            sysCodeList1.add(sysCode2);
            
            SysCode sysCode3 = new SysCode();
            sysCode3.setCodeid("APRNO");
            sysCode3.setCodevalue("审核不通过");
            sysCodeList1.add(sysCode3);
            
            SysCode sysCode4 = new SysCode();
            sysCode4.setCodeid("APRYES");
            sysCode4.setCodevalue("审核通过"); 
            sysCodeList1.add(sysCode4); 
        modelAndView.addObject("repaymentStatusList", sysCodeList1);
        
        return modelAndView;
    }

   
	 /**
     * 查询认证申请列表
     */
    @RequestMapping(StoreaffairConstant.CERTIFICATION_LIST)
    public ModelAndView certificationList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String accountName=pageUtilEntity.getRelationMap().get("accountName");
        if(accountName!=null&&!accountName.equals("")){ 
        	try {
				pageUtilEntity.getRelationMap().put("accountName", URLDecoder.decode(accountName, "utf-8"));
        	} catch (UnsupportedEncodingException e) { 
				e.printStackTrace();
			}
        }
        List<TableDataInfo> tableDataInfo = certificationService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
     
    
    /**
     * 跳转认证申请审核界面
     */
    @RequestMapping(StoreaffairConstant.CERTIFICATION_CHECK_URL)
    public ModelAndView certificationCheckPage(String storeId)
    { 
        ModelAndView modelAndView = this.getModelAndView(StoreaffairConstant.CERTIFICATION_CHECK_PAGE);
        //获取商户信息
        //List<Store> storeList=getStoreInfo(this.getRequest().getParameter("storeId"));
        Store s=getStoreInfo(storeId); 
        //licensePic,frontStorePic,innerStorePic,contractPic,transactionPic,utilitiesPic
        s.setLicensePic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getLicensePic());
        s.setFrontStorePic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getFrontStorePic());
        s.setInnerStorePic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getInnerStorePic());
        s.setContractPic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getContractPic());
        s.setTransactionPic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getTransactionPic());
        s.setUtilitiesPic("http://xrjf.oss-cn-shanghai.aliyuncs.com/"+s.getUtilitiesPic());
        modelAndView.addObject("store",s);
        modelAndView.addObject("bankaccount", getBankAccountInfo(storeId));
       
        
        //获取select框数据
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Supervisor supervisor : getSupervisorSelection())
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(supervisor.getSupervisorId()+"");
            sysCode.setCodevalue(supervisor.getName());
            sysCodeList.add(sysCode);
        } 
        modelAndView.addObject("supervisorList", sysCodeList);
        
        //审核下拉框      未认证:WRZ  审核中:APRING 审核不通过:APRNO 审核通过:APRYES
        List<SysCode> sysCodeList1 = new ArrayList<SysCode>(); 
        SysCode sysCode1 = new SysCode();
        sysCode1.setCodeid("APRYES");
        sysCode1.setCodevalue("通过");
        sysCodeList1.add(sysCode1);
        
        SysCode sysCode2 = new SysCode();
        sysCode2.setCodeid("APRNO");
        sysCode2.setCodevalue("不通过");
        sysCodeList1.add(sysCode2);  
        modelAndView.addObject("ispass", sysCodeList1);
        
        return modelAndView;
    }
    
    
    /**
     * 提交审核内容
     */
    @RequestMapping(StoreaffairConstant.CERTIFICATION_CHECK_SUBMIT) 
    public @ResponseBody Message saveCertificationCheck(Store store,String process) throws UnsupportedEncodingException {
        int result = 0;
        String id = store.getStoreId();
        if("APRYES".equals(process)){
            if (id != null&&!id.equals(""))
        {
            Store store1=certificationService.getStoreInfo(id);
            System.out.println("后台bug"+new Date()+store1.getUserId());
            HashMap map=new HashMap();
            map.put("userId",store1.getUserId());
            //调用app添加客户接口
            String result1= HttpClientUtil.httpPostRequest(addCustomer,map);
//            String result1= HttpUtils.sendPost(addCustomer,map.toString());
//            String result2=HttpUtils.sendPost(filePush,map.toString());
            //调用app发送文件接口
            String result2=HttpClientUtil.httpPostRequest(filePush,map);
            result = certificationService.saveCertificationCheck(store);
        }
        }
        return new Message(result);
    }
    
    
    
    /**
     * 获取申请列表页面督导员下拉框数据
     */
    public  List<Supervisor>  getSupervisorSelection() 
    {
    	return  certificationService.getSupervisorList(); 
    }
    
     
    
    /**
     * 获取商户信息
     */
    public  Store getStoreInfo(String storeid) 
    {
    	return certificationService.getStoreInfo(storeid);   
    }
     
    /**
     * 获取银行账户信息
     */
    public  BankAccount getBankAccountInfo(String storeid)
    {
    	return certificationService.getBankAccountInfo(storeid);   
    }


}
