package com.xingrongjinfu.system.PayMethod.dao;

import com.xingrongjinfu.system.PayMethod.model.PayMethod;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * @创建人 Lelouch
 * @创建时间 $date$
 * @描述
 */
public interface IPayMethodDao {

    List<TableDataInfo> payList(PageUtilEntity pageUtilEntity);

    int addPayMethod(PayMethod payMethod);

    /**
     * 根据督导员的id修改信息
     */
    int updatePayMethod(PayMethod payMethod);

}
