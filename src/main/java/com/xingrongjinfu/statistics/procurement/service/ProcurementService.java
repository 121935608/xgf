package com.xingrongjinfu.statistics.procurement.service;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.procurement.model.ProcurementDto;
import org.apache.shiro.common.UserConstants;
import org.apache.shiro.service.PasswordService;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commercial.repayment.dao.IRepaymentedDao;
import com.xingrongjinfu.statistics.procurement.dao.IProcurementDao;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.dao.IUserDao;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("procurementService")
public class ProcurementService implements IProcurementService
{

	@Autowired
    private IProcurementDao procurementDao;

    @Override
    public List<ProcurementDto> infoQuery(Map<String, String> param) {
        return procurementDao.infoQuery(param);
    }

    /**
     * 根据条件分页查询
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return procurementDao.pageInfoQuery(pageUtilEntity);
    }

}
