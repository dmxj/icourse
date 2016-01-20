package com.icourse.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icourse.bean.Lecture;
import com.icourse.util.DateTool;

public class LectureDao implements LectureDaoInterface {

	@Override
	public ArrayList<Lecture> findAll(Connection conn) throws SQLException {
		ArrayList<Lecture> list = new ArrayList<Lecture>();
		String fetchAllSql = "select * from lectures order by week";
		PreparedStatement ps = conn.prepareStatement(fetchAllSql);
		ResultSet results = ps.executeQuery();
		while(results.next()){
			Lecture lec = new Lecture();
			lec.setId(results.getLong("id"));
			lec.setUnit(results.getString("unit"));
			lec.setWeek(results.getString("week"));
			lec.setDemoProject(results.getString("demo_project"));
			lec.setCreateTime(DateTool.formatDate(results.getDate("create_time"), "yyyy-MM-dd HH:mm:ss"));
			list.add(lec);
		}
		return list;
	}

	@Override
	public ResultSet findOne(Connection conn, Long id) throws SQLException {
		String fetchOneSql = "select * from lectures where id = ?";
		PreparedStatement ps = conn.prepareStatement(fetchOneSql);
		ps.setLong(1, id);
		return ps.executeQuery();
	}

	@Override
	public void save(Connection conn, Lecture lecture) throws SQLException {
		String saveSql = "insert into lectures(unit,week,demo_project,create_time) values (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(saveSql);
		ps.setString(1, lecture.getUnit());
		ps.setString(2, lecture.getWeek());
		ps.setString(3, lecture.getDemoProject());
		ps.setDate(4, new Date(System.currentTimeMillis()));
		ps.execute();
	}

	@Override
	public void update(Connection conn, Lecture lecture, Long id) throws SQLException {
		String updateSql = "update lectures set unit = ?,week = ?,demo_project = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, lecture.getUnit());
		ps.setString(2, lecture.getWeek());
		ps.setString(3, lecture.getDemoProject());
		ps.setLong(4, id);
		ps.execute();
	}

	@Override
	public void delete(Connection conn, Long id) throws SQLException {
		String deleteSql = "delete from lectures where id = ?";
		PreparedStatement ps = conn.prepareStatement(deleteSql);
		ps.setLong(1, id);
		ps.execute();
	}

}
