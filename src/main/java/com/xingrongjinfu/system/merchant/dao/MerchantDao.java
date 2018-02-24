/**
 * Copyright (C), 2017
 * FileName: MerchantDao
 * Author:   zxuser
 * Date:     2017/12/26 18:11
 * Description: 持久层实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.system.merchant.model.AccountInfo;
import com.xingrongjinfu.system.merchant.model.Merchant;
import com.xingrongjinfu.system.user.model.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈持久层实现〉
 *
 * @author zxuser
 * @create 2017/12/26
 * @since 1.0.0
 */
@Repository("merchantDao")
public class MerchantDao extends DynamicObjectBaseDao implements IMerchantDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {

        List<TableDataInfo> merchantPageInfo = null;
        try
        {
            merchantPageInfo = (List<TableDataInfo>) this.findForList("MerchantMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return merchantPageInfo;
    }

    @Override
    public AccountInfo getUserAccount(String storeId)
    {
        AccountInfo userAccountInfo = null;
        try
        {
            userAccountInfo=(AccountInfo)this.findForObject("MerchantMapper.userAccountQuery",storeId);
        }
        catch (Exception e)
        {e.printStackTrace();}
        return userAccountInfo;
    }

    @Override
    public List<TableDataInfo> AccountPageInfoQuery(PageUtilEntity pageUtilEntity) {

        List<TableDataInfo> accountPageInfo = null;
        try
        {

            accountPageInfo = (List<TableDataInfo>) this.findForList("MerchantMapper.AccountpageInfoQuery",pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return accountPageInfo;
    }

    @Override
    public Merchant getMerchantInfo(Merchant merchant) {
        Merchant merchants = null;
        try
        {
            merchants=(Merchant) this.findForObject("MerchantMapper.getMerchantInfo",merchant);
        }
        catch (Exception e)
        {e.printStackTrace();}
        return merchants;
    }

    @Override
    public int changeMerchantStatus(User user) {
        return this.update("MerchantMapper.updateMerchantInfo",user);
    }
}