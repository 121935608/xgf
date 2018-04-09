package com.xingrongjinfu.commodity.toStock.service;

import java.util.List;
import java.util.Map;

import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.commodity.register.model.Register;
import com.xingrongjinfu.commodity.toStock.dao.IToStockDao;
import com.xingrongjinfu.statistics.purchase.model.Purchase;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ToStockService implements IToStockService {
    @Autowired
    private IToStockDao toStockDao;

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return toStockDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public Purchase getCommodityById(Map map) {
        return toStockDao.getCommodityById(map);
    }

    @Override
    public Register loadCommodity(Map map) {
        return toStockDao.loadCommodity(map);
    }

    @Override
    public List<String> getAllCommodity(String storeId) {
        return toStockDao.getAllCommodity(storeId);
    }

    @Override
    public int getCommodityByName(Map map) {
        return toStockDao.getCommodityByName(map);
    }

    @Override
    public Message updateCommodity(Map map,String orders) {
        JSONArray ja=JSONArray.fromObject(orders);
        for(Object o:ja){
            JSONObject jo=JSONObject.fromObject(o);
            int index=jo.getInt("index");
            //验证名称和条码
            map.put("commodityName", jo.getString("commodityName"+index));
            map.put("commodityNo", jo.getString("commodityNo"+index));
            map.put("stockNum", jo.getString("stockNum"+index));
            int n = toStockDao.updateCommodity(map);
            if(n == 0){
                return new Message(0);
            }
            //入库  更改商户端商品入库状态
            toStockDao.updateCommodityStatus(map);
        }
        return new Message(1);
    }
}
