package com.xingrongjinfu.statistics.cashCount.controller;

import com.xingrongjinfu.statistics.cashCount.Service.AmontService;
import com.xingrongjinfu.statistics.cashCount.model.Amount;
import com.xingrongjinfu.system.setting.model.Setting;
import com.xingrongjinfu.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CashQuarzt {

    @Autowired
    private AmontService amontService;

    public void cashCountForDay() throws Exception {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        Date date = new Date();
        Date preDate = new Date(date.getTime() - 24*60*60*1000);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=sdf.format(date);
        String preDateStr=sdf.format(preDate);
          List<Amount> amountList= amontService.countCashFlowDay(preDateStr);
          if (amountList!=null &&amountList.size()>0){
              //查询xzfRate
              Double xzfRate =amontService.findXzfRate("1");
             // Double xzfRate=2D;
              for(Amount amount:amountList){
                  String amountId= UuidUtil.get32UUID();
                  String amountNum= UuidUtil.getNo("ST");
                  amount.setAmountId(amountId);
                  amount.setAmountNum(amountNum);
                  amount.setAddTime(new Date());
                  amount.setXzfRate(xzfRate);
                  amount.setAmountStatus(1);
                  amontService.addAmont(amount);
              }
          }

//        List<String[]> list=new ArrayList<String[]>();
//        try {
//            Connect conn=new Connect();
//            Connection con=conn.getConn();
//            list=conn.search(con, "select IFNULL(SUM(c.money),0) totalMoney,c.storeId storeId  from m_cashFlow  c " +
//                    " where date_format(c.addTime,'%Y-%m-%d')='"+preDateStr+"'" +
//                    " GROUP BY c.storeId ");
//            con.close();
//            if (list.size()>0){
//                for(int i=0;i<list.size();i++){
//                    Double totalPrice=Double.parseDouble(list.get(i)[0]);
//                    String storeId=list.get(i)[1];
//                    String amountId= UuidUtil.get32UUID();
//                    String amountNum=UuidUtil.getNo("ST");
//                    Double xzfRate=2D;
//                    Connect conn1=new Connect();
//                    Connection con1=conn1.getConn();
//                    String sql=" INSERT INTO m_amount (amountId,amountNum,storeId,addTime,totalMoney,xzfRate,amountStatus,status) " +
//                            " VALUES ('"+amountId+"', '"+amountNum+"', '"+storeId+"', '"+dateStr+"', "+totalPrice+", "+xzfRate+", '1', '1') ";
//                    conn.update(con1,sql);
//                    con.close();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    public static void main(String[] args) {
        Date date = new Date();
        Date preDate = new Date(date.getTime() - 24*60*60*1000);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=sdf.format(date);
        String preDateStr=sdf.format(preDate);
        System.out.println(preDateStr);
    }
}
