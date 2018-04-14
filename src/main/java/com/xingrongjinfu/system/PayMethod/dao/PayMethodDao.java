package com.xingrongjinfu.system.PayMethod.dao;

import com.xingrongjinfu.system.PayMethod.model.PayMethod;
import com.xingrongjinfu.system.supervisor.model.Supervisor;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @创建人 Lelouch
 * @创建时间 $date$
 * @描述
 */
@Repository
public class PayMethodDao extends DynamicObjectBaseDao implements IPayMethodDao {
    @Override
    public List<TableDataInfo> payList(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> pageInfoQuery=null;
        try {
            pageInfoQuery=(List<TableDataInfo>)this.findForList("PayMethodMapper.getPayMethodList",pageUtilEntity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return pageInfoQuery;
    }

    @Override
    public int addPayMethod(PayMethod payMethod) {
        return this.save("PayMethodMapper.addPayMethod",payMethod);
    }

    @Override
    public int updatePayMethod(PayMethod payMethod) {
        return this.update("PayMethodMapper.updatePayMethod",payMethod);
    }
}
