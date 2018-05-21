package com.xingrongjinfu.crm.crmUser.controller;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.crmStore.common.CrmStoreConstant;
import com.xingrongjinfu.crm.crmStore.service.CrmStoreService;
import com.xingrongjinfu.crm.crmUser.service.CrmUserService;
import com.xingrongjinfu.crm.department.model.Dept;
import com.xingrongjinfu.crm.information.model.Information;
import com.xingrongjinfu.crm.information.service.InformatioinService;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.supervisor.common.SupervisorConstant;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.supervisor.service.ISupervisorService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AccessCodeUtil;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.Md5Utils;
import com.xingrongjinfu.utils.UuidUtil;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.crm.crmUser.common.CrmUserConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门管理  处理
 * 
 * @author y
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL_USER)
public class CrmUserController extends BaseController
{
    @Autowired
    private ISupervisorService supervisorService;

    @Autowired
    private CrmUserService crmUserService;
    
    @Autowired
    private InformatioinService informatioinService;
    
    @Autowired
    private CrmStoreService crmStoreService;
    
    /**
     * 跳转用户列表界面
     */
    @RequestMapping(CrmUserConstant.USER_URL)
    public ModelAndView userCrmDept()
    {
        return this.getModelAndView(CrmUserConstant.USER_PAGE);
    }

    /*
    * 查询用户列表
    * */
    @RequestMapping(CrmUserConstant.USER_CRM_URL)
    public ModelAndView userList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo=supervisorService.SupervisorCRMPageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /*
    * 修改CRM督导员状态
    * */
    @RequestMapping(CrmUserConstant.USER_CRM_UPDATE_STATUS)
    public @ResponseBody Message changeCRMSupervisorStatus(Supervisor supervisor){
        int result=0;
        Integer supervisorId=supervisor.getSupervisorId();
        if (supervisorId!=null) {
            if(supervisor.getCrmPwd() != null){
                supervisor.setCrmPwd(Md5Utils.hash(supervisor.getCrmPwd()));
            }
            result = supervisorService.updateSupervisorById(supervisor);
        }
        return new Message(result);
    }
    /*根据督导员ID查询督导员信息*/
    @RequestMapping(CrmUserConstant.USER_CRM_SELECT_URL)
    public ModelAndView suoervistorAddUpdate(Integer supervisorId){
        ModelAndView modelAndView =this.getModelAndView(CrmUserConstant.USER_CRM_UPDATE_PAGE);
        Supervisor supervisor=supervisorService.getSupervisor(supervisorId);
        modelAndView.addObject("Supervisor",supervisor);
        /*查询所有部门信息*/
        List<Dept> deptList = crmUserService.selectAllDept();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for(int i = 0; i< deptList.size();i++){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(deptList.get(i).getDeptId());
            sysCode.setCodevalue(deptList.get(i).getDeptName());
            sysCodeList.add(sysCode);
        }
        modelAndView.addObject("roles",getCRMAllRole());
        modelAndView.addObject("deptList",sysCodeList);
        return modelAndView;
    }
    /*根据督导员ID修改督导员信息*/
    @RequestMapping(CrmUserConstant.USER_CRM_UPDATE_URL)
    public @ResponseBody Message updateCRMSupervisor(Supervisor supervisor,MultipartFile picture){
        int result = 0;
        try {
            if(picture.getSize() !=0){
                AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
                String key = aliyunOSSClientUtil.uploadImg(picture);
                if(key!=null){
                    String filePath = aliyunOSSClientUtil.FOLDER + aliyunOSSClientUtil.filePath;
                    supervisor.setHeadPortrait(filePath);

                }
            }
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(result);
        }
        Integer supervistorID = supervisor.getSupervisorId();
        supervisor.setCrmPwd(Md5Utils.hash(supervisor.getCrmPwd()));
        if(supervistorID !=null){
            result = supervisorService.updateCRMSupervisor(supervisor);
        }
        return new Message(result);
    }

