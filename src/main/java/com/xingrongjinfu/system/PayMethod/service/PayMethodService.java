package com.xingrongjinfu.system.PayMethod.service;

import com.xingrongjinfu.system.PayMethod.dao.IPayMethodDao;
import com.xingrongjinfu.system.PayMethod.model.PayMethod;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 Lelouch
 * @创建时间 $date$
 * @描述
 */
@Service
public class PayMethodService implements IPayMethodService {


    @Autowired
    private IPayMethodDao iPayDao;

    @Override
    public List<TableDataInfo> payList(PageUtilEntity pageUtilEntity) {
        return iPayDao.payList(pageUtilEntity);
    }

    @Override
    public int addPayMethod(PayMethod payMethod) {
        return iPayDao.addPayMethod(payMethod);
    }

    @Override
    public int updatePayMethod(PayMethod payMethod) {
        return iPayDao.updatePayMethod(payMethod);
    }
}
