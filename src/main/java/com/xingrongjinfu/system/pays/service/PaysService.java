/**
 * Copyright (C), 2018
 * FileName: PayService
 * Author:   zxuser
 * Date:     2018/1/3 10:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.pays.service;

import com.xingrongjinfu.system.pays.dao.IPaysDao;
import com.xingrongjinfu.system.pays.model.Pays;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("paysService")
public class PaysService implements IPaysService {

    @Autowired
    private IPaysDao paysDao;
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return paysDao.pageInfoQuery(pageUtilEntity);
    }


    @Override
    public List<Pays> firstPageInfoQuery(String storeId) {
        return paysDao.firstPageInfoQuery(storeId);
    }

    @Override
    public List<TableDataInfo> payInfoQuery(Map<String, String> param) {
        return paysDao.payInfoQuery(param);
    }


}