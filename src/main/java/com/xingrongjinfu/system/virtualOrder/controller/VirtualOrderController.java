/**
 * Copyright (C), 2018 FileName: OrderController Author: zxuser Date: 2018/1/4 11:05 Description:
 * History: <author> <time> <version> <desc> 作者姓名 修改时间 版本号 描述
 */
package com.xingrongjinfu.system.virtualOrder.controller;

import com.alibaba.fastjson.JSONObject;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.commodity.common.CommodityConstant;
import com.xingrongjinfu.system.commodity.model.Commodity;
import com.xingrongjinfu.system.commodity.service.ICommodityService;
import com.xingrongjinfu.system.financial.model.Financial;
import com.xingrongjinfu.system.financial.service.IFinancialService;
import com.xingrongjinfu.system.order.common.OrderConstant;
import com.xingrongjinfu.system.order.controller.OrderController;
import com.xingrongjinfu.system.order.model.Order;
import com.xingrongjinfu.system.order.model.OrderAuditing;
import com.xingrongjinfu.system.order.model.OrderCommodityDetail;
import com.xingrongjinfu.system.order.model.OrderDetail;
import com.xingrongjinfu.system.product.model.Product;
import com.xingrongjinfu.system.product.service.IProductService;
import com.xingrongjinfu.system.storeaffairs.model.Store;
import com.xingrongjinfu.system.storeaffairs.service.ICertificationService;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.virtualOrder.common.VirtualOrderConstant;
import com.xingrongjinfu.system.virtualOrder.service.IVirtualOrderService;
import com.xingrongjinfu.utils.HttpClientUtil;
import com.xingrongjinfu.utils.StringUtil;
import com.xingrongjinfu.utils.UuidUtil;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单管理（平台端） 〈〉
 *
 * @author zxuser
 * @create 2018/1/4
 * @since 1.0.0
 */
