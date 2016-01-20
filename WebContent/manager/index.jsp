<%@page import="com.icourse.bean.Lecture,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台主页</title>
<link rel="stylesheet" type="text/css" href="../asset/css/style.css" />
<link rel="stylesheet" type="text/css" href="../asset/css/bs.min.css" />
<link rel="stylesheet" type="text/css" href="../asset/css/jquery.datetimepicker.css" />
<script charset="utf-8" src="../asset/js/jquery.min.js"></script>
<script charset="utf-8" src="../asset/js/jquery.datetimepicker.js"></script>
<script type="text/javascript">
	$(function(){
		setTimeout(function(){
			$('#alert-box').slideUp();
		}, 3000);
	});
	
	function confirmDelete(linkuri){
		if(confirm("确定删除该课程吗？")){
			window.location.href=linkuri;
		}
	}
</script>
</head>
<body>
<header class="navbar navbar-light navbar-static-top bd-navbar" role="banner">
	<a class="navbar-brand" href="#" style="margin-left:20px;">欢迎来到后台主页</a>
	<ol class="breadcrumb">
	  <li class="active">首页</li>
	  <li class="pull-right"><a href="<%= request.getContextPath() %>/manager/login?action=out">退出登录</a></li>
	</ol>
</header>
	<jsp:useBean id="lectureApi" scope="page" class="com.icourse.service.LectureApi"></jsp:useBean>
<div id="container" class="container">
<div class="row">
	<div class="col-ld-3 col-md-push-9">
	</div>
	<div class="col-ld-9 col-md-pull-3 bd-example">
	<%if(session.getAttribute("msg") != null){ %>
	<div class="alert alert-success" id="alert-box" role="alert">
	  <strong>Well done!</strong> <%=session.getAttribute("msg") %>
	</div>
	<%} 
	session.removeAttribute("msg");
	%>
	<p><a type="button" id="addUnitBtn" class="btn btn-danger" href="<%=request.getContextPath() %>/manager/lectureServlet?action=create">添加课程</a></p>
	<table class="table">
	  <thead class="thead-inverse">
	    <tr>
	      <th>#</th>
	      <th>Weeks</th>
	      <th>Units</th>
	      <th>Demo Projects</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
		<% 
		ArrayList<Lecture> lectures = lectureApi.fetchAllLecture();
		for(int i=0;i<lectures.size();i++ ){ 
		Lecture lecture = lectures.get(i);
		%>
		<tr>
	      <th scope="row"><%= i+1%></th>
	      <td><%=lecture.getWeek() %></td>
	      <td><%=lecture.getUnit() %></td>
	      <td><%=lecture.getDemoProject() %></td>
	      <td>
	      	<a href="<%=request.getContextPath() %>/manager/lectureServlet?action=update&id=<%=lecture.getId()%>">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
	      	<a href="javascript:;" onclick="confirmDelete('<%=request.getContextPath() %>/manager/lectureServlet?action=remove&id=<%=lecture.getId()%>');">删除</a>
	      </td>
	    </tr>
		<%} %>
	</tbody>
	</table>
	
	<a id="addUnitBtn" class="btn btn-warning" href="<%=request.getContextPath() %>/manager/htmlServlet">生成静态HTML</a>
	<a class="btn btn-info" href="<%=request.getContextPath() %>/lecture.html" target="_blank">预览Html</a>
	</div>
</div>
</div>	
</body>
</html>