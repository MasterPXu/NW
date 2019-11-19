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
</head>
<body>
	<div>	
		<div class="layui-form" >
		  <div class="layui-form-item">
		    <div class="layui-inline">
	          <label class="layui-form-label">巡岗日期</label>
		      <div class="layui-input-inline">
		        <input type="text" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
		      </div>
		    </div>
			<div class="weui-tabbar wy-foot-menu">
				<input type="button" id = "submit" value="提交（所选日期巡检人员）" class="layui-btn layui-btn-primary" onclick="formSubmit()">
				<!-- <input type="button" id = "submitAll" value="提交为长期巡检人员（每天晚8点到早6点会对该人员进行推送）" class="layui-btn layui-btn-primary" onclick="formSubmitAll()"> -->
				<input onclick="showdate()" type="button" value = "查看前一天数据(需选择具体日期)" class="layui-btn layui-btn-primary" >
				
			</div>
		  </div> 
		</div> <!-- -->

		<table class="layui-table" lay-filter="test" id = "test">
		</table>
		<script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
	</div>
</body>

<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
<script>
	var test = new Array();
	layui.use('table', function(){
		var table = layui.table;
		table.render({
		    elem: '#test'
		    ,toolbar: '#toolbarDemo'
		    ,title: '表'
		    ,height: 550
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {type: 'checkbox', fixed: 'left'}
		      ,{field: 'name', title: '回复者姓名', width: 120, sort: true, edit: 'text'}
		      ,{field: 'phoneNumber', title: '电话号', width:180, sort: true, edit: 'text'}
		      ,{field: 'dateStr', title: '巡检人员值班时间(例：19、22、23代表晚上8、10、11点 )', width:400, edit: 'text'}
		      ,{title: '操作', width: 65, align:'center', toolbar: '#barDemo'} /**/
		    ]]
		    ,data: test
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
          table.reload('test',{data : oldData});
	      break;
	      case 'update'://提交
	        layer.msg('提交');
	      break;
	      case 'delete':
	        if(data.length === 0){
	          layer.msg('请选择一行');
	        } else {
	          layer.msg('删除');
	        }
	      break;
	      case 'check':
	    	var a = document.getElementById("test1").value;
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
	    	 obj.del(); //删除对应行（tr）的DOM结构
	    	 layer.close(index);
	    	 
	    	 var oldData =  table.cache["test"];
	    	 for(var i = 0 ;i<oldData.length;i++){
	    		 if(oldData[i] == false)  
	             {  
 	    			 oldData.splice(i,1);  
                     i= i-1; /* */
                     console.log(oldData);
	             }  
	    	 }
	         table.reload('test',{data : oldData});
	      });
	    } else if(layEvent === 'edit'){
	      layer.msg('编辑操作');
	    }
	  });
	});
	
	//查看前一天数据
	function showdate(){			 
      var a = new Array();
      var con = {}
      con["date"] = document.getElementById("test1").value;
      a = con;
      var json = JSON.stringify(a); 
      console.log(json);
		 $.ajax({
             type : "GET",
             url : "/GC/show/showQuery",
             data: con,
/* 	                    dataType : "JSON", */
             heads : {
                 'content-type' : 'application/x-www-form-urlencoded'
             },
             success : function(data, textStatus) {
            	var test = data.result;
          	    var table = layui.table;
                table.reload('test',{data : test});
   				console.log(data.result);
   				console.log(textStatus);
             },
             error : function(XMLHttpRequest, textStatus,
                     errorThrown) {
    			 console.log(XMLHttpRequest);
       			 console.log(textStatus);
       			console.log(errorThrown);
             }
         });
	} 
</script>

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
	<button class="layui-btn layui-btn-sm" lay-event="check">查看</button>
</div>
</script>

