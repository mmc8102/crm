<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mmc8102.top/functions" prefix="myFn"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/department.js"></script>
</head>
<body>
	<table id="dept_datagrid"></table>
	
	<!-- 新增/编辑对话框 -->
	<div id="dept_dialog">
		<form id="dept_form" method="post">
			<input type="hidden" name="id"/>
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>部门编号</td>
					<td><input type="text" name="sn"/></td>
				</tr>
				<tr>
					<td>部门名称</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>部门经理</td>
					<td><input type="text" class="easyui-combobox" name="manager.id" data-options="valueField:'id',textField:'username',url:'/employee_queryForDept'"/></td>
				</tr>
				<tr>
					<td>上级部门</td>
					<td><input type="text" class="easyui-combobox" name="parent.id" data-options="valueField:'id',textField:'name',url:'/department_queryForEmp'"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 工具栏按钮 -->
	<div id="dept_datagrid_bt">
		<div>
			<c:if test="${myFn:checkPermission('cn.wolfcode.crm2.web.controller.DepartmentController:save')}">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			</c:if>
			<c:if test="${myFn:checkPermission('cn.wolfcode.crm2.web.controller.DepartmentController:update')}">
				<a id="dept_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			</c:if>
			<c:if test="${myFn:checkPermission('cn.wolfcode.crm2.web.controller.DepartmentController:delete')}">
				<a id="dept_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">停用</a>
			</c:if>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reflesh()">刷新</a>
		</div>
	</div>
	<!-- 对话框底部按钮 -->
	<div id="dept_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>