package com.icourse.service;

import java.sql.Connection;

import com.icourse.bean.Teacher;
import com.icourse.dao.UserDao;
import com.icourse.jdbc.ConnectionFactory;

public class ValidateUser {
	public static boolean checkUser(String name_or_num,String pwd){
		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			
			UserDao userDao = new UserDao();
			Teacher teacher = new Teacher();
			teacher.setUsername(name_or_num);
			teacher.setUsernum(name_or_num);
			teacher.setUserpwd(EncryptPwd.encryptPwd(pwd));
			if(userDao.getUserByUser(conn, teacher).next()){
				return true;
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (Exception e3) {
			}
		}
		return false;
	}
	
	//session validate
	public static boolean validateSession(Long uid,String uNum)
	{
		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			
			UserDao userDao = new UserDao();
			if(userDao.getUserByIdAndNum(conn, uid, uNum).next()){
				return true;
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (Exception e3) {
			}
		}
		return false;
	}
}
