<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>GC夜巡平台</title>
    <meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout-default-latest.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
</head>
<style>
	html{background-color:#E3E3E3; font-size:14px; color:#000; font-family:'微软雅黑';height: 699px;width:839px;}
	h2{line-height:30px; font-size:20px;}
	a,a:hover{ text-decoration:none;}
	pre{font-family:'微软雅黑'}
	.box{width:970px; padding:10px 20px; background-color:#fff; margin:10px auto;}
	.box a{padding-right:20px;}
	.ui-layout-center {height: 699px;width:999px;}
</style>
<body>

<div class="layui-layout layui-layout-admin">

	<div class="layui-header">
		<!--<div class="ui-layout-north">  -->
		            <div class="layui-logo">功能菜单</div>
		    <ul class="layui-nav layui-layout-left">
		  		<li class="layui-nav-item"> 欢迎[<shiro:principal/>]</li>
		    </ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="${pageContext.request.contextPath}/logout">退出</a></li>
			</ul>
	<!--	</div>  -->
	</div>
	
		<div class="layui-body">
			<div style="height: 699px;width:999px;">
	<%-- 			<iframe name="content" class="ui-layout-center"
				        src="${pageContext.request.contextPath}/welcome"></iframe> --%>
		        <iframe name="content" class="ui-layout-center"
		        src="${pageContext.request.contextPath}/welcome"></iframe>
			</div>
		</div>
		
		
	<div class="layui-footer">
	  <!-- <div class="ui-layout-south">  -->
		    GC夜岗巡查平台：<a href="http://www.gdgcep.com" target="_blank">http://www.gdgcep.com</a>
	  <!-- </div> -->
	</div>
	
	
	
	  <div class="layui-side layui-bg-black">
 		 <div class="layui-side-scroll">
					<!-- <div class="ui-layout-west"> -->
	    	<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;">
			    <c:forEach items="${menus}" var="m">
		  			  <li class="layui-nav-item layui-nav-itemed">
			       		   <a href="${pageContext.request.contextPath}/${m.url}" target="content">${m.name}</a>
			     	  </li>
			    </c:forEach>
	     	</ul>
					<!-- </div> -->
	 </div></div>



</div>

<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.layout-latest.min.js"></script>
<script>
window.alert = function() {
    return false;
}
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>