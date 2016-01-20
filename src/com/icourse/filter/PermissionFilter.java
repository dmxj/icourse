package com.icourse.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icourse.service.ValidateUser;
import com.icourse.util.DateTool;


public class PermissionFilter implements Filter {

   
    public PermissionFilter() {
    }


	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = (HttpSession)req.getSession();
		System.out.println(">>>>servlet_path:"+req.getServletPath());
		
		String sp = req.getServletPath();
		
		if(sp.equals("/manager/verify_code") || sp.equals("/manager/login.jsp") || sp.equals("/manager/login")){			
			chain.doFilter(request, response);
		}else{		
			Long uid = (Long)session.getAttribute("uid");
			String uNum = (String)session.getAttribute("tid");
			if(uid == null || uNum == null || !ValidateUser.validateSession(uid, uNum)){
				session.setAttribute("msg", "请登录后再进行操作！");
				resp.sendRedirect("login.jsp");
				return;
			}
	
			Long startTime = (Long) session.getAttribute("entertime");
			if(startTime != null && DateTool.caclDiffMinutes(startTime, System.currentTimeMillis()) > 60){
				session.invalidate();
				req.getSession().setAttribute("msg", "登录超时，需要重新登录！");
				resp.sendRedirect("login.jsp");
				return ;
			}
			chain.doFilter(request, response);
			
		}
	}
	

	public void destroy() {
	}


}
