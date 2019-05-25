$(function(){
	$("#dept_datagrid").datagrid({
		title:'部门信息',
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		toolbar:'#dept_datagrid_bt',
		pageList:[5,10,20,30,40,50],
		url:'/department_list',
		onClickRow:function(rowIndex, rowData){
			if(rowData.state){
				$("#dept_datagrid_edit,#dept_datagrid_del").linkbutton("enable");
			}else{
				//禁用按钮
				$("#dept_datagrid_edit,#dept_datagrid_del").linkbutton("disable");
			}
		}, 
		columns:[[
			{field:'sn',title:'部门编号',align:'center',width:1},
			{field:'name',title:'部门名称',align:'center',width:1},
			{field:'manager',title:'部门经理',align:'center',width:1,formatter:managerFormatter},
			{field:'parent',title:'上级部门',align:'center',width:1,formatter:parentFormatter},
			{field:'state',title:'状态',align:'center',width:1,formatter:stateFormatter},
		]]
	});
	
	$("#dept_dialog").dialog({
		height:300,
		width:251,
		buttons:"#dept_dialog_bt",
		closed:true,
	});
});

function parentFormatter(value,row,index){
	return value?value.name:null;
}
function stateFormatter(value,row,index){
	return value?"<font style='color:green;'>正常</font>":"<font style='color:gray;'>停用</font>";
}
function managerFormatter(value,row,index){
	return value?value.username:null;
}
function add(){
	$("#dept_dialog").dialog("open");
	$("#dept_dialog").dialog("setTitle","新增");
	$("#dept_form").form("clear");
}
function edit(){
	//获取到选中的数据
	var rowData = $("#dept_datagrid").datagrid("getSelected");
	if(rowData){
		$("#dept_dialog").dialog("open");
		$("#dept_dialog").dialog("setTitle","编辑");
		$("#dept_form").form("clear");
		//特殊属性处理
		if(rowData.parent){
			rowData["parent.id"] = rowData.parent.id;
		}
		if(rowData.manager){
			rowData["manager.id"] = rowData.manager.id;
		}
		$("#dept_form").form("load",rowData);//基于同名匹配规则
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function del(){
	//获取到选中的数据
	var rowData = $("#dept_datagrid").datagrid("getSelected");
	if(rowData){
        $.messager.confirm('温馨提示', '你确定停用该部门？', function(r){
            if (r){
            	//删除数据
            	$.get("/deptloyee_delete?id="+rowData.id,function(data){
            		if(data.success){
            			$.messager.alert("温馨提示",data.msg,"info");
            			$("#dept_datagrid").datagrid("reload");
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
	$("#dept_datagrid").datagrid("load");
}
function save(){
	var idVal = $("#dept_form [name=id]").val();
	var url;
	if(idVal){
		url = '/deptloyee_update';
	}else{
		url = '/deptloyee_save';
	}
	$("#dept_form").form("submit",{
		url:url,
		success:function(data){
			data = $.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					$("#dept_dialog").dialog("close");
					$("#dept_datagrid").datagrid("load");
				});
			}else{
				$.messager.alert("温馨提示",data.msg,"info");
			}
		}
	});
}

function cancel(){
	$("#dept_dialog").dialog("close");
}

function searchBtn(){
	var value = $("[name='keyWord']").val();
	$("#dept_datagrid").datagrid("load",{
		keyWord:value
	});
}