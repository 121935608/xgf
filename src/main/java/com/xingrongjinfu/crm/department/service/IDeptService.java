package com.xingrongjinfu.crm.department.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import com.xingrongjinfu.crm.department.model.Dept;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 部门管理  业务层  
* @date 2018年4月25日
 */
public interface IDeptService
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
     * @param: @param deptId
     * @return: Dept     
     */
	public Dept findByDeptId(String deptId);


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


	/**
	 * 根据部门ID删除部门信息
	 * 
	 * @param: dept
	 * @return: int      
	 */
	public int deleteDeptById(Dept dept);
}
