package com.icourse.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icourse.bean.Teacher;
import com.icourse.service.UserApi;
import com.icourse.service.ValidateUser;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    
   
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action != null){
			if(action.equals("out")){
				doLogout(request,response);
				return ;
			}
		}
		
		response.sendRedirect("login.jsp");
//		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teachername = request.getParameter("loginname").trim();
		String teacherpwd = request.getParameter("loginpwd").trim();
		String checkcode = request.getParameter("logincheckcode").trim();
		System.out.println("=========teachername=========="+teachername);
		System.out.println("=========teacherpwd=========="+teacherpwd);
		HttpSession session = (HttpSession) request.getSession();
		
		String safecode = (String)session.getAttribute("safecode"); 
		
		if(teachername == null || teachername.equals("") || teacherpwd == null || teacherpwd.equals("")){
			session.setAttribute("msg", "请填写用户名和密码！");
			response.sendRedirect("login.jsp");
		}else if(!safecode.trim().equals(checkcode)){ //验证码错误
			session.setAttribute("msg", "验证码错误，请重填！");
			response.sendRedirect("login.jsp");
		}else if(!ValidateUser.checkUser(teachername, teacherpwd)){  //用户名或密码错
			session.setAttribute("msg", "用户名或密码填写错误！");
			response.sendRedirect("login.jsp");
		}else{  //用户名和密码正确
			Teacher teacher = UserApi.getUserByinfo(teachername, teacherpwd);
			session.setAttribute("uid", teacher.getUid());
			session.setAttribute("tid", teacher.getUsernum());
			session.setAttribute("entertime", System.currentTimeMillis());			
			response.sendRedirect("index.jsp");
		}
		
	}
	
	
	//退出登录
	protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

}
