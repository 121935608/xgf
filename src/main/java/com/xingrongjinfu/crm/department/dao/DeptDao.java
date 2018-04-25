package com.xingrongjinfu.crm.department.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.crm.department.model.Dept;

/**
 * 部门管理 数据层处理
 * 
 * @author y
 */
@Repository("deptDao")
public class DeptDao extends DynamicObjectBaseDao implements IDeptDao
{
    /**
     * 根据条件分页查询部门对象
     * 
     * @param page 分页对象
     * @return 部门信息列表
     */
    @SuppressWarnings("unchecked")
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
    	List<TableDataInfo> deptPageInfo = null;
        try
        {
        	deptPageInfo = (List<TableDataInfo>) this.findForList("DeptMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return deptPageInfo;
	}

    
    /**
     * 根据部门ID查询部门信息
     * 
     * @param: deptId 部门ID
     * @return: Dept      
     */
	public Dept findByDeptId(Dept dept) {
		return (Dept) this.findForObject("DeptMapper.findByDeptId", dept);
	}


	/**
	 * 更新部门信息
	 * 
	 * @param dept
	 * @return   
	 */
	public int updateDeptInfo(Dept dept) {
		 return this.update("DeptMapper.updateDeptInfo", dept);
	}


	/**
	 * 新增部门信息
	 * 
	 * @param dept
	 * @return   
	 */
	public int addDeptInfo(Dept dept) {
		return (int) this.save("DeptMapper.addDeptInfo", dept);
	}


	/**
	 * 根据部门ID删除部门信息
	 * 
	 * @param: dept
	 * @return: int      
	 */
	public int deleteDeptById(Dept dept) {
		  return this.update("DeptMapper.deleteDeptById", dept);
	}
}
