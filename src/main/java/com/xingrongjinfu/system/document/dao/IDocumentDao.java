/**
 * Copyright (C), 2018
 * FileName: IDocumentDao
 * Author:   zxuser
 * Date:     2018/1/2 10:57
 * Description: 文件管理的业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.document.dao;

import com.xingrongjinfu.system.document.model.Document;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文件管理的业务层〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
public interface IDocumentDao {

   List<Document> documentPageInfoQuery(PageUtilEntity pageUtilEntity);

   /**
    * 保存文件
    */
   int saveDocument(Document document);

   /**
    * 根据id查询文件信息
    */
   Document findDocument(Integer documentId);

   /**
    * 修改文件信息
    */
   int updateDocument(Document document);

   /**
    * 校验公文名唯一
    */
   Document checkName(Document document);
}