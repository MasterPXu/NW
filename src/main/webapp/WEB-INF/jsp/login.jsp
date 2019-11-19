<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录</title>
    <style >
    .error{color:red;}
    html{   
    width: 100%;   
    height: 100%;   
    overflow: hidden;   
    font-style: sans-serif;   
	}   
	body{   
 	    width: 100%;   
	    height: 100%;   
	    font-family: 'Open Sans',sans-serif;   
	    margin: 0;   
/* 	    background-color: #979797;  
	    background-image: url("${pageContext.request.contextPath}/static/timg.jpg");  */
	}   /**/
	#login{   
	    position: absolute;   
	    top: 50%;   
	    left:50%;   
	    margin: -150px 0 0 -150px;   
	    width: 300px;   
	    height: 300px;   
	}   
	#login h1{   
	    color: #fff;   
	    text-shadow:0 0 10px;   
	    letter-spacing: 1px;   
	    text-align: center;   
	}   
	h1{   
	    font-size: 2em;   
	    margin: 0.67em 0;   
	}
	.bbb{
		color: #fff; 
	    font-size: 0.35em;   
	    margin: 0.67em 0; 
	}   
	
	.aa{   
	    width: 308px;   
	    height: 25px;   
	    margin-bottom: 10px;   
	/*    outline: none;  
 	    padding: 10px;   
	    font-size: 13px;   
	    color: #fff;   
	    text-shadow:1px 1px 1px;   
	    border-top: 1px solid #312E3D;   
	    border-left: 1px solid #312E3D;   
	    border-right: 1px solid #312E3D;   
	    border-bottom: 1px solid #56536A;   
	    border-radius: 4px;   */ 
	    background-color: #FFFFFF;    
	}   
	.but{   
	    width: 308px;   
	    min-height: 20px;   
	    display: block;   
	    background-color: #4a77d4;   
	    border: 1px solid #3762bc;   
	    color: #fff;   
	    padding: 9px 14px;   
	    font-size: 15px;   
	    line-height: normal;   
	    border-radius: 5px;   
	    margin: 0;   
	}  
    </style>
</head>
<body background="${pageContext.request.contextPath}/static/images/a.png" style=" background-repeat:no-repeat ;

background-size:100% 100%; 

background-attachment: fixed;" >

<div class="error">${error}</div>
<div id="login">
 	<h1>公诚夜岗巡查系统</h1>  
	<form action="" method="post">
		  <input type="text" name="username" value="<shiro:principal/>"  placeholder="用户名" class="aa"></input><br/>
		  <input type="password" name="password" placeholder="密码" class="aa"></input><br/>
		  <input type='hidden' value='${CSRFToken}' id='csrftoken'>
		  <a class="bbb">自动登录:</a><input type="checkbox" name="rememberMe" value="true"><br/>
		  <input class="but" type="submit" value="登录">
	</form>
</div>
<!-- 	      <input class="but" type="submit" value="登录" οnclick="submitForm()" type="button">	  -->
<%-- 	    <img src="${pageContext.request.contextPath}/static/images/logo.png">
	    <img src="${pageContext.request.contextPath}/static/images/b.png">	  --%>   
</body>
</html>