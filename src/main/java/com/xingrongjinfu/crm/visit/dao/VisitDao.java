package com.xingrongjinfu.crm.visit.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 访问记录管理 数据层处理 
* @date 2018年4月25日
 */
@Repository("visitDao")
public class VisitDao extends DynamicObjectBaseDao implements IVisitDao
{
	 /**
     * 根据条件分页查询拜访记录信息
     * 
     * @param page 分页对象
     * @return 拜访信息列表
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
    	List<TableDataInfo> deptPageInfo = null;
        try
        {
        	deptPageInfo = (List<TableDataInfo>) this.findForList("VisitMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return deptPageInfo;
	}
}
