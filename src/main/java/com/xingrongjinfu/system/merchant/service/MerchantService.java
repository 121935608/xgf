/**
 * Copyright (C), 2017
 * FileName: MerchantServiceImpl
 * Author:   zxuser
 * Date:     2017/12/26 18:03
 * Description: 业务层实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.service;

import com.xingrongjinfu.system.merchant.dao.IMerchantDao;
import com.xingrongjinfu.system.merchant.model.AccountInfo;
import com.xingrongjinfu.system.merchant.model.Merchant;
import com.xingrongjinfu.system.user.model.User;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务层实现〉
 *
 * @author zhaoyunfei
 * @create 2017/12/26
 * @since 1.0.0
 */
@Service("merchantService")
public class MerchantService implements IMerchantService {



    @Autowired
    private IMerchantDao merchantDao;

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return merchantDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public AccountInfo getUserAccount(String userId) {
        return merchantDao.getUserAccount(userId);
    }

    @Override
    public int changeMerchantStatus(User user) {
        return merchantDao.changeMerchantStatus(user);
    }

    @Override
    public List<TableDataInfo> AccountPageInfoQuery(PageUtilEntity pageUtilEntity) {
        return merchantDao.AccountPageInfoQuery(pageUtilEntity);
    }

    @Override
    public Merchant getMerchantInfo(String userId) { return merchantDao.getMerchantInfo(userId);}
}