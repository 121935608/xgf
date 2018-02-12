package com.xingrongjinfu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Connect {
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		PropertyUtils pu=new PropertyUtils("/resources.properties");
		String url=pu.getValue("connection.url");
		String username=pu.getValue("connection.username");
		String password=pu.getValue("connection.password");
		return getMySqlConnect(url, username, password);

	}


	public static Connection getMySqlConnect(String url,String username,String password) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver".toString());
		Connection conn = DriverManager.getConnection(url,username,password);
		conn.setAutoCommit(false);
		return conn;
	}

	public static List<String[]> search(Connection conn,String sql) throws SQLException{
		List<String[]> list=new ArrayList<String[]>();
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		try {
			rs=conn.createStatement().executeQuery(sql);
			rsmd = rs.getMetaData();
			int count=rsmd.getColumnCount();
			String[] columns=new String[count];
			for(int i=0;i<count;i++){
				columns[i]=rsmd.getColumnName(i+1);
			}
			while(rs.next()){
				String[] cs=new String[count];
				for(int i=0;i<count;i++){
					cs[i]=rs.getString(columns[i]);
				}
				list.add(cs);
			}
			rs.close();
		} catch (Exception e) {
			conn.rollback();
		}
		return list;
	}
	public List<String[]> search1(Connection conn,String sql) throws SQLException{
		List<String[]> list=new ArrayList<String[]>();
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		try {
			rs=conn.createStatement().executeQuery(sql);
			rsmd = rs.getMetaData();
			int count=rsmd.getColumnCount();
			String[] columns=new String[count];
			for(int i=0;i<count;i++){
				columns[i]=rsmd.getColumnName(i+1);
			}
			list.add(columns);
			while(rs.next()){
				String[] cs=new String[count];
				for(int i=0;i<count;i++){
					cs[i]=rs.getString(columns[i]);
				}
				list.add(cs);
			}
		} catch (Exception e) {
			conn.rollback();
		}
		return list;
	}

	public boolean zengshangai(Connection conn,String sql) throws SQLException {
		boolean result=false;
		int res=0;
		try {
			Statement stmt =conn.createStatement();
			res=stmt.executeUpdate(sql);
            conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
			conn.rollback();
		}
		return result;
	}

	
	public int update(Connection conn,String sql) throws SQLException {
		//boolean result=false;
		int res=0;
		try {
			Statement stmt =conn.createStatement();
			res=stmt.executeUpdate(sql);
            conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
		return res;
	}

	
	
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		PropertyUtils pu=new PropertyUtils("/resources.properties");
		String url=pu.getValue("connection.url");
		String username=pu.getValue("connection.username");
		String password=pu.getValue("connection.password");

		Class.forName("com.mysql.jdbc.Driver".toString());
		Connection conn = DriverManager.getConnection(url,username,password);
		conn.setAutoCommit(false);
		List<String[]> list=new ArrayList<String[]>();
		try {
			list = search(conn,"select * from m_cashFlow ");
			System.out.println(list.size());
			for(String[] s:list){
				for(String ss:s){
					//System.out.print(ss+" ");
				}
				//System.out.println("");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
