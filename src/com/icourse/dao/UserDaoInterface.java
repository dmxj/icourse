package com.icourse.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icourse.bean.Teacher;

public interface UserDaoInterface {
	public ResultSet getUserByUser(Connection conn,Teacher teacher) throws SQLException;
	public ResultSet getUserByIdAndNum(Connection conn,Long uid,String uNum) throws SQLException;
}
