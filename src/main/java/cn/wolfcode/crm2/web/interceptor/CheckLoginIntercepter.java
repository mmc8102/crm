package cn.wolfcode.crm2.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.util.PermissionUtil;
import cn.wolfcode.crm2.util.UserContext;

public class CheckLoginIntercepter implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserContext.set(request);
		//在session中获取用户数据
		Employee current = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
		if(null == current){
			//重定向到登录界面
			response.sendRedirect("/login.jsp");
			return false;
		}
		if(handler instanceof HandlerMethod){
			//登陆成功,在检查权限
			HandlerMethod mothod = (HandlerMethod)handler;
			String function = mothod.getBean().getClass().getName() + ":"+ mothod.getMethod().getName();
			//检查用户是否有该权限
			boolean flag = PermissionUtil.checkPermission(function);
			if(flag){
				return true;
			}else{
				if(mothod.getMethod().isAnnotationPresent(ResponseBody.class)){
					//如果是ajax
					response.sendRedirect("/errorpage/nopermission.json");
				}else{
					//如果是页面
					response.sendRedirect("/errorpage/nopermission.jsp");
				}
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
