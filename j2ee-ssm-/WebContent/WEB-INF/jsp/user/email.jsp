<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱测试</title>
<script type="text/javascript">
/* function aabb(){
	  var value = document.getElementById("email").value;
	  if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)) {
		  alert("邮箱格式不正确");
	  }
	} */
	function send(){
		var email = document.getElementById("email").value;
		if(email==null || email==""){
			alert("邮箱不能为空")
		}
		else if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(email)) {
			  alert("邮箱格式不正确");
		  }else{
		document.form.action="${pageContext.request.contextPath}/send.action";
		document.form.submit();
	}
}
	function check(){

	 	document.form.action="${pageContext.request.contextPath}/checkEmail.action";
		document.form.submit(); 
	}
</script>
</head>
<body>
${success }
${error }
<form name="form" action="${pageContext.request.contextPath}/checkEmail.action" method="post">
	邮箱:<input type="text" id="email" name="email" value="${user.email }"> <input type="button" value="获取验证码" onclick="send()"><br>
	验证码：<input type="text" id="verifyCode" name="verifyCode">
	<input type="button" value="提交" onclick="check()">
	
</form>
</body>
</html>