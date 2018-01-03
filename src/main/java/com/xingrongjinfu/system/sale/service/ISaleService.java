/**
 * Copyright (C), 2018
 * FileName: ISaleService
 * Author:   zxuser
 * Date:     2018/1/3 19:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.sale.service;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */
public interface ISaleService {

    /**
     * 查询商品销售信息
     */
    List<TableDataInfo>pageInfoQuery(PageUtilEntity pageUtilEntity);
}