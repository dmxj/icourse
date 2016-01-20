<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.icourse.bean.Lecture" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加课程</title>
<link rel="stylesheet" type="text/css" href="../asset/css/style.css" />
<link rel="stylesheet" type="text/css" href="../asset/css/bs.min.css" />
<link rel="stylesheet" type="text/css" href="../asset/css/jquery.datetimepicker.css" />
<script charset="utf-8" src="../asset/js/jquery.min.js"></script>
<script charset="utf-8" src="../asset/js/jquery.datetimepicker.js"></script>
<script type="text/javascript">
$(function(){
	
	$('.date-time-picker').datetimepicker({
        lang:'zh',
        timepicker:false,
        format:'Y-m-d',
        formatDate:'Y年m月d日',
    });
	
	setTimeout(function(){
		$('#alert-box').slideUp();
	}, 3000);
	
	$('#submitBtn').on('click',function(){
		if($('#week').val() == ""){
			alert("请选择上课日期!");
			return false;
		}else if($('#unit').val() == ""){
			alert("请填写课程名称!");
			return false;
		}else if($('#demo').val() == ""){
			alert("请填写demo Prijects!");
			return false;
		}else{
			$("form[name='updateForm']").submit();
			$(this).attr('disabled','true');
		}
	});
});
</script>
</head>
<body>
<header class="navbar navbar-light navbar-static-top bd-navbar" role="banner">
	<ol class="breadcrumb">
	  <li><a href="<%= request.getContextPath() %>/manager/index.jsp">首页</a></li>
	  <li class="active">添加课程</li>
	</ol>
</header>
<div id="container" class="container">
<div class="row bd-example">
<%if(session.getAttribute("msg") != null){ %>
<div class="alert alert-success" id="alert-box" role="alert">
  <strong>Well done!</strong> <%=session.getAttribute("msg") %>
</div>
<%} 
session.removeAttribute("msg");
%>
<form action="<%=request.getContextPath() %>/manager/lectureServlet" method="post" name="updateForm">
  <fieldset class="form-group">
    <label for="exampleInputEmail1">Week:</label>
    <input type="text" class="form-control date-time-picker" name="week" id="week" placeholder="choose week" />
    <small class="text-muted">选择上课时间</small>
  </fieldset>
  <fieldset class="form-group">
    <label for="exampleInputEmail1">Unit:</label>
    <input type="text" class="form-control" name="unit" id="unit" placeholder="填写章节名称" />
  </fieldset>
  <fieldset class="form-group">
    <label for="exampleInputEmail1">Demo Projects:</label>
    <input type="text" class="form-control" name="demo" id="demo" placeholder="填写示例工程链接" />
  </fieldset>
  <fieldset class="form-group">
	<input type="button" value="添加课程" id="submitBtn" class="btn btn-success"  />
  </fieldset>
</form>
</div>
</div>		
</body>
</html>