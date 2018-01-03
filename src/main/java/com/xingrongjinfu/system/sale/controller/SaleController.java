/**
 * Copyright (C), 2018
 * FileName: SaleController
 * Author:   zxuser
 * Date:     2018/1/3 19:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.sale.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.sale.common.SaleConstant;
import com.xingrongjinfu.system.sale.service.ISaleService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.DATACOUNT_URL)
public class SaleController extends BaseController {

    @Autowired
    private ISaleService saleService;

    @RequestMapping(SaleConstant.SALE_URL)
    public ModelAndView loadSalePage(){return this.getModelAndView(SaleConstant.SALE_PAGE);}

    @RequestMapping(SaleConstant.SALE_LIST_URL)
    public ModelAndView saleList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo=saleService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }
}