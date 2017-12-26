package com.xingrongjinfu.system.syscode.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 数据字典 数据层处理
 * 
 * @author y
 */
@Repository("sysCodeDao")
public class SysCodeDao extends DynamicObjectBaseDao implements ISysCodeDao
{

    /**
     * 根据用户code查询对应数据字段
     * 
     * @param codeGroup 用户code
     * @return 数据字段对象
     */
    @SuppressWarnings("unchecked")
    public List<SysCode> getUsysCodeByCond(@Param("codeGroup") String codeGroup)
    {
        List<SysCode> sysCodeList = null;
        try
        {
            sysCodeList = (List<SysCode>) this.findForList("SystemCodeMapper.querySysCodeListByCond", codeGroup);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sysCodeList;
    }
}
