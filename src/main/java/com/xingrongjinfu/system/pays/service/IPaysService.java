/**
 * Copyright (C), 2018
 * FileName: IPayService
 * Author:   zxuser
 * Date:     2018/1/3 10:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.pays.service;

import com.xingrongjinfu.system.pays.model.Pays;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public interface IPaysService {

    /**
     * 分页查询收银支付流水
     * @param pageUtilEntity
     * @return
     */
    List<TableDataInfo>pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 首页查询
     */
    List<Pays> firstPageInfoQuery(String storeId);



    List<TableDataInfo> payInfoQuery(Map<String, String> param);
}