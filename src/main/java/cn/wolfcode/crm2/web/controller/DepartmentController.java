package cn.wolfcode.crm2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm2.domain.Department;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.DepartmentQueryObject;
import cn.wolfcode.crm2.service.IDepartmentService;
import cn.wolfcode.crm2.util.AjaxResult;

@Controller
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("/department")
	public String index(){
		return "department";
	}
	
	@RequestMapping("/department_queryForEmp")
	@ResponseBody
	public List<Department> queryForEmp(){
		return departmentService.queryForEmp();
	}
	
	@RequestMapping("/department_list")
	@ResponseBody
	public PageResult list(DepartmentQueryObject qo){
		return departmentService.queryForPage(qo);
	}
	
	@RequestMapping("/deptloyee_save")
	@ResponseBody
	public AjaxResult save(Department d){
		AjaxResult result;
		try{
			d.setState(true);
			departmentService.insert(d);
			result = new AjaxResult(true, "保存成功");
		}catch(Exception e){
			result = new AjaxResult("保存失败,请联系管理员");
		}
		
		return result;
	}
	
	@RequestMapping("/deptloyee_update")
	@ResponseBody
	public AjaxResult update(Department d){
		AjaxResult result;
		try{
			departmentService.updateByPrimaryKey(d);
			result = new AjaxResult(true, "更新成功");
		}catch(Exception e){
			result = new AjaxResult("更新失败,请联系管理员");
		}
		
		return result;
	}
	
	@RequestMapping("/deptloyee_delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult result;
		try{
			departmentService.updateState(id);
			result = new AjaxResult(true, "修改成功");
		}catch(Exception e){
			result = new AjaxResult("修改失败,请联系管理员");
		}
		
		return result;
	}
}
