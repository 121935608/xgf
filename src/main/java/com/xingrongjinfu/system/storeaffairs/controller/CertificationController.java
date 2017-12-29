package com.xingrongjinfu.system.storeaffairs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.xingrongjinfu.system.storeaffairs.model.Supervisor;
import com.xingrongjinfu.system.storeaffairs.service.ICertificationService;
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
            sysCode.setCodeid(supervisor.getSupervisorId());
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
        modelAndView.addObject("store", getStoreInfo(storeId));
        modelAndView.addObject("bankaccount", getBankAccountInfo(storeId));
       
        
        //获取select框数据
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Supervisor supervisor : getSupervisorSelection())
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(supervisor.getSupervisorId());
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
    public @ResponseBody Message saveCertificationCheck(Store store)
    {
        int result = 0;
        String id = store.getStoreId();
        if (id != null&&!id.equals(""))
        {
            result = certificationService.saveCertificationCheck(store);
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
