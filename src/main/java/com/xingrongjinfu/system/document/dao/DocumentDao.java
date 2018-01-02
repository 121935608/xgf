/**
 * Copyright (C), 2018
 * FileName: DocumentDao
 * Author:   zxuser
 * Date:     2018/1/2 10:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.document.dao;

import com.xingrongjinfu.system.document.model.Document;
import com.xingrongjinfu.system.help.model.Help;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
@Repository("documentDao")
public class DocumentDao extends DynamicObjectBaseDao implements IDocumentDao {
    @Override
    public List<TableDataInfo> documentPageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> documentPageInfoQuery=null;
        try {
            documentPageInfoQuery=(List<TableDataInfo>)this.findForList("DocumentMapper.DocumentpageInfoQuery",pageUtilEntity);
        }catch (Exception e)
        {e.printStackTrace();}
        return documentPageInfoQuery;
    }

    @Override
    public int saveDocument(Document document) {
        return this.save("DocumentMapper.saveDocument",document);
    }

    @Override
    public Document findDocument(Integer documentId) {
        return (Document) this.findForObject("DocumentMapper.findDocument",documentId);
    }

    @Override
    public int updateDocument(Document document) {
        return this.update("DocumentMapper.updateDocument",document);
    }
}