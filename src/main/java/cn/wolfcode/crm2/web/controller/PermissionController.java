package cn.wolfcode.crm2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm2.domain.Permission;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.PermissionQueryObject;
import cn.wolfcode.crm2.service.IPermissionService;
import cn.wolfcode.crm2.util.AjaxResult;

@Controller
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/permission")
	public String index(){
		return "permission";
	}
	
	@RequestMapping("/perimission_list")
	@ResponseBody
	public PageResult list(PermissionQueryObject qo){
		return permissionService.queryForList(qo);
	}
	
	@RequestMapping("/permission_queryByRid")
	@ResponseBody
	public PageResult queryByRid(PermissionQueryObject qo){
		return permissionService.queryForList(qo);
	}
	
	@RequestMapping("/permission_save")
	@ResponseBody
	public AjaxResult save(Permission p){
		AjaxResult result;
		try{
			permissionService.insert(p);
			result = new AjaxResult(true, "添加成功");
		}catch(Exception e){
			result = new AjaxResult("添加异常,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/permission_update")
	@ResponseBody
	public AjaxResult update(Permission p){
		AjaxResult result;
		try{
			permissionService.updateByPrimaryKey(p);
			result = new AjaxResult(true, "更新成功");
		}catch(Exception e){
			result = new AjaxResult("更新异常,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/permission_delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult result;
		try{
			permissionService.deleteByPrimaryKey(id);
			result = new AjaxResult(true, "删除成功");
		}catch(Exception e){
			result = new AjaxResult("删除异常,请联系管理员");
		}
		return result;
	}
}
