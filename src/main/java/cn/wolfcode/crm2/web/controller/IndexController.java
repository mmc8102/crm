package cn.wolfcode.crm2.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm2.domain.Menu;
import cn.wolfcode.crm2.util.UserContext;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/queryForMenu")
	@ResponseBody
	public List<Menu> queryForMenu(){
		List<Menu> result;
		//result = menuService.queryForMenu();
		//登陆的时候就把菜单放入session中,再从session中获取
		result = (List<Menu>) UserContext.get().getSession().getAttribute(UserContext.MENU_IN_SESSION);
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(){
		UserContext.get().getSession().removeAttribute(UserContext.USER_IN_SESSION);
		return "redirect:/login.jsp";
	}
	
}
