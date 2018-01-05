/**
 * Copyright (C), 2017
 * FileName: SupervisorDao
 * Author:   zxuser
 * Date:     2017/12/29 10:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.supervisor.dao;

import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
@Repository("supervisorDao")
public class SupervisorDao extends DynamicObjectBaseDao implements ISupervisorDao {

    @Override
    public int addSupervisor(Supervisor supervisor) {
        return this.save("SupervisorMapper.addSupervisor",supervisor);
    }

    @Override
    public Supervisor getSupervisor(Integer supervisorId) {
        return (Supervisor) this.findForObject("SupervisorMapper.findSupervisor",supervisorId);
    }

    @Override
    public int updateSupervisorById(Supervisor supervisor) {
        return this.update("SupervisorMapper.updateSupervisor",supervisor);
    }

    @Override
    public List<TableDataInfo> SupervisorPageInfoQuery(PageUtilEntity pageUtilEntity) {

        List<TableDataInfo> supervisorPageInfo = null;
        try
        {
            supervisorPageInfo = (List<TableDataInfo>) this.findForList("SupervisorMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return supervisorPageInfo;
    }

}