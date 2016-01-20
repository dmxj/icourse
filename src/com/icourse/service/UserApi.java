package com.icourse.service;

import java.sql.Connection;
import java.sql.ResultSet;

import com.icourse.bean.Teacher;
import com.icourse.dao.UserDao;
import com.icourse.jdbc.ConnectionFactory;
import com.icourse.util.DateTool;

public class UserApi {

	public static Teacher getUserByinfo(String name_or_num,String pwd){
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			
			UserDao userDao = new UserDao();
			Teacher teacher = new Teacher();
			teacher.setUsername(name_or_num);
			teacher.setUsernum(name_or_num);
			teacher.setUserpwd(EncryptPwd.encryptPwd(pwd));
			
			rs = userDao.getUserByUser(conn, teacher);
			Teacher laoshi = new Teacher();
			while(rs.next()){
				laoshi.setUid(rs.getLong("id"));
				laoshi.setUsername(rs.getString("teacher_name"));
				laoshi.setUsernum(rs.getString("teacher_num"));
				laoshi.setLastLoginTime(DateTool.formatDate(rs.getDate("last_login_time"), "yyyy-MM-dd HH:mm:ss"));
			}
			
			conn.commit();
			return laoshi;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				rs.close();
			} catch (Exception e3) {
			}
		}
		return null;
	}
	
}
