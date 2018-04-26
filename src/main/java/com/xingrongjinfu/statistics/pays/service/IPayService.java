package com.xingrongjinfu.statistics.pays.service;

import java.util.List;
import java.util.Map;

import com.xingrongjinfu.statistics.pays.model.Pays;
import com.xingrongjinfu.statistics.pays.model.PaysDto;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.user.model.User;

/**
 *  业务层
 * 
 * @author 
 */
public interface IPayService
{

	/**
     * 根据条件分页查询对象
     * 
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);


    List<PaysDto> infoQuery(Map<String, String> param);
}
