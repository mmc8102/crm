<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mmc8102.top/functions" prefix="myFn"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/pay.js"></script>
</head>
<body>
	<table id="pay_datagrid"></table>
	
	<!-- 工具栏按钮 -->
	<div id="pay_datagrid_bt">
		<div>
			年份:<select name="year">
					<option value="-1">全部</option>
					<option value="2018">2018</option>
					<option value="2017">2017</option>
					<option value="2016">2016</option>
				</select>
			月份:<select name="month">
					<option value="-1">全部</option>
					<option value="1">一月</option>
					<option value="2">二月</option>
					<option value="3">三月</option>
					<option value="4">四月</option>
					<option value="5">五月</option>
					<option value="6">六月</option>
					<option value="7">七月</option>
					<option value="8">八月</option>
					<option value="9">九月</option>
					<option value="10">十月</option>
					<option value="11">十一月</option>
					<option value="12">十二月</option>
				</select>
			员工:<input type="text" name="username" placeholder="姓名"/><a class="easyui-linkbutton" href="javascript:;" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		</div>
		<div>
			<form method="post" enctype="multipart/form-data">
				上传工资表单<input type="file" name="file" accept=".xls" required="required"/>
				<a class="easyui-linkbutton" onclick="save()">导入</a>
				<a class="easyui-linkbutton" href="/pay_download?fileName=pay.xls">导出</a>
			</form>
		</div>
	</div>
</body>
</html>