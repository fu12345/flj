<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登陆</title>
</head>
<body>
${error }
${error1 }
<form action="${pageContext.request.contextPath }/loginUser.action" method="post">
用户名:<input type="text" name="username" value="${user.username }">

密码:<input type="password" name="password" value="${user.password }">

<input type="submit" value="提交">
</form>
</body>
</html>