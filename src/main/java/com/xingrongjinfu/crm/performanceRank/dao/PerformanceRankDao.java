package com.xingrongjinfu.crm.performanceRank.dao;


import com.xingrongjinfu.crm.department.model.Dept;
import com.xingrongjinfu.utils.DateUtils;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author hyq
 * @version V1.0
 * @Description: 业绩排名 数据层处理
 */
@Repository("performanceRankDao")
public class PerformanceRankDao extends DynamicObjectBaseDao implements IPerformanceRankDao {
    @Override
    public List<TableDataInfo> pageInfoQueryBydept(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> rankPageInfo = null;
        try
        {

            rankPageInfo = (List<TableDataInfo>) this.findForList("PerformanceRankMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rankPageInfo;
    }

    @Override
    public HashMap getPerformanceDetails(HashMap param) {
        HashMap result=new HashMap();
//        HashMap map = new HashMap();
        HashMap map1,map2,map3 = new HashMap();
        HashMap hashMap = new HashMap();
        hashMap.put("supervisorId",param.get("supervisorId").toString());
        try
        {
//            map=(HashMap) this.findForObject("PerformanceRankMapper.performanceDetails",param);

//            String queryType=param.get("queryType").toString();
            //类型为日业绩时参数为    今天开始时间、结束时间，昨天开始时间、结束时间
                hashMap.put("timeBegin", DateUtils.firstMomentOfDay(new Date()));
                hashMap.put("timeEnd", DateUtils.lastMomentOfDay(new Date()));
                hashMap.put("timeBegin1", DateUtils.getStarTimeYesterday(new Date()));
                hashMap.put("timeEnd1", DateUtils.getEndTimeYesterday(new Date()));
                map1=(HashMap) this.findForObject("PerformanceRankMapper.rankingInfo",hashMap);

            //类型为周业绩时参数为  本周开始时间、结束时间，上周开始时间、结束时间
                hashMap.put("timeBegin", DateUtils.getFirstDayOfCurrentWeek(new Date()));
                hashMap.put("timeEnd", DateUtils.getLastDayOfCurrentWeek(new Date()));
                hashMap.put("timeBegin1", DateUtils.getFirstDayOfLastWeek(new Date()));
                hashMap.put("timeEnd1", DateUtils.getLastDayOfLastWeek(new Date()));
                map2=(HashMap) this.findForObject("PerformanceRankMapper.rankingInfo",hashMap);

            //类型为月业绩时参数为  本月开始时间、结束时间，上月开始时间、结束时间
                hashMap.put("timeBegin", DateUtils.getTimesMonthmorning(new Date()));
                hashMap.put("timeEnd", DateUtils.getTimesMonthnight(new Date()));
                hashMap.put("timeBegin1", DateUtils.getFirstDayOfLastMonth(new Date()));
                hashMap.put("timeEnd1", DateUtils.getLastDayOfLastMonth(new Date()));
                map3=(HashMap) this.findForObject("PerformanceRankMapper.rankingInfo",hashMap);

            result.put("map",param);
            result.put("map1",map1);
            result.put("map2",map2);
            result.put("map3",map3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Dept> getDept() throws Exception {
        List<Dept> list=new ArrayList();
        list=(List<Dept>) this.findForList("PerformanceRankMapper.getDept");
        return list;
    }
}
