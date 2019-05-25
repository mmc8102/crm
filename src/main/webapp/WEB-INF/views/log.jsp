<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统日志管理</title>
<%@ include file="common.jsp"%>
<script type="text/javascript" src="/js/views/log.js"></script>
</head>
<body>
	<table id="log_datagrid"></table>
	<div id="log_datagrid_bt">
		关键字查询<input type="text" name="keyWord" placeholder="操作用户 操作时间 登陆IP"/><a class="easyui-linkbutton" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">刷新</a> 
	</div>
</body>
</html>