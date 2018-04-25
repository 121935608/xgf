package com.xingrongjinfu.crm.department.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.crm.department.dao.IDeptDao;
import com.xingrongjinfu.crm.department.model.Dept;

/**
* @author chenmengzhen    
* @version V1.0  
* @Description: 部门管理   业务层处理 
* @date 2018年4月25日
 */
@Service("deptService")
public class DeptService implements IDeptService
{

    @Autowired
    private IDeptDao deptDao;

    /**
     * 根据条件分页查询部门对象
     * 
     * @param page 分页对象
     * @return 部门信息集合信息
     */
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return deptDao.pageInfoQuery(pageUtilEntity);
    }

    
    /**
     * 根据部门ID查询部门信息
     * 
     * @param deptId 部门ID
     * @return   部门信息
     */
	public Dept findByDeptId(String deptId) {
		Dept dept = new Dept();
		dept.setDeptId(deptId);;
	    return deptDao.findByDeptId(dept);
	}



	/**
	 * 更新部门信息
	 * 
	 * @param: dept
	 * @return: int      
	 */
	public int updateDeptInfo(Dept dept) {
		return deptDao.updateDeptInfo(dept);
	}


	/**
	 * 新增部门信息
	 * 
	 * @param: dept 
	 * @return: int      
	 */
	public int addDeptInfo(Dept dept) {
		return deptDao.addDeptInfo(dept);
	}

	/**
	 * 根据部门ID删除部门信息
	 * 
	 * @param: dept
	 * @return: int      
	 */
	public int deleteDeptById(Dept dept) {
		return deptDao.deleteDeptById(dept);
	}
}
