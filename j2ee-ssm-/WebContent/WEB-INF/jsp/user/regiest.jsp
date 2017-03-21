<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript">
/* 在form表单提交时验证  不正确的话表单提交不了 */
function checkForm(){
	var username = document.getElementById("username").value;
	if(username==null||username==''){
		alert("用户名不能为空");
		return false;
	}

	var email = document.getElementById("email").value;
	 if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(email)) {
		  alert("邮箱格式不正确");
		  return false;
	  }
}
/*校验邮箱格式  不正确也可以提交 */
function checkEmail(){
	//获取email值
	var email = document.getElementById("email").value;
	//创建异步对象
	var xhp = createXmlHttp();
	//设置监听
	xhp.onreadystatechange=function(){
		if(xhp.readyState == 4){
			if(xhp.status == 200){
				document.getElementById("span2").innerHTML = xhp.responseText;
			}
		}
	}
	//打开连接
	xhp.open("Get","${pageContext.request.contextPath}/checkEmail1.action?email="+email,true);
	//发送
	xhp.send(null);
}
/*校验用户名  */
function checkUsername(){
	//1.获取文本框值
	var username = document.getElementById("username").value;
	/* alert(username); */
	//2.创建异步对象
	var xhr = createXmlHttp();
	//3.设置监听
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				document.getElementById("span1").innerHTML = xhr.responseText;
			}
		}
	}
	//4.打开链接
	xhr.open("Get","${pageContext.request.contextPath}/findUsername.action?username="+username,true);
	//5.发送
	xhr.send(null);
}


function createXmlHttp(){
	   var xmlHttp;
	   try{ // Firefox, Opera 8.0+, Safari
	        xmlHttp=new XMLHttpRequest();
	    }
	    catch (e){
		   try{// Internet Explorer
		         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		      }
		    catch (e){
		      try{
		         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		      }
		      catch (e){}
		      }
	    }

		return xmlHttp;
	 }

function change(){
	var img1 = document.getElementById("checkImg");
	img1.src="${pageContext.request.contextPath}/doGet.action?"+new Date().getTime();
}

</script>
</head>
<body>
${error }
<form action="${pageContext.request.contextPath }/insertUser.action" method="post" onsubmit="return checkForm()">
	用户名:<input type="text" name="username" id="username" value="${user.username }" onblur="checkUsername()">
	<span id="span1"></span><br>
	邮箱:<input type="text" name="email" id="email" value="${user.email}" onblur="checkEmail()">
	<span id="span2"></span><br>
	密码:<input type="password" id="password" name="password" value="${user.password }"><br>
	验证码:<input type="text" name="verifyCode" value="${user.verifyCode}"><br>
	<img alt="" id="checkImg" src="${pageContext.request.contextPath }/doGet.action" onclick="change()" title="点击更换验证码">
	       <input type="submit" value="提交">
</form>
</body>
</html> 