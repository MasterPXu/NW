<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<style>
/* 	html{background-color:#E3E3E3; font-size:14px; color:#000; font-family:'微软雅黑'}
	input{background-color:#E3E3E3; font-size:14px; color:#000; font-family:'微软雅黑'} */
	h2{line-height:30px; font-size:20px;}
	a,a:hover{ text-decoration:none;}
	pre{font-family:'微软雅黑'}
	.box{width:970px; padding:10px 20px; background-color:#fff; margin:10px auto;}
	.box a{padding-right:20px;}
</style>

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
	GC夜巡平台
	<!-- <input onclick="sendMSG()" type="button" value = "sendMSG" class="layui-btn layui-btn-primary" > -->
</body>


<script  type="text/javascript">
	$(function() {
		FastClick.attach(document.body);
	});
	
	function isPoneAvailable(str) {
          var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
          if (!myreg.test(str)) {
              return false;
          } else {
              return true;
          }
      }
      
	function sendMSG(){
		 $.ajax({
             type : "GET",
             url : "/GC/send/sendMsg",
             data: "",
             heads : {
                 'content-type' : 'application/x-www-form-urlencoded'
             },
             success : function(data, textStatus) {
   				console.log(data);
             },
             error : function(XMLHttpRequest, textStatus,
                     errorThrown) {
    			 console.log(XMLHttpRequest);
       			 console.log(textStatus);
       			console.log(errorThrown);
             }
         });
	}
	
	
 	function showdate(){
 		var flag = true;
 		var a = document.getElementById("dataInfo").value;
 		var b = a + " 12:34:23";
 		var d;
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
 		
 		$(".time").each(function(){
 			var c = $(this).val().split("、");
 			for(i=0;i<c.length ;i++ ){
 			  d = a + " " + c[i] + ":11:11";
 			  x.push(d);
 			}
 			
		});
		if(flag){
			console.log(d);
			console.log(typeof(b)); 
			console.log(y + ":" + typeof(y));
			console.log(z + ":" + typeof(z));
			console.log(x + ":" + typeof(z));
		}else{
			console.log("data error");
		}
	} /**/


		
	//提交表单数据
	function formSubmit(){
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
    /*                 	   showRightBottomMsg("系统提示", "提交成功！", 'slide',
                                   5000); */
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
	
	function createRow(){
		var editTable=document.getElementById("tbody");
		var tr=document.createElement("tr");
		var td0=document.createElement("td");
		var checkbox=document.createElement("input");
		checkbox.type="checkbox";
		checkbox.name="checkRow";
		td0.appendChild(checkbox);
		var td1=document.createElement("td");
		td1.innerHTML="<input type='text' id = 'test' class='name'/>";
		var td2=document.createElement("td");
		td2.innerHTML="<input type='text' class='phone'/>";
		var td3=document.createElement("td");
		td3.innerHTML="<input class='laydate-icon' onclick='laydate()'>";
		var td4=document.createElement("td");
		td4.innerHTML="<input type='text' class='time'/>";
		tr.appendChild(td0);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td4);
/* 		tr.appendChild(td3); */
		editTable.appendChild(tr);
	}
	
	function delRow(){
		var editTable=document.getElementById("tbody");
		if(confirm("确认删除所选?")){
			var checkboxs=document.getElementsByName("checkRow");
			for(var i=0;i<checkboxs.length;i++){
				if(checkboxs[i].checked){
					var n=checkboxs[i].parentNode.parentNode;
					editTable.removeChild(n);
					i--;
				}
			}
			
		}
	}
</script>
</html>