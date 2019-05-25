$(function(){
	$("#role_datagrid").datagrid({
		title:'角色信息',
		fit:true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		toolbar:'#role_datagrid_bt',
		pageList:[5,10,20,30,40,50],
		url:'/role_list',
		columns:[[
			{field:'sn',title:'角色编号',align:'center',width:1},
			{field:'name',title:'角色名称',align:'center',width:1}
		]]
	});
	
	$("#role_dialog").dialog({
		height:450,
		width:700,
		buttons:"#role_dialog_bt",
		closed:true,
	});
	
	$("#allPermission").datagrid({
		width:300,
		height:300,
		title:'所有权限',
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:'/perimission_list',
		onDblClickRow:function(rowIndex, rowData){
			//判断selfPermission中是否有这条记录
				//1.拿到selfPermission中所有的记录
			var rows = $("#selfPermission").datagrid("getRows");
			var flag = false;
			var index = -1;
				//2.遍历selfPermission中的记录
			for(i = 0; i < rows.length; i++){
				if(rows[i].id == rowData.id){
					flag = true;
					index = i;
					break;
				}
			}
			
			if(flag){
				//如果有,则选中
				$("#selfPermission").datagrid("selectRow",index);
			}else{
				//如果没有,则新增
				$("#selfPermission").datagrid("appendRow",rowData);
			}
		},
		columns:[[
			{field:'name',title:'权限名',width:1,align:'center'}
		]]
	});
	
	var pager = $("#allPermission").datagrid("getPager");
	pager.pagination({
        showPageList: false,
        showRefresh: false,
        displayMsg: ''
	});
	
	$("#selfPermission").datagrid({
		width:300,
		height:300,
		title:'已有权限',
		fitColumns:true,
		rownumbers:true,
		singleSelect:true,
		onDblClickRow:function(rowIndex, rowData){
			$("#selfPermission").datagrid("deleteRow", rowIndex);
		},
		columns:[[
			{field:'name',title:'权限名',width:1,align:'center'}
		]]
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
	$("#role_dialog").dialog("open");
	$("#role_dialog").dialog("setTitle","新增");
	//清空表单内容
	$("[name='id'],[name='name'],[name='sn']").val("");
	//清空数据表格的内容 
	$("#selfPermissions").datagrid("loadData",{rows:[]});
}
function edit(){
	//获取到选中的数据
	var rowData = $("#role_datagrid").datagrid("getSelected");
	if(rowData){
		$("#role_dialog").dialog("open");
		$("#role_dialog").dialog("setTitle","编辑");
		$("[name='id'],[name='name'],[name='sn']").val("");
		
		var options = $("#selfPermission").datagrid("options");
		options.url="/permission_queryByRid";
		$("#selfPermission").datagrid("load", {
			rid:rowData.id
		});
		$("#role_form").form("load",rowData);//基于同名匹配规则
	}else{
		$.messager.alert("温馨提示","请选着一条数据","info");
	}
}
function del(){
	//获取到选中的数据
	var rowData = $("#role_datagrid").datagrid("getSelected");
	if(rowData){
        $.messager.confirm('温馨提示', '你确定删除该角色？', function(r){
            if (r){
            	//删除数据
            	$.get("/role_delete?id="+rowData.id,function(data){
            		if(data.success){
            			$.messager.alert("温馨提示",data.msg,"info");
            			$("#role_datagrid").datagrid("reload");
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
	$("#role_datagrid").datagrid("load");
}
function save(){
	var idVal = $("#role_form [name=id]").val();
	var url;
	if(idVal){
		url = '/role_update';
	}else{
		url = '/role_save';
	}
	$("#role_form").form("submit",{
		url:url,
		onSubmit: function(param){ 
			var rows = $("#selfPermission").datagrid("getRows");
			for(i=0; i<rows.length; i++){
				param["permissions["+i+"].id"] = rows[i].id;    
			}
	    },
		success:function(data){
			data = $.parseJSON(data);
			if(data.success){
				$.messager.alert("温馨提示",data.msg,"info",function(){
					$("#role_dialog").dialog("close");
					$("#role_datagrid").datagrid("load");
				});
			}else{
				$.messager.alert("温馨提示",data.msg,"info");
			}
		}
	});
}

function cancel(){
	$("#role_dialog").dialog("close");
}

function searchBtn(){
	var value = $("[name='keyWord']").val();
	$("#role_datagrid").datagrid("load",{
		keyWord:value
	});
}