$(function(){
	$("#emp_datagrid").datagrid({
		title:'员工信息',
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		toolbar:'#emp_datagrid_bt',
		pageList:[5,10,20,30,40,50],
		url:'/employee_list',
		onClickRow:function(rowIndex, rowData){
			if(rowData.state){
				$("#emp_datagrid_edit,#emp_datagrid_del").linkbutton("enable");
			}else{
				//禁用按钮
				$("#emp_datagrid_edit,#emp_datagrid_del").linkbutton("disable");
			}
		}, 
		columns:[[
			{field:'username',title:'用户名',align:'center',width:1},
			{field:'realname',title:'真实姓名',align:'center',width:1},
			{field:'tel',title:'电话',align:'center',width:1},
			{field:'email',title:'电子邮箱',align:'center',width:1},
			{field:'dept',title:'部门',align:'center',width:1,formatter:deptFormatter},
			{field:'inputtime',title:'入职时间',align:'center',width:1},
			{field:'state',title:'状态',align:'center',width:1,formatter:stateFormatter},
			{field:'admin',title:'是否超级管理员',align:'center',width:1,formatter:adminFormatter}
		]]
	});
	
	$("#emp_dialog").dialog({
		height:300,
		width:251,
		buttons:"#emp_dialog_bt",
		closed:true,
	});
});

function deptFormatter(value,row,index){
	return value?value.name:null;
}
function stateFormatter(value,row,index){
	return value?"<font style='color:green'>正常</font>":"<font style='color:red'>离职</font>";
}
function adminFormatter(value,row,index){
	return value?'是':'否';
}
function add(){
	$("#emp_dialog").dialog("open");
	$("#emp_dialog").dialog("setTitle","新增");
	$("#emp_form").form("clear");
}
function edit(){
	//获取到选中的数据
	var rowData = $("#emp_datagrid").datagrid("getSelected");
	if(rowData){
		$("#emp_dialog").dialog("open");
		$("#emp_dialog").dialog("setTitle","编辑");
		$("#emp_form").form("clear");
		//特殊属性处理
		if(rowData.dept){
			rowData["dept.id"] = rowData.dept.id;
		}
		//发送一个同步请求到后台查询该员工角色集合
		 var html = $.ajax({
			  url: "/emp_queryByEid?id="+rowData.id,
			  async: false
			 }).responseText;
		 console.log(html);
		 html = $.parseJSON(html);
		 $("#emp_roles").combobox("setValues",html);
		$("#emp_form").form("load",rowData);//基于同名匹配规则
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function del(){
	//获取到选中的数据
	var rowData = $("#emp_datagrid").datagrid("getSelected");
	if(rowData){
        $.messager.confirm('温馨提示', '你确定离职该员工？', function(r){
            if (r){
            	//删除数据
            	$.get("/employee_delete?id="+rowData.id,function(data){
            		if(data.success){
            			$.messager.alert("温馨提示",data.msg,"info");
            			$("#emp_datagrid").datagrid("reload");
            		}else{
            			$.messager.alert("温馨提示",data.msg,"info");
            		}
            	},'json');
            }
        });
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function reflesh(){
	$("#emp_datagrid").datagrid("load");
}
function save(){
	var idVal = $("#emp_form [name=id]").val();
	var url;
	if(idVal){
		url = '/employee_update';
	}else{
		url = '/employee_save';
	}
	$("#emp_form").form("submit",{
		url:url,
		onSubmit: function(param){ 
			var values = $("#emp_roles").combobox("getValues");
			for(i=0; i<values.length; i++){
				param["roles["+i+"].id"] = values[i];    
			}
	    },
		success:function(data){
			data = $.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					$("#emp_dialog").dialog("close");
					$("#emp_datagrid").datagrid("load");
				});
			}else{
				$.messager.alert("温馨提示",data.msg,"info");
			}
		}
	});
}

function cancel(){
	$("#emp_dialog").dialog("close");
}

function searchBtn(){
	var value = $("[name='keyWord']").val();
	$("#emp_datagrid").datagrid("load",{
		keyWord:value
	});
}