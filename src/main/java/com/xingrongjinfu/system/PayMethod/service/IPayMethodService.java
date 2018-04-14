package com.xingrongjinfu.system.PayMethod.service;

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
public interface IPayMethodService {

    List<TableDataInfo> payList(PageUtilEntity pageUtilEntity);

    int addPayMethod(PayMethod payMethod);

    int updatePayMethod(PayMethod payMethod);
}
