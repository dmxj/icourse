package com.icourse.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icourse.bean.Lecture;
import com.icourse.service.LectureApi;

public class LectureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LectureServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if(action == null && id == null){
			response.sendRedirect("index.jsp");
			return;
		}else if(action != null){
			if(action.equals("create")){
				doGotoCreatePage(request, response);
			}else if(action.equals("update")){
				doGotoUpdatePage(request, response, Long.parseLong(id));
			}else if(action.equals("remove")){
				doRemove(request, response, Long.parseLong(id));
			}
		}else{
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String week = request.getParameter("week").trim();
		String unit = request.getParameter("unit").trim();
		String demo = request.getParameter("demo").trim();
		
		Long lectureId = request.getParameter("lectureid") == null ? 0 :Long.parseLong(request.getParameter("lectureid"));
		HttpSession session = request.getSession();
		
		String redirectUrl = "update.jsp";
		if(lectureId == null || lectureId == 0){ //添加课程
			redirectUrl = "create.jsp";
		}
		
		if(week == null || week.equals("")){
			session.setAttribute("msg", "请选择上课日期");
		}else if(unit == null || unit.equals("")){
			session.setAttribute("msg", "请填写课程名称");
		}else if(demo == null || demo.equals("")){
			session.setAttribute("msg", "请填写demo Prijects");
		}else{
			Lecture lecture = new Lecture();
			lecture.setWeek(week);
			lecture.setUnit(unit);
			lecture.setDemoProject(demo);
			lecture.setCreateTime("");
			if(lectureId == null || lectureId == 0){ //添加课程
				if(LectureApi.saveLecture(lecture)){
					session.setAttribute("msg", "添加课程成功！");
				}else{
					session.setAttribute("msg", "添加课程失败！");
				}
			}else{  //更新课程
				if(LectureApi.updateLecture(lecture, lectureId)){
					session.setAttribute("msg", "更新课程成功！");
				}else{
					session.setAttribute("msg", "更新课程失败！");
				}
			}
		}
		
		response.sendRedirect("index.jsp");
	}
	
	//删除课程
	protected void doRemove(HttpServletRequest request, HttpServletResponse response,Long id) throws ServletException, IOException {
		if(LectureApi.deleteLecture(id)){
			request.getSession().setAttribute("msg", "删除课程成功！");
		}else{
			request.getSession().setAttribute("msg", "删除课程失败！");
		}

		response.sendRedirect("index.jsp");
	}
	
	//显示修改页
	protected void doGotoUpdatePage(HttpServletRequest request, HttpServletResponse response,Long id) throws ServletException, IOException {
		request.getSession().setAttribute("lecid", id);
		response.sendRedirect("update.jsp");
	}
	
	//显示修改页
	protected void doGotoCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("create.jsp");
	}

}
