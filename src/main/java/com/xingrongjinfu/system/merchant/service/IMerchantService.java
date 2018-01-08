/**
 * Copyright (C), 2017
 * FileName: IMerchantService
 * Author:   zxuser
 * Date:     2017/12/26 18:02
 * Description: 商品管理的业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.merchant.service;

import com.xingrongjinfu.system.merchant.model.AccountInfo;
import com.xingrongjinfu.system.merchant.model.Merchant;
import com.xingrongjinfu.system.user.model.User;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品管理的业务层〉
 *
 * @author zxuser
 * @create 2017/12/26
 * @since 1.0.0
 */
public interface IMerchantService {


    List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    AccountInfo getUserAccount(Merchant merchant);

    int changeMerchantStatus(User user);

    Merchant getMerchantInfo(Merchant merchant);

    List<TableDataInfo> AccountPageInfoQuery(PageUtilEntity pageUtilEntity);
}