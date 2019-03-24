<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %> 
<html>
<head>
    <title></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/ui/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/easyloader.js"></script>
</head>
<body>
	<table class="table">
	    <thead>
	        <tr>
	        	<th>人员名称</th>
	            <th>手机号</th>
	            <th>安排时间</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${results}" var="result">
	            <tr>
    	            <td>${result.name}</td>
	                <td>${result.phoneNumber}</td>
	                <td>${result.time}</td>
                    <td>
	                    <a href="${pageContext.request.contextPath}/show/${result.id}/update">修改</a>
	                    <a href="${pageContext.request.contextPath}/show/${result.id}/delete">删除</a>
                    </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
</body>
</html>