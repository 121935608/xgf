package com.xingrongjinfu.crm.department.dao;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.crm.department.model.Dept;

/**
 * 部门管理 数据层
 * 
 * @author y
 */
public interface IDeptDao
{
    /**
     * 根据条件分页查询部门对象
     * 
     * @param page 分页对象
     * @return 部门信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    
    /**
     * 根据部门ID查询部门信息
     * 
     * @param: dept
     * @return: Dept      
     */
	public Dept findByDeptId(Dept dept);


	/**
	 * 更新部门信息
	 * 
	 * @param: dept
	 * @return: int      
	 */
	public int updateDeptInfo(Dept dept);


	/**
	 * 新增部门信息
	 * 
	 * @param: dept
	 * @return: int      
	 */
	public int addDeptInfo(Dept dept);
}
