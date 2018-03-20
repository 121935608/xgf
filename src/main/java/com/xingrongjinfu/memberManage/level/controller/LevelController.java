package com.xingrongjinfu.memberManage.level.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xingrongjinfu.memberManage.level.common.LevelConstant;
import com.xingrongjinfu.memberManage.level.model.Level;
import com.xingrongjinfu.memberManage.level.service.ILevelService;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.UuidUtil;

/**
 * Description: 会员等级（收银端）
 * date: 2018年3月12日 下午5:49:35 <br/>
 *
 * @author huYL
 * @version 
 */
@Controller
@RequestMapping(SystemConstant.MEMBER_URL)
public class LevelController extends BaseController{
    @Autowired
    private ILevelService levelService;
    
    /**
     * 加载等级界面
     * @return
     */
    @RequestMapping(LevelConstant.MEMBER_LEVEL_URL)
    public ModelAndView loadPage()
    {
        ModelAndView modelAndView=this.getModelAndView(LevelConstant.LEVEL_PAGE);
        return modelAndView;
    }
    /**
     * 跳转添加等级页面
     * @return
     */
    @RequestMapping(LevelConstant.LEVEL_ADD_URL)
    public ModelAndView toLevelAdd()
    {
        ModelAndView modelAndView=this.getModelAndView(LevelConstant.LEVEL_ADD_PAGE);
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        modelAndView.addObject("level", getLevel(storeId,null));
        return modelAndView;
    }
    /**
     * 跳转修改等级页面
     * @return
     */
    @RequestMapping(LevelConstant.LEVEL_EDIT_URL)
    public ModelAndView toEditLevel(String levelId)
    {
        ModelAndView modelAndView=this.getModelAndView(LevelConstant.LEVEL_EDIT_PAGE);
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Level level = levelService.getById(levelId);
        modelAndView.addObject("Level", level);
        modelAndView.addObject("level", getLevel(storeId,level.getLevelNo()));
        return modelAndView;
    }
    /**
     * 查询等级列表
     */
    @RequestMapping(LevelConstant.LEVEL_LIST_URL)
    public ModelAndView levelList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = levelService.pageQueryInfo(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }
    /**
     * 等级保存
     */
    @RequestMapping(LevelConstant.SAVE_LEVEL_URL)
    public @ResponseBody Message saveLevel(Level level) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        int result = 0;
        if(level.getLevelId() == null){//新增
            level.setLevelId(UuidUtil.get32UUID());
            level.setStatus(1);
            level.setAddWay("自动升级");
            level.setStoreId(storeId);
            result = levelService.addLevel(level);
        }else{//修改
            result = levelService.updateLevel(level);
        }
        return new Message(result);
    }
    /**
     * Description: 获取还可以添加的等级<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    public List<SysCode> getLevel(String storeId,String levelNo){
        List<Level> levelList = levelService.queryLevelExist(storeId);
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        List<String> listAll = new ArrayList<>();
        listAll.add("A");
        listAll.add("B");
        listAll.add("C");
        listAll.add("D");
        listAll.add("E");
        for (String string : listAll) {
            boolean flag = false;
            for (Level level:levelList){
                if(level.getLevelNo().equals(string)){
                    //该等级存在
                    flag = true;
                    break;
                }
            }
            if(!flag){
              //该等级不存在
                SysCode sysCode = new SysCode();
                sysCode.setCodeid(string);
                sysCode.setCodevalue(string);
                sysCodeList.add(sysCode);
            }
        }
        if(null != levelNo){
            //修改时的会员等级列表包含自身等级
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(levelNo);
            sysCode.setCodevalue(levelNo);
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
}
