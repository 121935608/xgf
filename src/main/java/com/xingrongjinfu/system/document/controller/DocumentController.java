/**
 * Copyright (C), 2018
 * FileName: DocumentController
 * Author:   zxuser
 * Date:     2018/1/2 10:55
 * Description: 文件管理的控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xingrongjinfu.system.document.controller;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.document.common.DocumentConstant;
import com.xingrongjinfu.system.document.model.Document;
import com.xingrongjinfu.system.document.service.IDocumentService;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import org.springframework.web.multipart.MultipartFile;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文件管理的控制层〉
 *
 * @author zxuser
 * @create 2018/1/2
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class DocumentController extends BaseController {

    @Autowired
    private IDocumentService documentService;

    /**
     * 跳转到文件管理界面
     * @return
     */
    @RequestMapping(DocumentConstant.DOCUMENT_URL)
    public ModelAndView loadDocument(){return this.getModelAndView(DocumentConstant.DOCUMENT_PAGE);}

    /**
     * 查询文件列表
     * @return
     */
    @RequestMapping(DocumentConstant.DOCUMENT_LIST_URL)
    public ModelAndView getDocumentList(){
        PageUtilEntity pageUtilEntity=this.getPageUtilEntity();
        List<Document> tableDataInfo=documentService.documentPageInfoQuery(pageUtilEntity);
        Integer i=1;
        for (Document document :tableDataInfo){
            document.setDocumentNum(pageUtilEntity.getPage()+(i++));
        }
        return buildDatasTable(pageUtilEntity.getTotalResult(),tableDataInfo);
    }

    /**
     * 跳转到添加文件界面
     * @return
     */
    @RequestMapping(DocumentConstant.DOCUMENT_ADD_URL)
    public ModelAndView loadDocumentAdd(){return this.getModelAndView(DocumentConstant.DOCUMENT_ADD_PAGE);}

    /**
     * 保存文件,文件上传到阿里云服务器
     */
    @RequestMapping(DocumentConstant.DOCUMENT_ADD)
    public @ResponseBody
    Message saveDocument(Document document, MultipartFile file){
        int result=0;
        AliyunOSSClientUtil aliyunOSSClientUtil=new AliyunOSSClientUtil();
        try {
            String key = aliyunOSSClientUtil.uploadImg(file);
            if (key!=null) {
                String originalFilename = file.getOriginalFilename();
                String filePath = aliyunOSSClientUtil.FOLDER + originalFilename;
                document.setUrl(filePath);
                User user = this.getCurrentUser();
                document.setCreator(user.getUserName());
                result = documentService.saveDocument(document);
            }
        }
        catch (OSSException e)
        {
            e.printStackTrace();
            return new Message(result);
        }
        return new Message(result);
    }

    /**
     * 跳转到文件修改页面
     * @param documentId
     * @return
     */
    @RequestMapping(DocumentConstant.DOCUMENT_MODIFY_URL)
    public ModelAndView loadModifyPage(Integer documentId)
    {
        ModelAndView modelAndView=this.getModelAndView(DocumentConstant.DOCUMENT_MODIFY_PAGE);
        Document document=documentService.findDocument(documentId);
        document.setFileName(document.getUrl().substring(13));
        modelAndView.addObject("document",document);
        return modelAndView;
    }

    /**
     * 公文管理的启用禁用
     * @param document
     * @return
     */
    @RequestMapping(DocumentConstant.DOCUMENT_CHANGESTATUS_URL)
    public @ResponseBody Message changeStatus(Document document){
        int result=0;
        Integer id=document.getDocumentId();
        User user=this.getCurrentUser();
        document.setCreator(user.getUserName());
        if (id!=null){
            result=documentService.updateDocument(document);
        }
        return new Message(result);
    }

    /**
     * 修改公文
     * @param document
     * @param file
     * @return
     */
    @RequestMapping(DocumentConstant.DOCUMENT_MODIFY)
    public @ResponseBody Message modifyDocument(Document document, MultipartFile file){
        int result=0;
        AliyunOSSClientUtil aliyunOSSClientUtil=new AliyunOSSClientUtil();
        Integer id=document.getDocumentId();
        if (id !=null) {
            try {
                if (file !=null) {
                    String key = aliyunOSSClientUtil.uploadImg(file);
                    String originalFilename = file.getOriginalFilename();
                    String filePath = aliyunOSSClientUtil.FOLDER + originalFilename;
                    document.setUrl(filePath);
                }
                User user = this.getCurrentUser();
                document.setCreator(user.getUserName());
                result = documentService.updateDocument(document);

            } catch (OSSException e) {
                e.printStackTrace();
                return new Message(result);
            }
        }
        return new Message(result);
    }

    /**
     * 校验公文标题是否唯一
     */
    @RequestMapping(DocumentConstant.CHECK_NAME_URL)
    public @ResponseBody String checkName(Document document){
        String uniqueFlag="0";
        if (document !=null)
        {
            uniqueFlag=documentService.checkName(document);
        }
        return uniqueFlag;
    }
}