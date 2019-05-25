package cn.wolfcode.crm2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm2.domain.Role;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.RoleQueryObject;
import cn.wolfcode.crm2.service.IRoleService;
import cn.wolfcode.crm2.util.AjaxResult;

@Controller
public class RoleController {
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/role")
	public String index(){
		return "role";
	}
	
	@RequestMapping("/role_save")
	@ResponseBody
	public AjaxResult save(Role role){
		AjaxResult result = null;
		try{
			roleService.insert(role);
			result = new AjaxResult(true, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/role_update")
	@ResponseBody
	public AjaxResult update(Role role){
		AjaxResult result = null;
		try{
			roleService.updateByPrimaryKey(role);
			result = new AjaxResult(true, "更新成功");
		}catch(Exception e){
			result = new AjaxResult("更新失败,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/role_list")
	@ResponseBody
	public PageResult list(RoleQueryObject qo){
		return roleService.queryForPage(qo);
	}
	
	@RequestMapping("/role_delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult result = null;
		try{
			roleService.deleteByPrimaryKey(id);
			result = new AjaxResult(true, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/role_queryForEmp")
	@ResponseBody
	public List<Role> queryForEmp(){
		return roleService.selectAll();
	}
}
