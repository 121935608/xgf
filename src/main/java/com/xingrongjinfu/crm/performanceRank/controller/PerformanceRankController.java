package com.xingrongjinfu.crm.performanceRank.controller;

import com.xingrongjinfu.commodity.classification.model.Category;
import com.xingrongjinfu.crm.CrmConstant;
import com.xingrongjinfu.crm.department.model.Dept;
import com.xingrongjinfu.crm.performanceRank.common.PerformanceRankConstant;
import com.xingrongjinfu.crm.performanceRank.dao.IPerformanceRankDao;
import com.xingrongjinfu.crm.performanceRank.service.IPerformanceRankService;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.system.syscode.model.SysCode;
import org.apache.poi.ss.formula.functions.T;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author hyq
 * @version V1.0
 * @Description: 业绩排名   处理
 * @date 2018年4月25日
 */
@Controller
@RequestMapping(CrmConstant.CRM_URL)
public class PerformanceRankController extends BaseController {
    @Autowired
    private IPerformanceRankService performanceRankService;
    @Autowired
    private IPerformanceRankDao performanceRankDao;
    /**
     * 跳转访问记录列表界面
     */
    @RequestMapping(PerformanceRankConstant.PERFORMANCE_RANK_URL)
    public ModelAndView loadCrmVisit() throws Exception {
        ModelAndView modelAndView= this.getModelAndView(PerformanceRankConstant.PERFORMANCE_RANK_PAGE);
        Supervisor supervisor=new Supervisor();
        modelAndView.addObject("supervisor",supervisor);
        //省
//        modelAndView.addObject("province",getProvince());
//        //市
//        modelAndView.addObject("category",getCategoryList());
//        //区
//        modelAndView.addObject("supply",getSupplyList());
        //部门
        modelAndView.addObject("dept",getDept());
        return modelAndView;
    }

    /**
     * 查询部门列表
     */
    @RequestMapping(PerformanceRankConstant.PERFORMANCE_LIST_URL)
    public ModelAndView performanceRankList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = performanceRankService.pageInfoQueryBydept(pageUtilEntity);
        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 跳转到查看业绩排名界面
     * @param
     * @return
     */
    @RequestMapping(PerformanceRankConstant.PERFORMANCE_QUERY_URL)
    public ModelAndView performanceQuery(String supervisorId,String userId,String queryType,String deptId,String deptName,String deptRank,String area,String name,String totalPrice)
    {
        HashMap param=new HashMap();
        param.put("supervisorId",supervisorId);
//        param.put("userId",userId);
//        param.put("queryType",queryType);
//        param.put("deptId",deptId);
        param.put("deptName",deptName);
        param.put("deptRank",deptRank);
        param.put("area",area);
        param.put("name",name);
        param.put("totalPrice",totalPrice);
        ModelAndView modelAndView=this.getModelAndView(PerformanceRankConstant.PERFORMANCE_QUERY_PAGE);
        HashMap result= performanceRankService.getPerformanceDetails(param);
        if (result !=null) {
            modelAndView.addObject("result", result);
        }
        return modelAndView;
    }

    /**
     * 获取所有部门id
     * @return
     */
    public List<SysCode> getDept() throws Exception {
        List<Dept> list=performanceRankDao.getDept();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Dept dept:list){
            SysCode sysCode = new SysCode();
            if (dept!=null) {
                sysCode.setCodeid(dept.getDeptId()==null?"":dept.getDeptId());
                sysCode.setCodevalue(dept.getDeptName()==null?"":dept.getDeptName());
                sysCodeList.add(sysCode);
            }
        }
        return sysCodeList;
    }

}
