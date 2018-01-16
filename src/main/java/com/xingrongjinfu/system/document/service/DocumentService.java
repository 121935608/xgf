/**
 * Copyright (C), 2018
 * FileName: DocumentService
 * Author:   zxuser
 * Date:     2018/1/2 10:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.document.service;

import com.xingrongjinfu.system.document.dao.IDocumentDao;
import com.xingrongjinfu.system.document.model.Document;
import com.xingrongjinfu.utils.ObjectUtil;
import org.apache.shiro.common.UserConstants;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
@Service("documentService")
public class DocumentService implements IDocumentService
{
    @Autowired
    private IDocumentDao documentDao;

    @Override
    public List<Document> documentPageInfoQuery(PageUtilEntity pageUtilEntity) {
        return documentDao.documentPageInfoQuery(pageUtilEntity);
    }

    @Override
    public int saveDocument(Document document) {
        return documentDao.saveDocument(document);
    }

    @Override
    public Document findDocument(Integer documentId) {
        return documentDao.findDocument(documentId);
    }

    @Override
    public int updateDocument(Document document) {
        return documentDao.updateDocument(document);
    }

    @Override
    public String checkName(Document document) {
        Document newDocument=documentDao.checkName(document);
        if (ObjectUtil.isNotNull(newDocument)){
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }
}