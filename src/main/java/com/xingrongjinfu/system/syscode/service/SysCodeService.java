package com.xingrongjinfu.system.syscode.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xingrongjinfu.system.syscode.dao.ISysCodeDao;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 数据字典 数据层处理
 * 
 * @author y
 */
@Service("sysCodeService")
public class SysCodeService implements ISysCodeService
{

    @Autowired
    private ISysCodeDao sysCodeDao;

    /**
     * 根据用户code查询对应数据字段
     * 
     * @param codeGroup 用户code
     * @return 数据字段对象
     */
    public List<SysCode> getUsysCodeListByCodekey(String codeGroup)
    {
        return sysCodeDao.getUsysCodeByCond(codeGroup);
    }

}
