<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算机网络课程列表</title>
<link rel="stylesheet" type="text/css" href="./asset/css/style.css" />
<link rel="stylesheet" type="text/css" href="./asset/css/bs.min.css" />
<style type="text/css">
body{
background-color: #EEE5D0!important;
}
</style>
<script charset="utf-8" src="./asset/js/jquery.min.js"></script>
</head>
<body>
<div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.icourse.bean.Lecture,java.util.ArrayList"%>
</div>
<jsp:useBean id="lectureApi" scope="page" class="com.icourse.service.LectureApi"></jsp:useBean>
<div id="content-body" class="container col-ld-12">
<div class="jumbotron" id="content-header-box">
  <h3 class="display-1">Network Programming Fall, 2015</h3>
  <p class="lead">Advanced Windows Network Programming.</p>
</div>
<nav class="navbar navbar-light bg-faded">
  <ul class="nav navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">HOME <span class="sr-only">(current)</span></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">WHAT'S NEW</a>
    </li>
    <li class="nav-item  active">
      <a class="nav-link" href="#">LECTURE</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">ASSIGNMENT</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">RESOURCE</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">SAMPLE CODE</a>
    </li>
  </ul>
</nav>
<div class="container">
<div class="row">
	<div class="col-ld-9 bd-example">
	<h3>Lecture slides and demo projects </h3>
	<table class="table">
	  <thead class="thead-default">
	    <tr>
	      <th>#</th>
	      <th>Weeks</th>
	      <th>Units</th>
	      <th>Demo Projects</th>
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
	    </tr>
		<%} %>
	</tbody>
	</table>
	<div class="top"><a href="#top">Top</a></div>
	</div>
	
	<div id="footer">
		<div class="column1" style="padding-top: 10px ">
			last updated
		    <script language="JavaScript" type="text/javascript">
			document.writeln(document.lastModified);
			</script>		
		  	by Prof. Lin Weiguo<br>
			© Copyright 2009-2015. College of Computing, Communication University of China, all rights reserved
		</div>
	</div>
	
</div>
</div>	
</div>
</body>
</html>