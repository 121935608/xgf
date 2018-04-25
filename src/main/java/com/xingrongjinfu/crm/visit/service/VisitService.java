package com.xingrongjinfu.crm.visit.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.crm.visit.dao.IVisitDao;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 访问记录管理  业务层处理 
* @date 2018年4月25日
 */
@Service("visitService")
public class VisitService implements IVisitService
{

    @Autowired
    private IVisitDao visitDao;

    /**
     * 根据条件分页查询拜访记录对象
     * 
     * @param page 分页对象
     * @return 角色信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return visitDao.pageInfoQuery(pageUtilEntity);
    }

}
