<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统权限管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/permission.js"></script>
</head>
<body>
	<table id="permission_datagrid"></table>
	
	<!-- 新增/编辑对话框 -->
	<div id="permission_dialog">
		<form id="permission_form" method="post">
			<input type="hidden" name="id"/>
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>权限名称</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>资源地址</td>
					<td><input type="text" name="resource"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 工具栏按钮 -->
	<div id="permission_datagrid_bt">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			<a id="permission_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			<a id="permission_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reflesh()">刷新</a>
		</div>
		<div>
			关键字查询:<input type="text" name="keyWord" placeholder="权限名称"/><a class="easyui-linkbutton" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		</div>
	</div>
	<!-- 对话框底部按钮 -->
	<div id="permission_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>