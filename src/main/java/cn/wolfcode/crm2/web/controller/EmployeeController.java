package cn.wolfcode.crm2.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.domain.Menu;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.EmployeeQueryObject;
import cn.wolfcode.crm2.service.IEmployeeService;
import cn.wolfcode.crm2.service.IMenuService;
import cn.wolfcode.crm2.service.IPermissionService;
import cn.wolfcode.crm2.util.AjaxResult;
import cn.wolfcode.crm2.util.PermissionUtil;
import cn.wolfcode.crm2.util.UserContext;

@Controller
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/employee")
	public String index(){
		return "employee";
	}
	
	@RequestMapping("/employee_list")
	@ResponseBody
	public PageResult list(EmployeeQueryObject qo){
		PageResult list = employeeService.queryForList(qo);
		return list;
	}
	
	@RequestMapping("/employee_save")
	@ResponseBody
	public AjaxResult save(Employee emp){
		AjaxResult result = null;
		try{
			emp.setPassword("123456");
			emp.setAdmin(false);
			emp.setState(true);
			employeeService.insert(emp);
			result = new AjaxResult(true, "添加成功");
		}catch(Exception e){
			result = new AjaxResult("添加失败,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/employee_delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult result = null;
		try{
			employeeService.updateState(id);
			result = new AjaxResult(true, "操作成功");
		}catch(Exception e){
			result = new AjaxResult("操作失败,请联系管理员");
		}
		
		return result;
	}
	
	@RequestMapping("/employee_update")
	@ResponseBody
	public AjaxResult update(Employee emp){
		AjaxResult result = null;
		try{
			employeeService.updateByPrimaryKey(emp);
			result = new AjaxResult(true, "修改成功");
		}catch(Exception e){
			result = new AjaxResult("修改失败,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/emp_queryByEid")
	@ResponseBody
	public List<Long> queryByEid(Long id){
		return employeeService.queryByEid(id);
	}

	@RequestMapping("/employee_queryForDept")
	@ResponseBody
	public List<Employee> queryForDept(){
		return employeeService.queryForDept();
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult login(String username, String password, HttpServletRequest request){
		AjaxResult result = null;
		UserContext.set(request);
		Employee current = employeeService.getEmployeeForLogin(username, password);
		if(current != null){
			request.getSession().setAttribute(UserContext.USER_IN_SESSION, current);
			//根据用户ID查询该员工所有权限信息,并存放到session中
			List<String> userPermission = permissionService.queryByEid(current.getId());
			for (String s : userPermission) {
				System.out.println(s);
			}
			request.getSession().setAttribute(UserContext.PERMISSION_IN_SESSION, userPermission);
			//查询用户菜单,并放到session中
			List<Menu> userMenu = menuService.queryForMenu();
			//用户菜单权限控制
			PermissionUtil.checkMenuPermission(userMenu);
			request.getSession().setAttribute(UserContext.MENU_IN_SESSION, userMenu);
			result = new AjaxResult(true, "登陆成功");
		}else{
			result = new AjaxResult("用户名或密码错误");
		}
		return result;
	}
}
