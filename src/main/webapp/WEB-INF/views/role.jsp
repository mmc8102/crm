<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
	<table id="role_datagrid"></table>
	
	<!-- 新增/编辑对话框 -->
	<div id="role_dialog">
		<form id="role_form" method="post">
			<input type="hidden" name="id"/>
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>角色名称<input type="text" name="name"/></td>
					<td>角色编号<input type="text" name="sn"/></td>
				</tr>
				<tr>
					<td><table id="allPermission"></table></td>
					<td><table id="selfPermission"></table></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 工具栏按钮 -->
	<div id="role_datagrid_bt">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			<a id="role_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			<a id="role_datagrid_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reflesh()">刷新</a>
		</div>
		<div>
			关键字查询:<input type="text" name="keyWord" placeholder="角色编号 角色名称"/><a class="easyui-linkbutton" iconCls="icon-search" onclick="searchBtn()">搜索</a>
		</div>
	</div>
	<!-- 对话框底部按钮 -->
	<div id="role_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save()">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  onclick="cancel()">取消</a>
	</div>
</body>
</html>