    /*跳转增加督导员信息界面*/
    @RequestMapping(CrmUserConstant.USER_CRM_ADD_URL)
    public ModelAndView suoervistorAddUpdate(){
        ModelAndView modelAndView =this.getModelAndView(CrmUserConstant.USER_CRM_ADD_PAGE);
        /*查询所有部门信息*/
        List<Dept> deptList = crmUserService.selectAllDept();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for(int i = 0; i< deptList.size();i++){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(deptList.get(i).getDeptId());
            sysCode.setCodevalue(deptList.get(i).getDeptName());
            sysCodeList.add(sysCode);
        }
        modelAndView.addObject("roles",getCRMAllRole());
        modelAndView.addObject("deptList",sysCodeList);
        return modelAndView;
    }
    /*添加督导员接口*/
    @RequestMapping(CrmUserConstant.USER_CRM_ADD_URL_TWO)
    public @ResponseBody Message addCRMSupervistor(Supervisor supervisor,MultipartFile picture){
        int result = 0;
        try {
            if(picture.getSize() !=0){
                AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
                String key = aliyunOSSClientUtil.uploadImg(picture);
                if(key!=null){
                    String filePath = aliyunOSSClientUtil.FOLDER + aliyunOSSClientUtil.filePath;
                    supervisor.setHeadPortrait(filePath);

                }
            }
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(result);
        }
        supervisor.setSupervisorNum(AccessCodeUtil.accessCode());
        supervisor.setCrmPwd(Md5Utils.hash(supervisor.getCrmPwd()));
        result = supervisorService.addCRMSupervisor(supervisor);
        return new Message(result);
    }

    /*获取所有角色*/
    public List<SysCode> getCRMAllRole(){
        List<Role> list = crmUserService.selectRole();
        List<SysCode> sysRoleList = new ArrayList<SysCode>();
        for(int r = 0; r< list.size();r++){
            SysCode sysCodeRole = new SysCode();
            sysCodeRole.setCodeid(list.get(r).getRoleId().toString());
            sysCodeRole.setCodevalue(list.get(r).getRoleName());
            sysRoleList.add(sysCodeRole);
        }
        return sysRoleList;
    }

    /*检查用户名是否存在*/
    @RequestMapping(CrmUserConstant.USER_CRM_CHECK_CRMLOGIN_URL)
    public @ResponseBody String checkCrm(Supervisor supervisor){
        String checkCrmLogin = "0";
            if(supervisor.getCrmLogin() != null){
                List<Supervisor> list = crmUserService.checkCrmLogin(supervisor.getCrmLogin());
                if(list.size()>0){
                    checkCrmLogin = "1";
                }
            }
            return checkCrmLogin;
    }
    
    
    /*跳转至业务转交界面*/
    @RequestMapping(CrmUserConstant.CRM_USER_STORE_URL)
    public ModelAndView crmStoreSupervistor(String supervisorIdOne){
        ModelAndView modelAndView = this.getModelAndView(CrmUserConstant.CRM_USER_STORE_PAGE);
        Supervisor supervisor = new Supervisor();
        supervisor.setSupervisorNum(supervisorIdOne);
        modelAndView.addObject("supervisor",supervisor);
        return  modelAndView;
    }
    
    
    /*业务分配业务员接口*/
    @RequestMapping(CrmUserConstant.CRM_UPDATE_STORE_SUPERVISTOR_URL)
    public @ResponseBody Message updateStoreSupervisor(@RequestParam(required = true) String supervisorIdOne, @RequestParam(required = true) String supervisorId){
        int result = 0;
        System.out.println("原业务员"+supervisorIdOne+"和新分配业务员"+supervisorId);
        List<Store> storeList = crmStoreService.findBySupervisorNum(supervisorIdOne);
        if(storeList == null || storeList.size() < 0){
        	return new Message(false,"该业务员名下没有门店信息!");
        }
        result = crmStoreService.batchUpdateStoreSupervistor(supervisorIdOne, supervisorId);
        Information information = new Information();
        information.setInfoId(UuidUtil.get32UUID());
        information.setInfoHeadline("业务转交");
        information.setSupervisorNum(supervisorId);
        information.setInfoContent("已将编号为"+supervisorIdOne+"的业务员所有的门店全部转交给您，请注意查看！");
        information.setInfoStatus("0");
        int addInfor = informatioinService.addInfor(information);
        return new Message(result);
    }

}
