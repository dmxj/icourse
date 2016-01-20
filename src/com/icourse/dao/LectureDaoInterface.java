package com.icourse.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icourse.bean.Lecture;

public interface LectureDaoInterface {
	public ArrayList<Lecture> findAll(Connection conn) throws SQLException;
	public ResultSet findOne(Connection conn,Long id) throws SQLException;
	public void save(Connection conn,Lecture lecture) throws SQLException;
	public void update(Connection conn,Lecture lecture,Long id) throws SQLException;
	public void delete(Connection conn,Long id) throws SQLException;
}
