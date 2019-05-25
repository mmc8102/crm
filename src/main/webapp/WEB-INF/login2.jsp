<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理系统</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/default/easyui.css"> <!-- 样式文件 -->  
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/icon.css"> <!-- 图标样式 -->  
<script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script> <!-- jQuery核心库 -->
<script type="text/javascript" src="/js/jquery-easyui/jquery.easyui.min.js"></script><!-- EasyUI核心库 -->
<script type="text/javascript" src="/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script><!-- 语言包 -->
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
	$(function() {
	    // 在被嵌套时就刷新上级窗口
	    if(window.parent != window){
	    	window.parent.location.reload(true);
	    }
	});
	
	$(document).keyup(function(event){
		  console.log(event);
		  if(event.keyCode == 13){
			  submitForm();
		  }
	});
	
	//通过异步防止登陆
	function submitForm(){
		$.post("/login",$("form").serialize(),function(data){
			if(data.success){
				window.location.href = "/index";
			}else{
				$.messager.alert("温馨提示", data.msg, "info");
			}
		},"json");
	}
	
	function resetForm(){
		$("input[name]").val("");
	}
</script>
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form method="post">
        <p><input type="text" name="username" value="" placeholder="账号"></p>
        <p><input type="password" name="password" value="" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="submitForm()">
        	<input type="button" value="重置" onclick="resetForm()">
        </p>
      </form>
    </div>
  </section>
<div style="text-align:center;" class="login-help">
	<p>Copyright ©2015 广州小码哥教育科技有限公司</p>
</div>
</html>