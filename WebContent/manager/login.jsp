<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>课程管理后台登录</title>
<link rel="stylesheet" type="text/css" href="../asset/css/style.css" />
<link rel="stylesheet" type="text/css" href="../asset/css/bs.min.css" />
<script charset="utf-8" src="../asset/js/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		$('#submitBtn').on('click',function(){
			var form = $("form[name='loginForm']");
			var name = form.find("input[name='loginname']");
			var pwd = form.find("input[name='loginpwd']");
			var code = form.find("input[name='logincheckcode']");
			
			if(name.val().trim() == ""){
				alert("用户名不能为空");
				name.focus();
			}else if(pwd.val().trim() == ""){
				alert("密码不能为空");
				pwd.focus();
			}else if(code.val().trim() == ""){
				alert("验证码不能为空");
				code.focus();
			}else{
				form.submit();
				$(this).attr('disabled',true);
			}
		});	
		
		$("input[name='loginname'],input[name='loginpwd']").on('change',function(){
			$('#tip').remove();
		});
		
	});

	function changeCode(){
		$('img#verify_code').attr("src",$('img#verify_code').attr('src')+"?a=b");
	}
	
</script>
</head>
<body>
	<header id="header" class="hf"><h3>课程后台管理系统</h3></header>
	<div id="container" class="container">
		<div class="row bd-example">
		<%if(session.getAttribute("msg") != null){ %>
		<div class="alert alert-success" id="tip" role="alert">
		  <strong>Well done!</strong> <%=session.getAttribute("msg") %>
		</div>
		<%} 
		session.removeAttribute("msg");
		%>
		<form action="<%=request.getContextPath() %>/manager/login" method="post" name="loginForm">
			<fieldset class="form-group">
				<label for="loginname">*用户名：</label> 
				<input type="text" name="loginname" class="form-control" />
			</fieldset>
			<fieldset class="form-group">
				<label for="loginpwd">*密   码：</label> 
				<input type="text" name="loginpwd" class="form-control" />
			</fieldset>
			<fieldset class="form-group">
				<label for="logincheckcode">*验证码：</label> 
				<input type="text" name="logincheckcode" class="form-control" />
				<img id="verify_code" src="<%=request.getContextPath() %>/manager/verify_code" onclick="changeCode();" /><a href="javascript:;" onclick="changeCode();">看不清，换一个</a>
			</fieldset>
			<fieldset class="form-group">
				<input type="button" value="登录" id="submitBtn" class="btn btn-success"  />
				<input type="reset" value="重置" class="btn btn-info" />
			</fieldset>
		</form>
		</div>
	</div>	
	<footer id="footer" class="hf">
		<p>@中国传媒大学-计算机网络与程序设计-课程后台系统</p>
	</footer>
</body>
</html>