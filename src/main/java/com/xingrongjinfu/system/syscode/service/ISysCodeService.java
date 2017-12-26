package com.xingrongjinfu.system.syscode.service;

import java.util.List;
import com.xingrongjinfu.system.syscode.model.SysCode;

/**
 * 数据字典 业务层
 * 
 * @author y
 */
public interface ISysCodeService
{
    /**
     * 根据用户code查询对应数据字段
     * 
     * @param codeGroup 用户code
     * @return 数据字段对象
     */
    public List<SysCode> getUsysCodeListByCodekey(String codeGroup);
}