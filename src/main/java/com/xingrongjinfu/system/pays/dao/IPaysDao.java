/**
 * Copyright (C), 2018
 * FileName: IPayDao
 * Author:   zxuser
 * Date:     2018/1/3 10:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.pays.dao;

import com.xingrongjinfu.system.pays.model.Pays;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/3
 * @since 1.0.0
 */

public interface IPaysDao {

    /**
     * 查询收银支付流水
     */
    List<TableDataInfo>pageInfoQuery(PageUtilEntity pageUtilEntity);


    List<Pays> firstPageInfoQuery(String storeId);
}