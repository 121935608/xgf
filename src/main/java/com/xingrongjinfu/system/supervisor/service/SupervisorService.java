/**
 * Copyright (C), 2017
 * FileName: SupervisorService
 * Author:   zxuser
 * Date:     2017/12/29 10:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.supervisor.service;

import com.xingrongjinfu.system.supervisor.dao.ISupervisorDao;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import com.xingrongjinfu.utils.ObjectUtil;
import org.apache.shiro.common.UserConstants;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
@Service("supervisorService")
public class SupervisorService implements ISupervisorService {


    @Autowired
    private ISupervisorDao supervisorDao;


    @Override
    public int addSupervisor(Supervisor supervisor) {
        return supervisorDao.addSupervisor(supervisor);
    }

    @Override
    public int addCRMSupervisor(Supervisor supervisor) {
        return supervisorDao.addCRMSupervisor(supervisor);
    }

    @Override
    public int updateSupervisorById(Supervisor supervisor) {
        return supervisorDao.updateSupervisorById(supervisor);
    }

    @Override
    public List<TableDataInfo> SupervisorPageInfoQuery(PageUtilEntity pageUtilEntity) {
        return supervisorDao.SupervisorPageInfoQuery(pageUtilEntity);
    }

    @Override
    public List<TableDataInfo> SupervisorCRMPageInfoQuery(PageUtilEntity pageUtilEntity) {
        return supervisorDao.SupervisorCRMPageInfoQuery(pageUtilEntity);
    }

    @Override
    public Supervisor getSupervisor(Integer supervisorId) {
        return supervisorDao.getSupervisor(supervisorId);
    }

    @Override
    public String checkPhone(Supervisor supervisor) {
        if (supervisor.getSupervisorId() ==null ){
            supervisor.setSupervisorId(null);
        }
        String phone=supervisor.getPhone();
        Integer supervisorId=supervisor.getSupervisorId();
        supervisor=new Supervisor();
        supervisor.setPhone(phone);
        supervisor.setSupervisorId(supervisorId);
        Supervisor newSupervisor= supervisorDao.findPhone(supervisor);
        if (ObjectUtil.isNotNull(newSupervisor)){
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;

    }
}