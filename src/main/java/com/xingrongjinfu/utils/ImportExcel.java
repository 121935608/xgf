package com.xingrongjinfu.utils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 *@Author cj
 *@Date 2018年1月12日 下午2:56:45
 *@Version 1.0
 */
public class ImportExcel<T> {
	
	public  List<T> readExcel(InputStream input,String[] fields,String className){
	 
		List<T> list = new ArrayList<T>();
		try{
		    //InputStream input = new FileInputStream(path);
		    Workbook rwb  = Workbook.getWorkbook(input);
		    //得到第一张表
		    Sheet sheet = rwb.getSheet(0);
		    //得到总行
		    int rowsCount = sheet.getRows();
		    int cellsCount = sheet.getColumns();
		    //得到列
		    Cell cell;
		    
		    //匹配方法名获取所有要调用的method
		    List<Method> methods=new ArrayList<Method>();
		    for(int i=0;i<fields.length;i++){
		    	T t = (T)Class.forName(className).newInstance();
		    	String methodName="set"+fields[i].substring(0,1).toUpperCase()+fields[i].substring(1);
		    	Method[] met = t.getClass().getDeclaredMethods();
		    	for(int m=0;m<met.length;m++){
		    		if(met[m].getName().equals(methodName)){
		    			methods.add(met[m]);
		    		}      
		    	}
		    } 
		    //从第二行开始读取数据
		   l: for(int rowNum=1;rowNum<rowsCount;rowNum++){
		    	T t = (T)Class.forName(className).newInstance(); 
		    	//遍历列数 
		    	for(int cellNum=0;cellNum<cellsCount;cellNum++){
		    		Method method=methods.get(cellNum); 
		    		cell= ((Sheet)sheet).getCell(cellNum, rowNum);
		    		Class paramClass= methods.get(cellNum).getParameterTypes()[0];
		    		String str = cell.getContents(); 
		    		if(cellNum == 0 && StringUtil.nullOrBlank(str))continue l;
		    		if(cell.getType()==CellType.LABEL || cell.getType()==CellType.NUMBER){
		    			if(paramClass.equals(Integer.class)){
		    				if("".equals(str))str="0";
		    				method.invoke(t, new Object[]{Integer.valueOf(str)});
		    			}else if(paramClass.equals(Double.class)){
		    				if("".equals(str))str="0";
		    				method.invoke(t, new Object[]{Double.valueOf(str)});
		    			}else if(paramClass.equals(Float.class)){
		    				if("".equals(str))str="0";
		    				method.invoke(t, new Object[]{Float.valueOf(str)});
		    			}else if(paramClass.equals(Date.class)){
		    				method.invoke(t, new Object[]{toDate(str)});
		    			}else{
		    				method.invoke(t, new Object[]{str});
		    			}
		    			 
		    		}else if(cell.getType()==CellType.DATE){
		    			DateCell dateCell = (DateCell)cell;
		    			java.util.Date date = dateCell.getDate();
		    			//java.sql.Date date = new java.sql.Date(xlsdate.getTime()); 
		    			if(paramClass.equals(java.util.Date.class)){
		    				method.invoke(t, new Object[]{date});
		    			}else{
		    				String dateStr=new SimpleDateFormat("yyyy-MM-dd").format(date);
		    				method.invoke(t, new Object[]{dateStr});
		    			}
						
		    		}
		    	}
		    	list.add(t);
		    	
		    }
		    rwb.close();
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public Date toDate(String s){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			return  sdf.parse(s);
		} catch (ParseException e) { 
			e.printStackTrace();
			return  null;
		}
	}
	

}

