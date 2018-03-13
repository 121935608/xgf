/**
 * Copyright (C), 2018
 * FileName: PaysController
 * Author:   zxuser
 * Date:     2018/1/3 10:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.pays.controller;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.pays.common.PaysConstant;
import com.xingrongjinfu.system.pays.service.IPaysService;

/**
 * 收银支付流水（平台端）
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class PaysController extends BaseController{

    @Autowired
    private IPaysService paysService;

    @RequestMapping(PaysConstant.PAYS_URL)
    public ModelAndView loadPays(){return this.getModelAndView(PaysConstant.PAYS_PAGE);}

    @RequestMapping(PaysConstant.PAYS_LIST_URL)
    public ModelAndView getPaysList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=paysService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }
}