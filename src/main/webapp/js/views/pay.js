$(function() {
	$("#pay_datagrid").datagrid({
		title : '工资表',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		url : '/pay_list',
		toolbar : '#pay_datagrid_bt',
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [ [ {
			field : 'sn',
			title : '编号',
			align : 'center',
			width : 1
		}, {
			field : 'year',
			title : '年份',
			align : 'center',
			width : 1
		}, {
			field : 'month',
			title : '月份',
			align : 'center',
			width : 1
		}, {
			field : 'pay',
			title : '工资',
			align : 'center',
			width : 1
		}, {
			field : 'emp',
			title : '员工',
			align : 'center',
			width : 1,
			formatter : empFormatter
		}, ] ]
	});

});

function empFormatter(value, row, index) {
	return value ? value.username : null;
}

function save() {
	var file = $("[name='file']").val();
	console.log(file);
	if (!file) {
		$.messager.alert("温馨提示", "请选择要上传的文件!", "info");
		return;
	}
	$("form").form("submit", {
		url : '/pay_save',
		success : function(data) {
			data = $.parseJSON(data);
			if (data.success) {
				$.messager.alert("温馨提示", data.msg, "info", function() {
					$("#emp_dialog").dialog("close");
					$("#emp_datagrid").datagrid("load");
				});
			} else {
				$.messager.alert("温馨提示", data.msg, "info");
			}
		}
	});
}

function searchBtn() {
	var usernameValue = $("[name='username']").val();
	var yearValue = $("[name='year']").val();
	var monthValue = $("[name='month']").val();
	$("#pay_datagrid").datagrid("load", {
		username : usernameValue,
		year : yearValue,
		month : monthValue
	});
}