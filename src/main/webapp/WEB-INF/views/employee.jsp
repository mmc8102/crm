<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mmc8102.top/functions" prefix="myFn"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
	<table id="emp_datagrid"></table>
	
	<!-- 新增/编辑对话框 -->
	<div id="emp_dialog">
		<form id="emp_form" method="post">
			<input type="hidden" name="id"/>
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>账号</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>真实姓名</td>
					<td><input type="text" name="realname"/></td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td><input type="text" name="tel"/></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<td>部门</td>
					<td><input type="text" class="easyui-combobox" name="dept.id" data-options="valueField:'id',textField:'name',url:'/department_queryForEmp'"/></td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input type="text" class="easyui-datebox" name="inputtime"/></td>
				</tr>
				<tr>
					<td>角色</td>
					<td><input id="emp_roles" type="text" class="easyui-combobox" data-options="valueField:'id',textField:'name',url:'/role_queryForEmp',multiple:true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 工具栏按钮 -->
	<div id="emp_datagrid_bt">
		<div>
			<c:if test="${myFn:checkPermission('cn.wolfcode.crm2.web.controller.EmployeeController:save')}">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			</c:if>
			<c:if test="${myFn:checkPermission('cn.wolfcode.crm2.web.controller.EmployeeController:update')}">
				<a id="emp_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			</c:if>
			<c:if test="${myFn:checkPermission('cn.wolfcode.crm2.web.controller.EmployeeController:delete')}">
				<a id="emp_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">离职</a>
			</c:if>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reflesh()">刷新</a>
		</div>
		<div>
			关键字查询:<input type="text" name="keyWord" placeholder="账号 姓名 电话"/><a class="easyui-linkbutton" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		</div>
	</div>
	<!-- 对话框底部按钮 -->
	<div id="emp_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>