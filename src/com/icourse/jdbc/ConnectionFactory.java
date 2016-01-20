package com.icourse.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class ConnectionFactory {

	private static String driver;
	private static String dburl;
	private static String dbuser;
	private static String dbpwd;
	
	private static ConnectionFactory _instance = new ConnectionFactory();
	
	static{
		Properties pro = new Properties();
		InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		try {
			pro.load(is);
		} catch (IOException e) {
			System.out.println("获取数据库配置文件失败！");
			e.printStackTrace();
		}
		
		driver = pro.getProperty("driver");
		dburl = pro.getProperty("dburl");
		dbuser = pro.getProperty("db_user");
		dbpwd = pro.getProperty("db_pwd");
		
	}
	
	private ConnectionFactory(){
		
	}
	
	public static ConnectionFactory getInstance(){
		if(_instance == null){
			_instance = new ConnectionFactory();
		}
		
		return _instance;
	}
	
	public Connection makeConnection() throws Exception{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dburl, dbuser, dbpwd);
		
		return conn;
	}
	
}
