package com.xingrongjinfu.commodity.register.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xingrongjinfu.statistics.cashCount.common.CashCountConstant;
import org.apache.shiro.common.utils.SessionUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSException;
import com.xingrongjinfu.commodity.fenlei.model.Fenlei;
import com.xingrongjinfu.commodity.fenlei.service.IFenleiService;
import com.xingrongjinfu.commodity.register.common.RegisterConstant;
import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.register.model.RegisterExp;
import com.xingrongjinfu.commodity.register.service.IRegisterService;
import com.xingrongjinfu.commodity.supply.model.Supply;
import com.xingrongjinfu.commodity.supply.service.ISupplyService;
import com.xingrongjinfu.commodity.unit.model.Unit;
import com.xingrongjinfu.commodity.unit.service.IUnitService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.utils.AliyunOSSClientUtil;
import com.xingrongjinfu.utils.ExportExcel;
import com.xingrongjinfu.utils.ImportExcel;
import com.xingrongjinfu.utils.UuidUtil;

@Controller
@RequestMapping(RegisterConstant.COMMODITY_URL)
public class RegisterController extends BaseController{
    @Autowired
    private IRegisterService registerService;
    @Autowired
    private IFenleiService fenleiService;
    @Autowired
    private ISupplyService supplyService;
    @Autowired
    private IUnitService unitService;
    /**
     * Description: 跳转商品登记页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(RegisterConstant.TO_COMMODITY_REGISTER_URL)
    public ModelAndView toCommodityRegister() throws Exception{
        ModelAndView modelAndView = new ModelAndView(RegisterConstant.COMMODITY_REGISTER_PAGE);
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        modelAndView.addObject("fenlei", getFL(storeId));
        return modelAndView;
    }
    /**
     * Description: 跳转登记添加页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(RegisterConstant.TO_REGISTER_ADD)
    public ModelAndView toRegisterAdd(Integer type) throws Exception{
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        ModelAndView modelAndView = new ModelAndView(RegisterConstant.REGISTER_ADD_PAGE);
        modelAndView.addObject("fenlei", getFL(storeId));
        modelAndView.addObject("supply", getSupply(storeId));
        modelAndView.addObject("unit", getUnit(storeId));
        if(null == type){
            type = 0;
        }
        modelAndView.addObject("type", type);
        return modelAndView;
    }
    /**
     * Description: 跳转登记编辑页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(RegisterConstant.TO_REGISTER_MODIFY)
    public ModelAndView toRegisterModify(String commodityId) throws Exception{
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        ModelAndView modelAndView = new ModelAndView(RegisterConstant.REGISTER_MODIFY_PAGE);
        modelAndView.addObject("fenlei", getFL(storeId));
        modelAndView.addObject("supply", getSupply(storeId));
        modelAndView.addObject("unit", getUnit(storeId));
        Register register = registerService.getRegisterById(commodityId);
        modelAndView.addObject("register", register);
        modelAndView.addObject("categorys", getCategoryByCommodity(commodityId,storeId));
        return modelAndView;
    }
    /**
     * Description: 跳转登记导入页面<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    @RequestMapping(RegisterConstant.TO_REGISTER_IMP)
    public ModelAndView toImpPage(String commodityId) throws Exception{
        ModelAndView modelAndView = new ModelAndView(RegisterConstant.REGISTER_IMP_PAGE);
        return modelAndView;
    }
    /**
     * 查询登记列表
     */
    @RequestMapping(RegisterConstant.COMMODITY_REGISTER_LIST)
    public ModelAndView registerList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        pageUtilEntity.getRelationMap().put("storeId", storeId);
        List<TableDataInfo> tableDataInfo = registerService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     *  导出收银统计数据Excel
     *
     * @author fengqian
     * @date 2018/4/26 11:18
     * @param request
     * @param response
     * @return org.framework.core.model.Message
     */
    @RequestMapping(RegisterConstant.DOWNLOAD_STOCK_DATA)
    public Message downloadStockData(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        param.put("storeId", storeId);
        param.put("categoryId", request.getParameter("categoryId"));
        param.put("condition", request.getParameter("condition"));
        param.put("commodityNo", request.getParameter("commodityNo"));

        List<Map> data = registerService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            //ExportExcel<Map> ee = new ExportExcel<>();
            String[][] headers = {{"编码",          "商品名称",         "商品条码",     "供应商",     "分类",        "单位",      "库存上限",     "库存下限",  "库存"},
                    {"commodityCode","commodityName","commodityNo","supplierName","categoryName","unitName","upperLimit","lowerLimit", "stockNum"}};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("库存查询表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }


    /**
     *  导出收银统计数据Excel
     *
     * @author fengqian
     * @date 2018/4/26 11:18
     * @param request
     * @param response
     * @return org.framework.core.model.Message
     */
    @RequestMapping(CashCountConstant.DOWNLOAD_CASHCOUNT_DATA)
    public Message downloadCashCountData(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> param = new HashMap();
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        param.put("storeId", storeId);
        param.put("categoryId", request.getParameter("categoryId"));
        param.put("condition", request.getParameter("condition"));
        param.put("commodityNo", request.getParameter("commodityNo"));

        List<Map> data = registerService.infoQuery(param);
        //data.stream().filter(s->s.getSalePrice())
        try {
            //ExportExcel<Map> ee = new ExportExcel<>();
            String[][] headers = {{"编码",          "商品名称",         "商品条码",     "供应商",     "分类",        "单位",      "库存上限",     "库存下限",  "库存"},
                                  {"commodityCode","commodityName","commodityNo","supplierName","categoryName","unitName","upperLimit","lowerLimit", "stockNum"}};
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=export.xls");// 设置文件名
            OutputStream output = response.getOutputStream();
            ExportExcel.exportExcel2("收银统计表", headers, data, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(0);
        }
        return new Message(1);
    }






    /**
     * 登记保存
     */
    @RequestMapping(RegisterConstant.SAVE_REGISTER_URL)
    public @ResponseBody Message saveRegister(Register register,MultipartFile picture) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        int result = 0;
        AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
        try {
            String key = aliyunOSSClientUtil.uploadImg(picture);
            if (key != null) {

                String originalFilename = picture.getOriginalFilename();
                String filePath = aliyunOSSClientUtil.FOLDER + aliyunOSSClientUtil.filePath;
                register.setImgMain(filePath);

            }
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(result);
        }
        register.setCommodityId(UuidUtil.get32UUID());
        register.setCommodityCode(UuidUtil.get20UUID());
        register.setCommodityStatus("0");
        register.setStatus(1);
        register.setStoreId(storeId);
        register.setInPrice(register.getInPrice()*100);
        register.setSalePrice(register.getSalePrice()*100);
        register.setVipPrice(register.getVipPrice()*100);
        result = registerService.addRegister(register);
        return new Message(result);
    }
    /**
     * 登记保存
     */
    @RequestMapping(RegisterConstant.MODIFY_REGISTER_URL)
    public @ResponseBody Message modifyRegister(Register register,MultipartFile picture) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        int result = 0;
        try {
            if(picture.getSize() != 0){
                AliyunOSSClientUtil aliyunOSSClientUtil = new AliyunOSSClientUtil();
                String key = aliyunOSSClientUtil.uploadImg(picture);
                if (key != null) {
                    
                    String originalFilename = picture.getOriginalFilename();
                    String filePath = aliyunOSSClientUtil.FOLDER + aliyunOSSClientUtil.filePath;
                    register.setImgMain(filePath);
                }
            }
        } catch (OSSException e) {
            e.printStackTrace();
            return new Message(result);
        }
        register.setInPrice(register.getInPrice()*100);
        register.setSalePrice(register.getSalePrice()*100);
        register.setVipPrice(register.getVipPrice()*100);
        result = registerService.updateRegister(register);
        return new Message(result);
    }
    /**
     * 登记启用停用
     */
    @RequestMapping(RegisterConstant.CHANGE_REGISTER_STATUS_URL)
    public @ResponseBody Message changeRegisterStatus(String commodityId,String status) {
        Map map = new HashMap<>();
        map.put("commodityId", commodityId);
        map.put("commodityStatus", status);
        int result = registerService.updateStatus(map);
        return new Message(result);
    }
    /**
     * Description: 导出商品列表<br/>
     *
     * @author huYL
     * @return
     * @throws Exception 
     */
    @RequestMapping(RegisterConstant.EXPORT_RIGISTER_URL)
    public @ResponseBody Message expRegisterList(HttpServletResponse response,String status,
            String categoryId,Integer discount,String commodityNo) throws Exception {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Map map = new HashMap<>();
        map.put("status", status);
        map.put("categoryId", categoryId);
        map.put("discount", discount);
        map.put("commodityNo", commodityNo);
        map.put("storeId", storeId);
        List<RegisterExp> list = registerService.getExpRegisterList(map);
        int num = 0;
        for (RegisterExp register : list) {
            num++;
            register.setNum(num);
        }
        ExportExcel<RegisterExp> ee=new ExportExcel<RegisterExp>();
        String[] headers=new String[]{"序号","编码","商品名称","条码","供货商","分类","单位","进价（元）","售价（元）","会员价（元）","折扣","库存","库存上限","库存下限","状态"};
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition","attachment;fileName=export.xls");// 设置文件名
        try {
           OutputStream output = response.getOutputStream();
           ee.exportExcel("商品信息", headers, list, output); 
           output.flush();
           output.close();
       } catch (IOException e) {
           e.printStackTrace();
           return new Message(0);
       }
        return new Message(1);
    }

    /**
     * Description: 导入商品登记模板<br/>
     *
     * @author huYL
     * @return
     * @throws Exception 
     */
    @RequestMapping(RegisterConstant.IMP_RIGISTER_MODEL_URL)
    public @ResponseBody Message getImpExcelModel(HttpServletResponse response){
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition","attachment;fileName=export.xls");// 设置文件名
        try {
           InputStream is= RegisterController.class.getClassLoader().getResource("../jsp/excelModel/model_pro.xls").openStream();
           OutputStream output = response.getOutputStream();
           int ch;
           while ((ch = is.read()) != -1) {
               output.write(ch);   
           } 
           output.flush();
           output.close();
       } catch (IOException e) { 
           e.printStackTrace();
           return new Message(0);
       } 
        return new Message(1);
    }
    /**
     * Description: 导入商品Excel<br/>
     *
     * @author huYL
     * @return
     * @throws Exception 
     */
    @RequestMapping(RegisterConstant.IMP_RIGISTER_URL)
    public @ResponseBody Message impRegisterList(MultipartFile file,String type) throws IllegalStateException, IOException{
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        String[] fields=new String[]{ "commodityName","commodityNo","categoryCode","unitCode","supplierCode","salePrice",
                "inPrice","vipPrice","discount","stockNum","upperLimit","lowerLimit"};
        List<Register> list = new ArrayList<>();
        try {
            ImportExcel<Register> ie=new ImportExcel<Register>();
            list=ie.readExcel(file.getInputStream(), fields, new Register().getClass().getName());
            if(list.size()==0){return new Message(false,"没有数据！"); }
            int n = list.size();
            //新增商品
            if(type.equals("1")){
                for(Register register:list){
                    //检验商品名、条码唯一   分类、单位、供应商编号存在
                    Map map = new HashMap();
                    map.put("storeId", storeId);
                    if(null == register.getCommodityName() || null == register.getCommodityNo()){
                        return new Message(false,"请输入商品名称和条码！");
                    }
                    map.put("commodityName", register.getCommodityName());
                    map.put("commodityNo", register.getCommodityNo());
                    if(null != register.getCategoryCode()){
                        map.put("categoryCode", register.getCategoryCode());
                    }
                    if(null != register.getUnitCode()){
                        map.put("unitCode", register.getUnitCode());
                    }
                    if(null != register.getSupplierCode()){
                        map.put("supplierCode", register.getSupplierCode());
                    }
                    Map m = registerService.isExist(map);
                    if(null == m){
                        return new Message(false,"请输入正确的编号！");
                    }
                    if(null != m.get("commodityId")){
                        return new Message(false,"商品  "+register.getCommodityName()+"  已存在！");
                    }
                    if(null != register.getUnitCode()){
                        if(null == m.get("unitId")){
                            return new Message(false,"输入的单位编号有误！");
                        }
                        register.setUnitId((String) m.get("unitId"));
                    }
                    if(null != register.getSupplierCode()){
                        if(null == m.get("supplierId")){
                                return new Message(false,"输入的供应商编号有误！");
                        }
                        register.setSupplierId((String) m.get("supplierId"));
                    }
                    if(null != register.getCategoryCode()){
                        if(null == m.get("categoryId")){
                                return new Message(false,"输入的分类编号有误！");
                        }
                        register.setCategoryId((String) m.get("categoryId"));
                    }
                    register.setCommodityId(UuidUtil.get32UUID());
                    register.setAddTime(new Date());
                    register.setCommodityCode(UuidUtil.get20UUID());
                    register.setCommodityStatus("0");
                    register.setStatus(1);
                    register.setStoreId(storeId);
                    if(null != register.getInPrice())
                        register.setInPrice(register.getInPrice()*100);
                    if(null != register.getSalePrice())
                        register.setSalePrice(register.getSalePrice()*100);
                    if(null != register.getVipPrice())
                        register.setVipPrice(register.getVipPrice()*100);
                }
                n=registerService.updateImpExcel(list);
                return new Message(true,"导入成功，添加数据"+n+"条！"); 
            }
            //覆盖商品
            if(type.equals("2")){
                for(Register register:list){
                    //检验商品名、条码、分类、单位、供应商编号存在
                    Map map = new HashMap();
                    map.put("storeId", storeId);
                    if(null == register.getCommodityName() || null == register.getCommodityNo()){
                        return new Message(false,"请输入商品名称和条码！");
                    }
                    map.put("commodityName", register.getCommodityName());
                    map.put("commodityNo", register.getCommodityNo());
                    if(null != register.getCategoryCode()){
                        map.put("categoryCode", register.getCategoryCode());
                    }
                    if(null != register.getUnitCode()){
                        map.put("unitCode", register.getUnitCode());
                    }
                    if(null != register.getSupplierCode()){
                        map.put("supplierCode", register.getSupplierCode());
                    }
                    Map m = registerService.isExistByNameAndNo(map);
                    if(null == m){
                        return new Message(false,"商品  "+register.getCommodityName()+"  不存在！");
                    }
                    if(null == m.get("commodityId")){
                        return new Message(false,"商品  "+register.getCommodityName()+"  不存在！");
                    }
                    if(null != register.getUnitCode()){
                        if(null == m.get("unitId")){
                            return new Message(false,"输入的单位编号有误！");
                        }
                        register.setUnitId((String) m.get("unitId"));
                    }
                    if(null != register.getSupplierCode()){
                        if(null == m.get("supplierId")){
                                return new Message(false,"输入的供应商编号有误！");
                        }
                        register.setSupplierId((String) m.get("supplierId"));
                    }
                    if(null != register.getCategoryCode()){
                        if(null == m.get("categoryId")){
                                return new Message(false,"输入的分类编号有误！");
                        }
                        register.setCategoryId((String) m.get("categoryId"));
                    }
                    register.setUpdateTime(new Date());
                    if(null != register.getInPrice())
                        register.setInPrice(register.getInPrice()*100);
                    if(null != register.getSalePrice())
                        register.setSalePrice(register.getSalePrice()*100);
                    if(null != register.getVipPrice())
                        register.setVipPrice(register.getVipPrice()*100);
                }
                registerService.updateRegisterList(list);
                return new Message(true,"导入成功，修改数据"+n+"条！"); 
            }
            //追加库存
            if(type.equals("3")){
                for(Register register:list){
                    //库存必填
                    if(null == register.getStockNum()){
                        return new Message(false,"请输入商品库存！");
                    }
                    //检验商品名、条码是否存在
                    Map map = new HashMap();
                    map.put("storeId", storeId);
                    if(null == register.getCommodityName() || null == register.getCommodityNo()){
                        return new Message(false,"请输入商品名称和条码！");
                    }
                    map.put("commodityName", register.getCommodityName());
                    map.put("commodityNo", register.getCommodityNo());
                    Map m = registerService.isExist(map);
                    if(null == m){
                        return new Message(false,"商品  "+register.getCommodityName()+"  不存在！");
                    }
                    if(null == m.get("commodityId")){
                        return new Message(false,"商品  "+register.getCommodityName()+"  不存在！");
                    }
                    register.setUpdateTime(new Date());
                }
                registerService.updateStockList(list);
                return new Message(true,"导入成功，追加库存数据"+n+"条！"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(false,"导入失败！"); 
        }
        return new Message(true,"导入成功！"); 
    }
    /**
     * Description: 获取分类<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    public List<SysCode> getFL(String storeId) throws Exception{
        List<Fenlei> fenleiList = fenleiService.getAll(storeId);
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Fenlei fenlei:fenleiList){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(fenlei.getCategoryId());
            sysCode.setCodevalue(fenlei.getCategoryName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
    /**
     * Description: 获取供应商<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    public List<SysCode> getSupply(String storeId) throws Exception{
        List<Supply> supplyList = supplyService.getAll(storeId);
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Supply supply:supplyList){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(supply.getSupplierId());
            sysCode.setCodevalue(supply.getSupplierName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
    /**
     * Description: 获取单位<br/>
     *
     * @author huYL
     * @return
     * @throws Exception
     */
    public List<SysCode> getUnit(String storeId) throws Exception{
        List<Unit> unitList = unitService.getAll(storeId);
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Unit unit:unitList){
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(unit.getUnitId());
            sysCode.setCodevalue(unit.getUnitName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }
    /**
     * Description: 获取某分类的所有小类<br/>
     *
     * @author huYL
     * @return
     */
    @ResponseBody
    @RequestMapping(RegisterConstant.GET_CATEGORYS_URL)
    public List<Fenlei> getCategorys(String parentCategoryId)throws Exception{
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Map map = new HashMap();
        map.put("storeId", storeId);
        map.put("parentId", parentCategoryId);
        List<Fenlei> typeList = fenleiService.getCategorys(map);
        return typeList;
        
    }
    /**
     * Description: 获取某商品的所有小分类<br/>
     *
     * @author huYL
     * @return
     */
    public List<Fenlei> getCategoryByCommodity(String commodityId,String storeId)throws Exception{
        Map map = new HashMap();
        map.put("commodityId", commodityId);
        map.put("storeId", storeId);
        List<Fenlei> typeList = registerService.getCategoryByCommodity(map);
        return typeList;
        
    }
    /**
     * 商品名、商品条码是否存在
     */
    @RequestMapping(RegisterConstant.CHECK_CATEGORY_NAME)
    public @ResponseBody int checkCategoryName(String commodityName,String commodityId,String commodityNo) {
        String storeId = (String) SessionUtils.getSession().getAttribute("storeId");
        Map map = new HashMap();
        map.put("storeId", storeId);
        if(null != commodityId){
            map.put("commodityId", commodityId);
        }
        if(null != commodityName){
            map.put("commodityName", commodityName);
        }
        if(null != commodityNo){
            map.put("commodityNo", commodityNo);
        }
        int n = registerService.checkCategoryName(map);
        return n;
    }
}
