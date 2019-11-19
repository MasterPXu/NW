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
</head>
<body>
	<div>
		<table class="layui-table" lay-filter="test" id = "test">
		</table>
	</div>
</body>
		<script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-sm" lay-event="check">导出所有数据</a>
			<a class="layui-btn layui-btn-sm" lay-event="checkout">导出缺岗人员数据</a>
		</script>

<script type="text/javascript">
var laydate = layui.laydate;
layui.use('laydate', function(){
  var laydate = layui.laydate;
  //常规用法
  laydate.render({
    elem: '#test1'
  });
  
  //国际版
  laydate.render({
    elem: '#test1-1'
    ,lang: 'en'
  });
  
  //年选择器
  laydate.render({
    elem: '#test2'
    ,type: 'year'
  });
  
  //年月选择器
  laydate.render({
    elem: '#test3'
    ,type: 'month'
  });
  
  //时间选择器
  laydate.render({
    elem: '#test4'
    ,type: 'time'
  });
  
  //日期时间选择器
  laydate.render({
    elem: '#test5'
    ,type: 'datetime'
  });
  
  //日期范围
  laydate.render({
    elem: '#test6'
    ,range: true
  });
});
</script>

<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
<script>
	layui.use('table', function(){
		var table = layui.table;
		table.render({
		    elem: '#test'
		    ,toolbar: '#barDemo' 
		    ,title: '到岗信息表'
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
		      ,{field: 'time', title: '时间', width: 200,sort: true,edit: 'text'}
		      /*,{fixed: 'right',title: '操作', width: 65, align:'center', toolbar: '#barDemo'} */
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
	      break;
	      case 'checkout':
				$.ajax({
		              type : "get",
		              url : "/GC/show/exportAbs",
		              data : "1",
		              heads : {
		                  'content-type' : 'application/x-www-form-urlencoded'
		              },
		              success : function(data, textStatus) {
	            		   var result = data.exportAbs; /* */
	            		   table.exportFile(obj.config.id, result, 'xls');
		              },
		              error : function(XMLHttpRequest, textStatus,
		                      errorThrown) {
		     			 console.log(XMLHttpRequest);
		       			 console.log(textStatus);
		       			console.log(errorThrown);
		      			$("#submit").attr("disabled",false); 
		                  alert("数据填写错误");
		              }
		          });
	      break;
	      case 'check':
				$.ajax({
		              type : "get",
		              url : "/GC/show/export",
		              data : "1",
		              heads : {
		                  'content-type' : 'application/x-www-form-urlencoded'
		              },
		              success : function(data, textStatus) {
	            		   var result = data.exports; /* */
	            		   console.log(data);
	            		   table.exportFile(obj.config.id, result, 'xls');
		              },
		              error : function(XMLHttpRequest, textStatus,
		                      errorThrown) {
		     			 console.log(XMLHttpRequest);
		       			 console.log(textStatus);
		       			console.log(errorThrown);
		      			$("#submit").attr("disabled",false); 
		                  alert("数据填写错误");
		              }
		          });
/* 	    	  var result = ${json};
		      table.exportFile(obj.config.id, result, 'xls');
	    	  console.log(result);
 	    	  console.log("olddata----------------");
	          var oldData =  table.cache["test"];
	          console.log(oldData); */
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
</html>