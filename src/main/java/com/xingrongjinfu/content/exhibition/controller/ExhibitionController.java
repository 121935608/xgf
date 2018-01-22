package com.xingrongjinfu.content.exhibition.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.content.ContentConstant;
import com.xingrongjinfu.content.exhibition.common.ExhibitionConstant;
import com.xingrongjinfu.content.exhibition.model.Exhibition;
import com.xingrongjinfu.content.exhibition.service.IExhibitionService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;

/**
 * 业务处理
 * 
 * @author
 */
@Controller
@RequestMapping(ContentConstant.CONTENT_URL)
public class ExhibitionController extends BaseController {

	@Autowired
	private IExhibitionService exhibitionService;

	/**
	 * 跳转列表界面
	 */
	@RequestMapping(ExhibitionConstant.EXHIBITION_URL)
	public ModelAndView loadExhibition() {
		ModelAndView modelAndView = this.getModelAndView(ExhibitionConstant.EXHIBITION_PAGE);
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("停用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		return modelAndView;
	}

	/**
	 * 跳转新增界面
	 */
	@RequestMapping(ExhibitionConstant.TO_ADD_URL)
	public ModelAndView toExhibitionAdd(String exhibitionId) {
		ModelAndView modelAndView = this.getModelAndView(ExhibitionConstant.ADD_PAGE);
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("停用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		return modelAndView;
	}
	
	/**
	 * 跳转修改界面
	 */
	@RequestMapping(ExhibitionConstant.TO_MODIFY_URL)
	public ModelAndView toExhibitionModify(@RequestParam(required = true) String exhibitionId) {
		ModelAndView modelAndView = this.getModelAndView(ExhibitionConstant.MODIFY_PAGE);
		
		List<SysCode> sysCodeList1 = new ArrayList<SysCode>();
		SysCode sysCode1 = new SysCode();
		sysCode1.setCodeid("1");
		sysCode1.setCodevalue("启用");
		sysCodeList1.add(sysCode1);

		SysCode sysCode2 = new SysCode();
		sysCode2.setCodeid("-1");
		sysCode2.setCodevalue("停用");
		sysCodeList1.add(sysCode2);

		modelAndView.addObject("statusList", sysCodeList1);
		
		if (exhibitionId != null) {
			Exhibition exhibition = exhibitionService.findByExhibitionId(exhibitionId);
			modelAndView.addObject("exhibition",exhibition);
		}

		return modelAndView;
	}

	/**
	 * 查询列表
	 */
	@RequestMapping(ExhibitionConstant.EXHIBITION_LIST_URL)
	public ModelAndView exhibitionList() {

		PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
		String fuzzyCondition = pageUtilEntity.getRelationMap().get("fuzzyCondition");
		if (fuzzyCondition != null && !fuzzyCondition.equals("")) {
			try {
				pageUtilEntity.getRelationMap().put("fuzzyCondition", URLDecoder.decode(fuzzyCondition, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Exhibition> tableDataInfo = exhibitionService.pageInfoQuery(pageUtilEntity);
		Integer i=1;
		for (Exhibition exhibition : tableDataInfo) {
			exhibition.setExhibitionNum(pageUtilEntity.getPage()+(i++));
			}

		return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
	}
	
	/**
     * 根据ID删除
     */
    @ActionControllerLog(title = "内容管理", action = "内容管理-删除展位", isSaveRequestData = true)
    @RequestMapping(ExhibitionConstant.DEL_URL)
    public @ResponseBody Message deleteExhibitionById(Exhibition exhibition)
    {
        int result = 0;
        String id = exhibition.getExhibitionId();
        if (id != null)
        {           
                result = exhibitionService.deleteById(exhibition);           
        }
        return new Message(result);
    }

	/**
	 * 保存信息
	 */
	@ActionControllerLog(title = "内容管理", action = "展位管理-保存", isSaveRequestData = true)
	@RequestMapping(ExhibitionConstant.SAVE_EXHIBITION_URL)
	public @ResponseBody Message saveExhibition(Exhibition exhibition,String exhibitionId,MultipartFile picture) {
		int result = 0;		
		
		try {
		    if(picture.getSize() != 0){
    		    AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
    			String key = aliyunOSSClientUtil.uploadImg(picture);
    			if(key!=null){
    				
    				String originalFilename = picture.getOriginalFilename();
    				String filePath = aliyunOSSClientUtil.FOLDER + AliyunOSSClientUtil.filePath;
    				exhibition.setExhibitionImg(filePath);
    				
    			}
		    }
		} catch (OSSException e) {
			e.printStackTrace();
            return new Message(result);
		}
		if (exhibitionId != null) {
			result = exhibitionService.updateExhibitionInfo(exhibition);
		} else {
			result = exhibitionService.addExhibitionInfo(exhibition);
		}
		
		return new Message(result);

	}
}