@Controller
@RequestMapping(SystemConstant.VIRTUALORDER_URL)
public class VirtualOrderController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(VirtualOrderController.class);
    @Autowired
    @Qualifier("virtualOrderService")
    private IVirtualOrderService orderService;

    @Autowired
    private IFinancialService financialService;

    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private ICertificationService certificationService;

    @Autowired
    private IProductService productService;

    @RequestMapping(OrderConstant.ORDER_MANAGE_URL)
    public ModelAndView loadOrderManage() {
        return this.getModelAndView(VirtualOrderConstant.VIRTUALORDER_MANAGE_PAGE);
    }

    /**
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_MANAGE_LIST_URL)
    public ModelAndView orderManageList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = orderService.pageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 查看订单信息
     *
     * @param orderNumber
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_LOOK_URL)
    public ModelAndView lookOrderInfo(String orderNumber) {
        ModelAndView modelAndView = this.getModelAndView(OrderConstant.ORDER_LOOK_PAGE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order orders = orderService.findOrderInfo(orderNumber);
        fillingOrder(orders);
        List<OrderDetail> orderDetails = orderService.findOrderDetailInfo(orderNumber);
        fillingOrderDetail(orderDetails);
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("orderDetails", orderDetails);
        return modelAndView;
    }

    /**
     * 发货界面
     *
     * @param orderNumber
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_SEND_URL)
    public ModelAndView loadSendPage(String orderNumber) {
        ModelAndView modelAndView = this.getModelAndView(OrderConstant.ORDER_SEND_PAGE);
        return modelAndView;
    }

    /**
     * 收货后更新订单信息
     *
     * @param order
     * @return
     */
    @RequestMapping(OrderConstant.ORDER_CONFIRM_URL)
    public @ResponseBody
    Message orderConfirm(Order order) {
        int result = 0;
        String orderNumber = order.getOrderNumber();
        if (orderNumber != null && orderNumber != "") {
            result = orderService.updateOrderInfo(order);
        }
        return new Message(result);
    }

    /**
     * 跳转到打印配送单界面
     */
    @RequestMapping(OrderConstant.ORDER_PRINT_URL)
    public ModelAndView loadPrintPage(String orderNumber) {
        ModelAndView modelAndView = this.getModelAndView(OrderConstant.ORDER_PRINT_PAGE);
        List<OrderDetail> orderDetails = orderService.findOrderDetailInfo(orderNumber);
        modelAndView.addObject("orderDetails", orderDetails);
        return modelAndView;
    }

    /**
     * 跳转到订单快递查询界面
     */
    @RequestMapping(OrderConstant.ORDER_EXPRESS_URL)
    public ModelAndView loadExpressPage() {
        return this.getModelAndView(OrderConstant.ORDER_EXPRESS_PAGE);
    }

    /**
     * 查询订单快递信息
     */
    @RequestMapping(OrderConstant.ORDER_EXPRESS_LIST_URL)
    public ModelAndView orderExpressList() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<TableDataInfo> tableDataInfo = orderService.expressPageInfoQuery(pageUtilEntity);
        return buildDatasTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 财务结算单界面
     */
    @RequestMapping(OrderConstant.ORDER_FINANCIAL_URL)
    public ModelAndView loadFinancialPage() {
        return this.getModelAndView(OrderConstant.ORDER_FINANCIAL_PAGE);
    }

    /**
     * 跳转到输入密码界面
     */
    @RequestMapping(OrderConstant.ORDER_CHECK_URL)
    public ModelAndView loadCheckPage(Financial financial) {
        ModelAndView modelAndView = this.getModelAndView(OrderConstant.ORDER_CHECK_PAGE);
        modelAndView.addObject("financial", financial);
        return modelAndView;
    }

    /**
     * @param @Description:跳转到审核界面
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: niu
     */
    @RequestMapping(OrderConstant.ORDER_AUDITING_URL)
    @ResponseBody
    public ModelAndView loadAuditingPage(String orderNumber) {
        ModelAndView modelAndView = this.getModelAndView(VirtualOrderConstant.VIRTUALORDER_AUDITING_PAGE);
        Order orders = orderService.findOrderInfo(orderNumber);
        fillingOrder(orders);
        List<OrderDetail> orderDetails = orderService.findOrderDetailInfo(orderNumber);
        List<OrderCommodityDetail> orderCommodityDetails = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getInPrice() == null) {
                orderDetail.setInPrice(0.00);
            }
            if (orderDetail.getCommodityNum() == null) {
                orderDetail.setCommodityNum(0);
            }
            // 总金额=数量*进价
            orderDetail.setInPrice(orderDetail.getInPrice());
            Double all = orderDetail.getInPrice() * orderDetail.getCommodityNum();
            BigDecimal b = new BigDecimal(all);
            all = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            orderDetail.setTotalMoney(all);

            // 根据商品条形码查询商品
            String commodityNo = orderDetail.getCommodityNo();
            List<Commodity> commodities = commodityService.queryByCommodityNo(commodityNo);
            System.out.println(commodities);
            for (Commodity commodity : commodities) {
                OrderCommodityDetail orderCommodityDetail = new OrderCommodityDetail();
                // 将订单和商品信息存储
                // BeanUtils.copyProperties(commodity, orderCommodityDetail);
                BeanUtils.copyProperties(orderDetail, orderCommodityDetail);
                orderCommodityDetail.setSalePrice(commodity.getSalePrice());
                orderCommodityDetail.setSubPrice(commodity.getSubPrice());
                orderCommodityDetail.setSubPriceUnit(commodity.getSubPriceUnit());
                orderCommodityDetail.setWeight(commodity.getWeight());
                orderCommodityDetails.add(orderCommodityDetail);
            }
        }

        modelAndView.addObject("orders", orders);
        modelAndView.addObject("orderCommodityDetails", orderCommodityDetails);
        return modelAndView;
    }

    /**
     * 确认结账
     */
    @RequestMapping(OrderConstant.ORDER_TOCHECK_URL)
    public @ResponseBody
    Message toCheck(Financial financial, User user) {
        int result = 0;
        User nowUser = this.getCurrentUser();
        String amountId = financial.getAmountId();
        if (amountId != null && amountId != "") {
            // 校验密码
            if (nowUser.getPassword().equals(user.getPassword())) {
                result = 1;
            }
            // 校验密码通过之后更新结算单
            if (result == 1) {
                financial.setAmountStatus(0);

                result = financialService.updateAmountInfo(financial);
            }
        }
        return new Message(result);
    }

    /**
     * 确认收款
     */
    @RequestMapping(OrderConstant.PAY_ORDER_URL)
    public @ResponseBody
    Message payOrder(String orderNumber) {
        int result = 0;
        result = orderService.updatePayOrder(orderNumber);
        return new Message(result);
    }

    /**
     * @param orderDetails @Description:校验填充订单明细
     * @return: void
     * @author: niu @Date: 2018/5/23
     */
    private void fillingOrderDetail(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getInPrice() == null) {
                orderDetail.setInPrice(0.00);
            }
            if (orderDetail.getCommodityNum() == null) {
                orderDetail.setCommodityNum(0);
            }
            // 总金额=数量*进价
            orderDetail.setInPrice(orderDetail.getInPrice());
            Double all = orderDetail.getInPrice() * orderDetail.getCommodityNum();
            BigDecimal b = new BigDecimal(all);
            all = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            orderDetail.setTotalMoney(all);
        }
    }

    /**
     * @param orders @Description校验填充订单
     * @return: void
     * @author: niu @Date: 2018/5/23
     */
    private void fillingOrder(Order orders) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.setOrderPrice(orders.getOrderPrice() == null ? 0 : orders.getOrderPrice());
        orders.setOrderTimes(orders.getOrderTime() == null ? "" : sdf.format(orders.getOrderTime()));
        orders.setTotalPrice(orders.getTotalPrice() == null ? 0 : orders.getTotalPrice());
        orders.setFreight(orders.getFreight() == null ? 0 : orders.getFreight());
        orders.setPayment(orders.getPayment() == null ? 0 : orders.getPayment());
    }

    /**
     * 用户异步保存客服修改订单的商品数量
     *
     * @param commodityNum 商品数量
     * @param orderNumber  订单编号
     * @param commodityNo  商品条形码
     * @return
     */
  /*  @RequestMapping(OrderConstant.ORDER_CHANGE_COMMODITY_NUM)
    public @ResponseBody
    Message changeOrderNum(
            String commodityNum, String orderNumber, String commodityNo) {
        // 查询商品
        Product product = productService.findProductInfoByNo(commodityNo);
        Integer kxdStock = product.getKxdStock();
        Integer newNum = Integer.valueOf(commodityNum);
        if (newNum < 0) {
            return new Message(false, "商品数量不能小于零");
        }
        OrderDetail orderDetail = getOrderDetail(orderNumber, commodityNo);
        Integer oldNum = orderDetail.getCommodityNum();
        Integer differentNum = newNum - oldNum;
        if (differentNum > 0 && differentNum > kxdStock) {
            return new Message(false, "库存不足");
        } else {
            // 修改商品库存信息
            product.setKfStock(newNum); // 设置新的客服库存
            product.setKxdStock(product.getKxdStock() - differentNum); // 减少可下单库存
            if (productService.updateProduct(product) > 0) {
                return new Message(true);
            }
            return new Message(false, "更新商品库存失败");
        }
    }*/

    /**
     * @Description:保存客服修改的订单信息
     * @return: org.framework.core.model.Message
     * @author: niu @Date: 2018/5/23
     */
    @RequestMapping(value = OrderConstant.ORDER_SAVE_MODIFY_OPS)
    public @ResponseBody
    Message saveOrder(
            @Param("cancelOrder") String cancelOrder,
            @Param("serviceRemark") String serviceRemark,
            @Param("serviceId") String serviceId,
            @Param("addOrderTable") String addOrderTable,
            Order order) {
        logger.info(
                "==========客服提交审核 cancelOrder:{},addOrderTable:{},serviceRemark:{},serviceId:{}",
                cancelOrder,
                addOrderTable,
                serviceRemark,
                serviceId);
        // 定义结果显示
        Boolean modifyResult = true;
        Boolean addResult = true;

        JSONObject jsonObject = JSONObject.parseObject(cancelOrder);
        JSONObject addOrderTableObj = JSONObject.parseObject(addOrderTable);
        String orderNumber = order.getOrderNumber();
        int addOrderTableSize = addOrderTableObj.size();

        /** ****** 存在取消订单逻辑 ****** */

        // 如果存在修改订单
        if (jsonObject.size() > 0) {
            // 遍历jsonObject
            for (int i = 0; i < jsonObject.size(); i++) {
                // 创建订单审核对象
                OrderAuditing orderAuditing = new OrderAuditing();
                orderAuditing.setServiceRemark(serviceRemark); // 设置客服人员备注
                orderAuditing.setAuditingId(UuidUtil.get32UUID()); // 设置审核单id
                orderAuditing.setOrderId(order.getOrderId());
                orderAuditing.setServiceId(serviceId); // 设置操作人员id

                JSONObject jsonObj = jsonObject.getJSONObject(String.valueOf(i));
                String commodityNo = (String) jsonObj.get("commodityNo");
                String commodityNum = (String) jsonObj.get("commodityNum");
                // 根据orderNumber和commodiytNO 查询订单明细
                OrderDetail orderDetail = getOrderDetail(orderNumber, commodityNo);
                if (orderDetail == null) {
                    return new Message(false, "不存在改订单明细");
                }
                if (commodityNum == null) {
                    // 如果商品数为空,代表是取消商品
                    Integer oldCommodityNum = orderDetail.getCommodityNum();
                    // 修改客服库存
                    Product productByNo = productService.findProductInfoByNo(commodityNo);
                    if (productByNo.getKfStock() <= 0 || productByNo.getKfStock() < oldCommodityNum) {
                        return new Message(false, "客服库存数异常"); // 客服库存数小于支付商品数量,则下单时库存数量操作异常
                    }
                    productByNo.setKfStock(productByNo.getKfStock() - oldCommodityNum);
                    productByNo.setKxdStock(productByNo.getKxdStock() + oldCommodityNum);
                    orderAuditing.setServiceModify(oldCommodityNum);
                    orderAuditing.setModifyStatus(2);

                    // 设置订单状态为取消
                    orderDetail.setStatus(-1);
                    orderService.updateOrderDetailStatus(orderDetail);
                } else { // 不为空表示可能存在修改商品数量
                    Integer modifiedNum = Integer.valueOf(commodityNum);
                    if (modifiedNum < 0) {
                        return new Message(false, "商品数量不能小于0");
                    }
                    orderAuditing.setServiceModify(modifiedNum);
                    // 判断修改后的商品数量和原来商品数量是否相同
                    if (orderDetail.getCommodityNum() == modifiedNum) {
                        continue; // 相同则是没有任何操作
                    } else {
                        orderAuditing.setModifyStatus(1); // 不相同则是修改订单
                        // 修改订单明细商品数量
                        orderDetail.setCommodityNum(modifiedNum);
                        if (orderService.updateOrderDetailComNum(orderDetail) < 0) {
                            return new Message(false, "更新订单明细商品数量失败");
                        }
                    }
                }

                orderAuditing.setOrderDetailId(orderDetail.getOrderDetailId());
                orderAuditing.setOrderNumber(orderNumber);
                orderAuditing.setCommodityId(orderDetail.getCommodityId());
                orderAuditing.setCommodityName(orderDetail.getCommodityName());
                orderAuditing.setCommodityNo(commodityNo);
                orderAuditing.setCommodityNum(orderDetail.getCommodityNum());
                orderAuditing.setModifyTime(new Date());

                if (orderService.insertOrderAuditing(orderAuditing) < 0) {
                    modifyResult = false;
                    return new Message(false, "插入订单审核记录失败");
                }
            }
        }

        /** ****** 存在新增订单逻辑 ****** */

        // 如果存在新增订单
        if (addOrderTableSize > 0) {
            // 遍历新增订单
            for (int i = 0; i < addOrderTableObj.size(); i++) {

                // 创建订单审核对象
                OrderAuditing orderAuditing = new OrderAuditing();
                orderAuditing.setServiceRemark(serviceRemark); // 设置客服人员备注
                orderAuditing.setAuditingId(UuidUtil.get32UUID()); // 设置审核单id
                orderAuditing.setOrderNumber(orderNumber); // 订单编号
                orderAuditing.setOrderId(order.getOrderId());
                orderAuditing.setServiceId(serviceId);

                JSONObject jsonObj = addOrderTableObj.getJSONObject(String.valueOf(i));
                String commodityNo = (String) jsonObj.get("commodityNo");
                Integer commodityNum = Integer.valueOf((String) jsonObj.get("commodityNum"));

                Product product = productService.findProductInfoByNo(commodityNo);
                // 判断商品库存是否足够
                Integer kxdStock = product.getKxdStock();
                if (kxdStock <= 0 || kxdStock < commodityNum) {
                    return new Message(false, "商品库存不足");
                }
                // 增加客服库存,减少可下单库存
                product.setKfStock(product.getKfStock() + kxdStock);
                product.setKxdStock(product.getKxdStock() - commodityNum);
                productService.updateProductStock(product);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(UuidUtil.get32UUID());
                orderDetail.setOrderNumber(orderNumber);
                orderDetail.setCommodityId(product.getCommodityId());
                orderDetail.setCommodityNum(commodityNum);
                orderDetail.setCommodityName((String) jsonObj.get("commodityName"));
                orderDetail.setUnit((String) jsonObj.get("unit"));
                orderDetail.setSalePrice(Double.valueOf((String) jsonObj.get("salePrice")));
                orderDetail.setTotalMoney(Double.valueOf((String) jsonObj.get("totalMoney")));
                orderDetail.setTaxRate(product.getTaxRate());
                orderDetail.setImgMain(product.getImgMain());
                orderDetail.setCommodityNo(commodityNo);
                orderDetail.setInPrice(product.getInPrice());
                orderDetail.setAddTime(new Date());

                // 补全订单审核表参数
                orderAuditing.setOrderDetailId(orderDetail.getOrderDetailId());
                orderAuditing.setCommodityId(product.getCommodityId());
                orderAuditing.setCommodityName((String) jsonObj.get("commodityName"));
                orderAuditing.setCommodityNo((String) jsonObj.get("commodityNo"));
                orderAuditing.setCommodityNum(0); // 商品原数量为0
                orderAuditing.setServiceModify(commodityNum); // 新增商品的数量
                orderAuditing.setModifyStatus(0);
                orderAuditing.setModifyTime(new Date());

                // 将记录添加到数据库中
                if (orderService.insertOrderAuditing(orderAuditing) < 0
                        || orderService.insertOrderDetail(orderDetail) < 0) {
                    addResult = false;
                }
            }
        }

        int updateResult = 0;
        if (modifyResult && addResult) {
            // 设置订单状态
            order.setOrderStatus("7");
            // 更新订单
            updateResult = orderService.updateOrderStatus(order);
        } else {
            return new Message(false, "审核订单失败");
        }

        /** ****** 推送库存 ****** */
        if (updateResult > 0) {
            // 封装推送库存参数
            String orderId = order.getOrderId();
            try {
                // 审核完成后,减少商品客服库存,订单状态改为库存审核,推送到库存
                boolean b = pushStock(orderId);// 推送库存,和库存返回信息是否为空
                return b ? new Message("0000", "操作成功") : new Message(b, "推送库存失败");
            } catch (UnsupportedEncodingException e) {
                return new Message(false, "推送库存失败");
            }
        } else {
            return new Message(false, "更新订单信息失败");
        }
    }

    /**
     * 查询订单明细
     *
     * @param orderNumber
     * @param commodityNo
     * @return
     */
    private OrderDetail getOrderDetail(String orderNumber, String commodityNo) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderNumber", orderNumber);
        hashMap.put("commodityNo", commodityNo);
        List<OrderDetail> orderDetailInfos = orderService.findOrderDetail(hashMap);
        if (orderDetailInfos == null) {
            return new OrderDetail();
        }
        return orderDetailInfos.get(0);
    }

    /**
     * 推送给库存
     *
     * @param orderId
     * @return
     * @throws UnsupportedEncodingException
     */
    @Transactional // 事务管理
    public boolean pushStock(String orderId) throws UnsupportedEncodingException {
        logger.info("==========开始推送订单,订单orderId:{}", orderId);
        Order virtualOrder = orderService.findOrder(orderId); // 获取订单
        String userId = virtualOrder.getUserId(); // 获取到userId
        Store store = certificationService.getStoreByUserId(userId); // 查询商铺
        if (store == null) {
            store = certificationService.getVirtualStoreInfo(userId); // 查询虚拟订单
            if (store == null) {
                return false;
            }
        }
        JSONObject jsonObject = new JSONObject(); // 用于封装推送参数

        // 根据订单明细查询商品信息
        JSONArray jsonArray = new JSONArray();
        String orderNumber = virtualOrder.getOrderNumber();
        List<OrderDetail> orderDetailInfos = orderService.findOrderDetailInfoByNo(orderNumber);
        for (OrderDetail orderDetail : orderDetailInfos) {
            // 封装products
            JSONObject object = new JSONObject();
            object.put("barCode", orderDetail.getCommodityNo());
            object.put("number", orderDetail.getCommodityNum());
            object.put("salePrice", (int) Math.ceil(orderDetail.getSalePrice()));
            object.put("totalPrice", (int) Math.ceil(orderDetail.getTotalPrice() == null ? 0 : orderDetail.getTotalPrice()));

            // 查询商品
            Commodity commodity =
                    commodityService.queryByCommodityNo(orderDetail.getCommodityNo()).get(0);
            String subPrice = "";
            String subPriceUnit = "";
            if (commodity.getSubPrice() != null) {
                subPrice = commodity.getSubPrice();
            }
            if (commodity.getSubPriceUnit() != null) {
                subPriceUnit = commodity.getSubPriceUnit();
            }
            object.put("subPrice", subPrice);
            object.put("subPriceUnit", subPriceUnit);
            jsonArray.add(object);

            // 减少客服库存
            Product product = productService.findProductInfoByNo(orderDetail.getCommodityNo());
            product.setKfStock(product.getKfStock() - orderDetail.getCommodityNum());
            productService.updateProductStock(product);
        }

        // 封装商铺信息
        jsonObject.put("shopId", store.getStoreId());
        jsonObject.put("shopName", store.getStoreName());
        jsonObject.put("shopAddress", store.getAddress());
        jsonObject.put("shopPhone", store.getPhone());
        jsonObject.put("shopContacts", store.getUserName());
        String receiveTime = "";
        if (!StringUtil.nullOrBlank(store.getStartCollect())
                && !StringUtil.nullOrBlank(store.getEndCollect())) {
            receiveTime = store.getStartCollect() + "--" + store.getEndCollect();
        }
        jsonObject.put("cusreceiveTime", receiveTime);
        // 封装订单信息
        jsonObject.put("purchaser", virtualOrder.getReceiver());
        jsonObject.put("phone", virtualOrder.getReceivePhone());
        jsonObject.put("address", virtualOrder.getReceiveArea() + virtualOrder.getReceiveAdd());
        jsonObject.put("orderNumber", virtualOrder.getOrderNumber());
        jsonObject.put("orderPrice", (int) Math.ceil(virtualOrder.getOrderPrice()));
        int payWay = 2; // 线下
        if (virtualOrder.getPayCode() == null || virtualOrder.getPayCode().equalsIgnoreCase("HDFK")) { // 若为空表示货到付款,或默认为货到付款
            payWay = 2;
        } else {
            payWay = 1; // 线上
        }
        jsonObject.put("payWay", payWay);
        jsonObject.put("cusremark", virtualOrder.getRemark());
        // 添加产品信息
        jsonObject.put("products", jsonArray);

        // 封装参数
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("params", jsonObject);
        logger.info("==========推送订单到库存,参数为:{}", stringObjectHashMap);
        // 推送到库存
        String resultStr =
                HttpClientUtil.httpPostRequest(OrderConstant.STORAGE_URL, stringObjectHashMap);
        logger.info("==========接收库存返回参数:{}", resultStr);
        if (!StringUtil.nullOrBlank(resultStr)) {
            return pubStorage(virtualOrder, resultStr);
        }
        return false;
    }

    private boolean pubStorage(Order virtualOrder, String resultStr) {
        net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultStr);
        String code = jsonObject1.getString("code");
        String storageNo = "";
        if ("0000".equals(code)) {
            String data = jsonObject1.getString("data");
            net.sf.json.JSONObject jsonObject2 = net.sf.json.JSONObject.fromObject(data);
            storageNo = jsonObject2.getString("purchaserId");
            virtualOrder.setStorageNo(storageNo); // 设置订单库存号
            // order.setOrderStatus("2"); // 设置订单为待发货
            logger.info("==========订单storageNo:{}", storageNo);
            if (orderService.updateStorageAndStatus(virtualOrder) > 0) { // 更新订单信息
                logger.info("===============订单库存号修改成功");
                return true;
            }
        }
        logger.warn("===============订单order:{},库存号storageNo:{}修改失败", virtualOrder.getOrderId(), storageNo);
        return false;
    }

    /**
     * @param orderNumber @Description:整单取消
     * @return: org.framework.core.model.Message
     * @author: niu @Date: 2018/5/24
     */
    @RequestMapping(OrderConstant.ORDER_CANCELL_ALL_OPS)
    public @ResponseBody
    Message cancelAllOrder(
            String orderId, String orderNumber, String serviceId, String serviceRemark) {
        boolean resultFlag = true;
        // 查询所有的明细
        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", orderNumber);
        List<OrderDetail> orderDetailInfos = orderService.findOrderDetail(map);
        for (OrderDetail orderDetailInfo : orderDetailInfos) {
            // 补全订单审核表参数
            OrderAuditing orderAuditing = new OrderAuditing();
            orderAuditing.setAuditingId(UuidUtil.get32UUID());
            orderAuditing.setOrderId(orderId);
            orderAuditing.setOrderDetailId(orderDetailInfo.getOrderDetailId());
            orderAuditing.setOrderNumber(orderNumber);
            orderAuditing.setCommodityNo(orderDetailInfo.getCommodityNo());
            orderAuditing.setServiceId(serviceId);
            orderAuditing.setServiceRemark(serviceRemark);
            List<Commodity> commodities =
                    commodityService.queryByCommodityNo(orderDetailInfo.getCommodityNo());
            // 查询所有订单明细所对应的商品
            for (Commodity commodity : commodities) {
                orderAuditing.setCommodityId(commodity.getCommodityId());
                orderAuditing.setCommodityName(commodity.getCommodityName());
                orderAuditing.setModifyStatus(3);
                int result = orderService.insertOrderAuditing(orderAuditing);
                if (result <= 0) {
                    resultFlag = false;
                }
            }

            // 将订单明细的状态设置为取消
            orderDetailInfo.setStatus(-1);
            orderService.updateOrderDetailStatus(orderDetailInfo);
        }

        int updateResult = 0;
        if (resultFlag) {
            Order virtualOrder = new Order();
            virtualOrder.setOrderId(orderId);
            virtualOrder.setOrderNumber(orderNumber);
            virtualOrder.setServiceRemark(serviceRemark);
            virtualOrder.setOrderStatus("-1");
            updateResult = orderService.updateModifyOrder(virtualOrder);
        }

        return new Message(updateResult);
    }

    /**
     * @param commodityNo @Description:根据商品条形码查询商品
     * @return: com.xingrongjinfu.system.commodity.model.Commodity
     * @author: niu @Date: 2018/5/27
     */
    @RequestMapping(value = CommodityConstant.COMMODITY_ONE_NO)
    @ResponseBody
    public Message findCommodityByNo(String commodityNo) {
        List<Commodity> commodities = commodityService.queryByCommodityNo(commodityNo);
        if (commodities != null && commodities.size() > 0) {
            return new Message(true, commodities.get(0));
        }
        return new Message(false);
    }

    /**
     * @param commodityName @Description:根据商品名称模糊查询商品
     * @return: com.xingrongjinfu.system.commodity.model.Commodity
     * @author: niu @Date: 2018/5/27
     */
    @RequestMapping(value = CommodityConstant.COMMODITY_ALL_NAME)
    public @ResponseBody
    JSONArray findCommodityByName(String commodityName) {
        List<Commodity> commodities = commodityService.queryByCommodityName(commodityName);
        JSONArray jaProCommodity = JSONArray.fromObject(commodities);
        return jaProCommodity;
    }

    @RequestMapping(value = OrderConstant.ORDER_CONFIRMORDER)
    public @ResponseBody
    Message confirmOrder(String orderNumber) {
        if (StringUtil.isEmpty(orderNumber)) {
            return new Message(false, "无orderNumber参数");
        }
        logger.info("==========前端确认出库,参数:{}", orderNumber);
        Order order = orderService.findOrderInfo(orderNumber);
        if (order == null) {
            return new Message(false, "不存在该订单");
        }
        logger.info("==========更新orderNumber:{},确认出库", orderNumber);
        if (order.getConfirmOrder() > 0) {
            return new Message(false, "该订单已确认");
        }
        order.setConfirmOrder(1); // 设置用户已确认订单出库
        int result = 0;
        result = orderService.updateConfirmOrder(order);
        if (result > 0) {
            // 推送给库存开始发货
            return pushComfirmOrderStock(orderNumber);
        } else {
            logger.warn("===============订单orderNumber:{}更新失败", orderNumber);
            return new Message("1111", "更新用户确认收库状态失败");
        }
    }

    /**
     * 推送库存发货
     *
     * @param orderNumber
     */
    private Message pushComfirmOrderStock(String orderNumber) {
        final String url = OrderConstant.STORAGE_COMFIRMORDER_URL; // 配置库存地址
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("orderNo", orderNumber);
        Map map = new HashMap<>();
        map.put("params", jsonObject);
        // 推送给库存
        try {
            logger.info("==========推送库存请求参数:{}", map);
            String resultStr = HttpClientUtil.httpPostRequest(url, map);
            logger.info("==========接收库存返回参数:{}", resultStr);
            net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultStr);
            String code = jsonObject1.getString("code");
            if ("0000".equals(code)) {
                return new Message(true, "库存操作成功");
            } else {
                return new Message(false, "库存操作失败");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.warn("===============推送库存请求失败:{}", e);
            return new Message(false, "推送请求异常");
        }
    }


}
