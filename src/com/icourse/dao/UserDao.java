package com.icourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icourse.bean.Teacher;

public class UserDao implements UserDaoInterface {

	@Override
	public ResultSet getUserByUser(Connection conn, Teacher teacher) throws SQLException {
		
		String fetchSql  = "select * from teachers where teacher_name = ? or teacher_num = ? and teacher_pwd = ?";
		PreparedStatement ps = conn.prepareStatement(fetchSql);
		ps.setString(1, teacher.getUsername());
		ps.setString(2, teacher.getUsernum());
		ps.setString(3, teacher.getUserpwd());
		
		return ps.executeQuery();
	}

	@Override
	public ResultSet getUserByIdAndNum(Connection conn, Long uid, String uNum) throws SQLException {
		String fetchSql  = "select * from teachers where id = ? and teacher_num = ?";
		PreparedStatement ps = conn.prepareStatement(fetchSql);
		ps.setLong(1, uid);
		ps.setString(2, uNum);
		
		return ps.executeQuery();
	}

}
