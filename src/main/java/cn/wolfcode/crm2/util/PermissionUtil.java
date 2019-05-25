package cn.wolfcode.crm2.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.domain.Menu;
import cn.wolfcode.crm2.domain.Permission;
import cn.wolfcode.crm2.service.IPermissionService;
@Component
public class PermissionUtil {
	private static IPermissionService permissionService;
	
	@Autowired
	public void setPermissionService(IPermissionService permissionService) {
		PermissionUtil.permissionService = permissionService;
	}

	public static boolean checkPermission(String function) {
		HttpSession session = UserContext.get().getSession();
		Employee currentUser = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
		//超级管理员直接放行
		if(currentUser.getAdmin()){
			return true;
		}
		//在所有权限中检查是否有该权限
		//先获取所有权限信息
		if(CommonUtil.allPermission.size() == 0){
			//从数据库中查询所有的权限并封装到allPermission中
			List<Permission> list = permissionService.selectAll();
			for (Permission p : list) {
				CommonUtil.allPermission.add(p.getResource());
			}
		}
		//判断该表达式是否需要权限控制
		if(CommonUtil.allPermission.contains(function)){
			//如果有,该表达式需要控制
			//拿到用户所有权限集合,从session中获取
			List<String> userPermission = (List<String>) session.getAttribute(UserContext.PERMISSION_IN_SESSION);
			//进行完全匹配
			if(userPermission.contains(function)){
				return true;
			}else{
				//ALL权限匹配
				String userAllpermission = function.split(":")[0] + ":ALL";
				if(userPermission.contains(userAllpermission)){
					return true;
				}else{
					return false;
				}
			}
			
		}else{
			//如果没有,表示该权限不需要控制,放行
			return true;
		}
	}

	/**
	 * 如果用户没有的菜单就从列表中删除
	 * @param userMenu
	 */
	public static void checkMenuPermission(List<Menu> userMenu) {
		Employee current = (Employee) UserContext.get().getSession().getAttribute(UserContext.USER_IN_SESSION);
		//如果是超级管理员直接放行
		if(current.getAdmin()){
			return;
		}
		
		Menu menu;
		//遍历第一层,看哪些需要权限控制
		for (int i = userMenu.size()-1; i >= 0; i--) {
			menu = userMenu.get(i);
			if(StringUtils.isNotBlank(menu.getFunction())){
				//拿到用户权限集合,看看该用户是否有该权限
				List<String> userPermission = (List<String>)UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
				if(!userPermission.contains(menu.getFunction())){
					//删除该菜单
					userMenu.remove(i);
				}
			}
			//判断是否有子节点
			if(menu.getChildren() != null && menu.getChildren().size() > 0){
				checkMenuPermission(menu.getChildren());
			}
		}
	}
}
