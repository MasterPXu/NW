<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
	<template>
	  <div class="rate" :class="{'disabled':disabled}">
	    <i v-for="i in 5" class="iconfont" @mouseenter="disabled?'':curScore=i" @mouseleave="disabled?'':curScore=''" @click="disabled?'':setScore(i)" :class="getClass(i)">
	      <i v-if="disabled&&i==Math.floor(score)+1" class="iconfont icon-star" :style="'width:'+width"></i>
	    </i>
	    <span v-if="showText" class="text">{{curScore||score}}分</span>
	  </div>
	</template>
	  只读，不显示数字：<my-rate :score="1.5" disabled/>
	  只读，显示数字：<my-rate :score="3.6" disabled showText/>
	  鼠标点击评分,显示数字：<my-rate :score.sync="curScore" showText/>
	 <button @click="submit">提交</button>
	
</body>


<script src="${pageContext.request.contextPath}/static/vue.min.js" charset="utf-8"></script>
<script>
  export default {
    name:'MyRate',
    props: {
      score: {
        type: Number,
        default: 0,
        //required: true
      },
      disabled: {
        type: Boolean,
        default: false,
      },
      showText: {
        type: Boolean,
        default: false,
      },
    },
    data() {
      return {
        curScore: '',
        width:'',
      }
    },
    created: function () {
      this.getDecimal();
    },
    methods: {
      getClass(i) {
        if (this.curScore === '') {
          return i <= this.score ? 'icon-star' : 'icon-star-o'
        } else {
          return i <= this.curScore ? 'icon-star' : 'icon-star-o'
        }
      },
      getDecimal() {
        this.width=Number(this.score * 100 - Math.floor(this.score) * 100)+'%';
      },
      setScore(i){
        this.$emit('update:score',i);//使用`.sync`修饰符，对score 进行“双向绑定
      }
    }
  }
</script>
</html> --%>
<div id="app">
  <label for="r1">修改颜色</label><input type="checkbox" v-model="use" id="r1">
  <br><br>
  <div v-bind:class="{'class1': use}">
    v-bind:class 指令
  </div>
</div>
    
<script>
new Vue({
    el: '#app',
  data:{
      use: false
  }
});
</script>