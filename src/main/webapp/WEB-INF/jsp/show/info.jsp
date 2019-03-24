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
	<meta name="renderer" content="webkit">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
</head>
<body>
<%-- 	<div>
		<table class="table">
		    <thead>
		        <tr>
		        	<th>人员名称</th>
		        	<th>所在公司</th>
		            <th>手机号</th>
		            <th>发送的验证码</th>
		            <th>回复的验证码</th>
		            <th>回复时间</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${results}" var="result">
		            <tr>
	    	            <td>${result.replyName}</td>
		                <td>${result.compsName}</td>
		                <td>${result.phoneNumber}</td>
		                <td>${result.taskInfo}</td>
		                <td>${result.taskResult}</td>
		                <td>${result.time}</td> 
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div> --%>
	
	<div>
<%-- 		<table class="layui-table" lay-data="${json}" lay-filter="test" id = "test">
		  <thead>
		    <tr>
		      <th lay-data="{type:'checkbox'}">ID</th>
		      <th lay-data="{field:'id', width:80, sort: true}">ID</th>
		      <th lay-data="{field:'taskInfo', edit: 'text', minWidth: 150}">信息</th>
		      <th lay-data="{field:'taskResult', width:80, edit: 'text'}">回传</th>
		      <th lay-data="{field:'phoneNumber', edit: 'text', minWidth: 100}">电话</th>
		      <th lay-data="{field:'replyName', edit: 'text', minWidth: 100}">人名</th>
		      <th lay-data="{field:'compsName', sort: true, edit: 'text'}">公司名</th>
		      <th lay-data="{field:'time', width:120, sort: true, edit: 'text'}">时间</th>
		    </tr>
		  </thead>
/* 		      ,{field: 'taskResult', title: '回复', width: 90, sort: true, totalRow: true} */
		</table> --%>
		<table class="layui-table" lay-filter="test" id = "test">
		</table>
		<script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
	</div>

</body>

<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
<script>
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
		      {type: 'checkbox', fixed: 'left'}
		      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left',hide: 'true'}
		      ,{field: 'phoneNumber', title: '电话号', width:180, sort: true, edit: 'text'}
		      ,{field: 'replyName', title: '回复者姓名', width: 120, sort: true, edit: 'text'}
		      ,{field: 'taskInfo', title: '信息', width:80, edit: 'text'}
		      ,{field: 'taskResult', title: '回复', width: 90, sort: true, edit: 'text'}
		      ,{field: 'compsName', title: '公司名', width:250, edit: 'text'} 
		      ,{field: 'time', title: '时间', width: 200, edit: 'text'}
 		      ,{fixed: 'right',title: '操作', width: 65, align:'center', toolbar: '#barDemo'} /**/
		    ]]
		    ,data: ${json}
	  });
		
	 //监听头工具栏事件
	  table.on('toolbar(test)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id)
	    ,data = checkStatus.data; //获取选中的数据
	    switch(obj.event){
	      case 'add':
          var oldData =  table.cache["test"];
          var data1={};
          oldData.push(data1);
          console.log(oldData);
          table.reload('test',{data : oldData});
          layer.msg('添加');
	      break;
	      case 'update'://提交
	        layer.msg('提交');
/* 	        if(data.length === 0){
	          layer.msg('请选择一行');
	        } else if(data.length > 1){
	          layer.msg('只能同时编辑一个');
	        } else {
	          layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
	        } */
	      break;
	      case 'delete':
	        if(data.length === 0){
	          layer.msg('请选择一行');
	        } else {
	          layer.msg('删除');
	        }
	      break;
	      case 'check':
	    	  var result = ${results};
	    	  console.log(result);
	    	  console.log("olddata----------------");
	          var oldData =  table.cache["test"];
	          console.log(oldData);
	      break;
	    };
	  });
	  //监听行工具事件
	  table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
	    var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'detail'){
	      layer.msg('查看操作');
	    } else if(layEvent === 'del'){
	      layer.confirm('真的删除行么', function(index){
/* 	        obj.del(); //删除对应行（tr）的DOM结构
	        layer.close(index); */
	        console.log(data);
	        //向服务端发送删除指令
	      });
	    } else if(layEvent === 'edit'){
	      layer.msg('编辑操作');
	    }
	  });
	});
</script>

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    <button class="layui-btn layui-btn-sm" lay-event="update">提交</button>
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
    <button class="layui-btn layui-btn-sm" lay-event="check">查看</button>
  </div>
</script>
</html>