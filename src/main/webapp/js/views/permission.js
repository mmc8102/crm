$(function(){
	$("#permission_datagrid").datagrid({
		title:'权限信息',
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		toolbar:'#permission_datagrid_bt',
		pageList:[5,10,20,30,40,50],
		url:'/perimission_list',
		columns:[[
			{field:'name',title:'权限名称',align:'center',width:1},
			{field:'resource',title:'资源地址',align:'center',width:1},
		]]
	});
	
	$("#permission_dialog").dialog({
		height:150,
		width:251,
		buttons:"#permission_dialog_bt",
		closed:true,
	});
});

function add(){
	$("#permission_dialog").dialog("open");
	$("#permission_dialog").dialog("setTitle","新增");
	$("#permission_form").form("clear");
}
function edit(){
	//获取到选中的数据
	var rowData = $("#permission_datagrid").datagrid("getSelected");
	if(rowData){
		$("#permission_dialog").dialog("open");
		$("#permission_dialog").dialog("setTitle","编辑");
		$("#permission_form").form("clear");
		$("#permission_form").form("load",rowData);//基于同名匹配规则
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function del(){
	//获取到选中的数据
	var rowData = $("#permission_datagrid").datagrid("getSelected");
	if(rowData){
        $.messager.confirm('温馨提示', '你确定删除该权限？', function(r){
            if (r){
            	//删除数据
            	$.get("/permission_delete?id="+rowData.id,function(data){
            		if(data.success){
            			$.messager.alert("温馨提示",data.msg,"info");
            			$("#permission_datagrid").datagrid("reload");
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
	$("#permission_datagrid").datagrid("load");
}
function save(){
	var idVal = $("#permission_form [name=id]").val();
	var url;
	if(idVal){
		url = '/permission_update';
	}else{
		url = '/permission_save';
	}
	$("#permission_form").form("submit",{
		url:url,
		success:function(data){
			data = $.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					$("#permission_dialog").dialog("close");
					$("#permission_datagrid").datagrid("load");
				});
			}else{
				$.messager.alert("温馨提示",data.msg,"info");
			}
		}
	});
}

function cancel(){
	$("#permission_dialog").dialog("close");
}

function searchBtn(){
	var value = $("[name='keyWord']").val();
	$("#permission_datagrid").datagrid("load",{
		keyWord:value
	});
}