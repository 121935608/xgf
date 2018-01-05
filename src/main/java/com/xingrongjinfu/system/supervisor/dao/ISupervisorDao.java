/**
 * Copyright (C), 2017
 * FileName: ISupervisorDao
 * Author:   zxuser
 * Date:     2017/12/29 10:16
 * Description: 督导员的持久层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.supervisor.dao;

import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈督导员的持久层〉
 *
 * @author zxuser
 * @create 2017/12/29
 * @since 1.0.0
 */
public interface ISupervisorDao {

    /**
     * 督导员列表查询
     * @param pageUtilEntity
     * @return
     */
    List<TableDataInfo> SupervisorPageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 根据督导员的id修改信息
     */
    int updateSupervisorById(Supervisor supervisor);

    /**
     * 根据督导员的id查询督导员的信息
     */
    Supervisor getSupervisor(Integer supervisorId);

    /**
     * 添加督导员
     */
    int addSupervisor(Supervisor supervisor);

}