<script type="text/javascript">
function isPoneAvailable(str) {
/*     var myreg=/^[1][3,4,5,7,8][0-9]{9}$/; */
    var myreg=/^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\d{8})$/
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}

	
//提交表单数据
function formSubmit(){
	 var table = layui.table;
	 var oldData =  table.cache["test"];
 	 var a = document.getElementById("test1").value;
	 var b = a;
	 var cons = new Array();		
     var flag = true;
	if(b == ''){
		alert("请选择日期");
		flag = false;
	}
	 for(var i = 0;i<oldData.length;i++){
		 console.log(oldData[i].name == undefined);
		if(oldData[i].name == "" || oldData[i].name == null || oldData[i].name == "undefined" || oldData[i].name == undefined || oldData[i].name == "null"){
			flag = false;
			alert("姓名不能为空");
		}else{
			if(isPoneAvailable(oldData[i].phoneNumber)){ 				
				 var con = {};
			     con["id"] = oldData[i].id;
			     con["phone"] = oldData[i].phoneNumber;
			     con["time"] = oldData[i].dateStr;
			     con["comps"] = oldData[i].comps;
			     con["name"] = oldData[i].name;
			     con["timeStamp"] = oldData[i].timeStamp;
			     con["date"] = b;
				 cons[i] = con;
			}else{
				flag = false;
				alert("在第" + (i+1) + "行，您输入的电话号码：" + oldData[i].phoneNumber + "有误，请输入正确的电话号码后重新提交。");
			}
		}
			
	 }
  var json = JSON.stringify(cons);
  console.log(json);	  
  /*  */
  if(flag){
	  $("#submit").attr("disabled",true); 
	 	$.messager.confirm('确认', '确定要提交所有的数据?', function(r) {
			$("#submit").attr("disabled",false); 
	      if (r) {
				$.ajax({
	              type : "post",
	              url : "/GC/test/doSave",
	              data : {
	                  json : json
	              },
	              heads : {
	                  'content-type' : 'application/x-www-form-urlencoded'
	              },
	              success : function(data, textStatus) {
	            	  console.log(data == 1);
	            	   if (data == 1) { 
	            		alert("添加成功");
	              	   	window.location.href = "${pageContext.request.contextPath}/show/show";
	                  } else if(data == 2){
	                	  alert("巡检人员值班时间设置出错，请严格按照格式对巡检时间进行设置，以“、”隔开  ");
	                  } else if(data == 3){
	                	  alert("请输入巡检时间！");
	                  } else{
	                      if (data.errorType == "test/error") {
	                          showAlertMsg("提示", "您填写的部分数据超出该条目的总分", "warning");
	                      }
	                  }
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
	      }
	  });
  }
}

//长期巡检人员
function formSubmitAll(){
	var score = new Array();
	var itemId = new Array();
	var totalScore = new Array();
	
	var cons = new Array(); 
	
	var flag = true;
	var a = document.getElementById("dataInfo").value;
	var b = a + " 12:34:23";
	var y = new Array();
	var z = new Array();
	var x = new Array();
	
	$(".name").each(function(){
		y.push($(this).val());
	});
	$(".phone").each(function(){
		if(isPoneAvailable($(this).val())){ 				
			z.push($(this).val());
		}else{
			flag = false;
			z.push("null");
			alert("您输入的电话号码："+$(this).val()+"有误，请输入正确的电话号码");
		}
	});
	 for(var i = 0;i<y.length;i++){
	    var con = {};
	    con["date"] = b;
	    con["name"] = y[i];
	    con["phone"] = z[i];
	    con["time"] = x[i];
	    cons[i] = con;
	  }
  var json = JSON.stringify(cons);
  $("#submit").attr("disabled",true); 
	$.messager.confirm('确认', '确定要提交所有的数据?', function(r) {
		$("#submit").attr("disabled",false); 
      if (r) {
			$.ajax({
              type : "post",
              url : "/GC/test/doSave",
              data : {
                  json : json
              },
              dataType : "JSON",
              heads : {
                  'content-type' : 'application/x-www-form-urlencoded'
              },
              success : function(data, textStatus) {
            	   if (data == 1) { 
              	   window.location.href = "${pageContext.request.contextPath}/show/showTask";
                  } else {
                      if (data.errorType == "test/error") {
                          showAlertMsg("提示", "您填写的部分数据超出该条目的总分", "warning");
                      }
                  }
              },
              error : function(XMLHttpRequest, textStatus,
                      errorThrown) {
      			$("#submit").attr("disabled",false); 
                  alert("数据填写错误");
              }
          });
      }
  });
}
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
</html>