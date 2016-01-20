package com.icourse.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.icourse.bean.Lecture;
import com.icourse.dao.LectureDao;
import com.icourse.jdbc.ConnectionFactory;
import com.icourse.util.DateTool;

public class LectureApi {
	/**
	 * 获取所有的课程数据
	 * @return
	 */
	public static ArrayList<Lecture> fetchAllLecture(){
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
		Connection conn = null;
		LectureDao lectureDao = new LectureDao();
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			lectureList = lectureDao.findAll(conn);			
			conn.commit();
			return lectureList;
		} catch (Exception e) {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e2) {
			}
			return null;
		}
	}
	
	/**
	 * 根据ID获取课程数据
	 * @param id
	 * @return
	 */
	public static Lecture getLectureById(Long id){
		Connection conn = null;
		LectureDao lectureDao = new LectureDao();
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			Lecture lecture = new Lecture();
			ResultSet rs = lectureDao.findOne(conn, id);
			if (rs.next()) {
				lecture.setId(rs.getLong("id"));
				lecture.setUnit(rs.getString("unit"));
				lecture.setWeek(rs.getString("week"));
				lecture.setDemoProject(rs.getString("demo_project"));
				lecture.setCreateTime(DateTool.formatDate(rs.getDate("create_time"), "yyyy-MM-dd HH:mm:ss"));
			}else{
				return null;
			}
			conn.commit();
			return lecture;
		} catch (Exception e) {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e2) {
			}
			return null;
		}
	}
	
	/**
	 * 保存课程数据
	 * @param lecture
	 * @return
	 */
	public static boolean saveLecture(Lecture lecture){
		Connection conn = null;
		LectureDao lectureDao = new LectureDao();
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			lectureDao.save(conn, lecture);
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 更新课程信息
	 * @param lecture
	 * @param id
	 * @return
	 */
	public static boolean updateLecture(Lecture lecture,Long id){
		Connection conn = null;
		LectureDao lectureDao = new LectureDao();
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			lectureDao.update(conn, lecture, id);
			conn.commit();
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return false;
	}
	
	/**
	 * 根据课程id删除课程
	 * @param id
	 * @return
	 */
	public static boolean deleteLecture(Long id){
		Connection conn = null;
		LectureDao lectureDao = new LectureDao();
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			lectureDao.delete(conn, id);
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
}
