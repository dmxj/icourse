package com.icourse.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";   
    
    public HtmlServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ServletContext sc = getServletContext();
		  String url = "/tpl/lecture_tpl.jsp"; 
		  String filePath = this.getServletConfig().getServletContext().getRealPath("/");
		  System.out.println("===============>>>>>filePath:"+filePath);
		  String name = "lecture.html";  //这是生成的html文件名
		    
		  String pName = filePath+"\\"+name;  //生成html的完整路径
		  RequestDispatcher rd = sc.getRequestDispatcher(url);
		  final ByteArrayOutputStream os = new ByteArrayOutputStream();
		  final ServletOutputStream stream = new ServletOutputStream() {
			   public void write(byte[] data, int offset, int length) {
			    os.write(data, offset, length);
			   }
			   public void write(int b) throws IOException {
			    os.write(b);
			   }
				@Override
				public boolean isReady() {
					return true;
				}
				@Override
				public void setWriteListener(WriteListener arg0) {
					
				}
		  };
		  final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
		  HttpServletResponse rep = new HttpServletResponseWrapper(response) {
		   public ServletOutputStream getOutputStream() {
		    return stream;
		   }
		   public PrintWriter getWriter() {
		    return pw;
		   }
		  };
		  rd.include(request, rep);
		  pw.flush();
		  FileOutputStream fos = new FileOutputStream(pName); // 把jsp输出的内容写到指定路径的htm文件中
		  os.writeTo(fos);
		  fos.close();
		  //response.sendRedirect(request.getContextPath()+"/"+name); // 书写完毕后转向htm页面
		  request.getSession().setAttribute("msg", "生成静态HTML页面成功！");
		  response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
