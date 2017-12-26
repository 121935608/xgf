package com.xingrongjinfu.system.syscode.dao;

import java.util.List;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 数据字典 数据层
 * 
 * @author y
 */
public interface ISysCodeDao
{

    /**
     * 根据用户code查询对应数据字段
     * 
     * @param codeGroup 用户code
     * @return 数据字段对象
     */
    public List<SysCode> getUsysCodeByCond(String codeGroup);

}
