<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/ui/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/easyloader.js"></script>
	<meta name="renderer" content="webkit">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/layui.css"  media="all">
  	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/lib/weui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-weui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/iconfont.css">

<script
	src="${pageContext.request.contextPath}/static/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/fastclick.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.Spinner.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-weui.js"></script>
<script src="${pageContext.request.contextPath}/static/js/swiper.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/easyloader.js"></script>
<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<%-- 
<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<shiro:hasPermission name="user:create">
    <a href="${pageContext.request.contextPath}/user/create">用户新增</a><br/>
</shiro:hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>用户名</th>
            <th>所属组织</th>
            <th>角色列表</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${zhangfn:organizationName(user.organizationId)}</td>
                <td>${zhangfn:roleNames(user.roleIds)}</td>
                <td>
                    <shiro:hasPermission name="user:update">
                        <a href="${pageContext.request.contextPath}/user/${user.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="user:delete">
                        <a href="${pageContext.request.contextPath}/user/${user.id}/delete">删除</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="user:update">
                        <a href="${pageContext.request.contextPath}/user/${user.id}/changePassword">改密</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table> --%>

<div>
	<table class="layui-table" lay-filter="test" id = "test">
	</table>
</div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="add">新增用户</button>
  </div>
</script>
<script type="text/html" id="barDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
	<button class="layui-btn layui-btn-sm" lay-event="del">删除</button>
	<button class="layui-btn layui-btn-sm" lay-event="cpwd">改密</button>
</div>
</script>
<script type="text/javascript"> 
	layui.use('table', function(){
		var table = layui.table;
		table.render({
		    elem: '#test'
		    ,toolbar: '#toolbarDemo'
		    ,title: '表'
		    ,height: 600
		    ,page: true //开启分页
		    //  ,toolbar: 'default' 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
		    ,cols: [[ //表头
		      {type: 'checkbox', fixed: 'left',hide: 'true'}
		      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left',hide: 'true'}
		      ,{field: 'username', title: '用户名', width:180, sort: true}
		      ,{field: 'organizationname', title: '所属组织', width: 399, sort: true}
		      ,{field: 'role', title: '角色列表', width: 200,}
		      ,{fixed: 'right',title: '操作', width: 176, align:'center', toolbar: '#barDemo'}/* */
		    ]]
		    ,data: ${jsons}
	  });
		 //监听头工具栏事件
		  table.on('toolbar(test)', function(obj){
		    var checkStatus = table.checkStatus(obj.config.id)
		    ,data = checkStatus.data; //获取选中的数据
		    switch(obj.event){
		      case 'add':
		    	  window.location.href = "${pageContext.request.contextPath}/user/create";
		      break;

		      break;
		    };
		  });
		  //监听行工具事件
		  table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		    var data = obj.data //获得当前行数据
		    ,layEvent = obj.event; //获得 lay-event 对应的值
		    if(layEvent === 'edit'){
 		      window.location.href = "${pageContext.request.contextPath}/user/"+data.id+"/update"; /* */
		    } else if(layEvent === 'del'){
/* 		      layer.confirm('真的删除行么', function(index){
		    	 obj.del(); //删除对应行（tr）的DOM结构 
		      }); */
		    	window.location.href = "${pageContext.request.contextPath}/user/"+data.id+"/delete"; 
		    } else if(layEvent === 'cpwd'){
		    	window.location.href = "${pageContext.request.contextPath}/user/"+data.id+"/changePassword"; 
		    }
		  });
	});
</script>
</body>
</html>