package com.xingrongjinfu.statistics.procurement.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.model.User;

/**
 *  数据层处理
 * 
 * @author 
 */
@Repository("procurementDao")
public class ProcurementDao extends DynamicObjectBaseDao implements IProcurementDao
{

	/**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> procurementPageInfo = null;
        try
        {
        	procurementPageInfo = (List<TableDataInfo>) this.findForList("StatisticsProcurementMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return procurementPageInfo;

    }
    

}
