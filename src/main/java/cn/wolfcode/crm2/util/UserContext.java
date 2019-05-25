package cn.wolfcode.crm2.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {
	public final static String USER_IN_SESSION = "user_in_session";
	public final static String PERMISSION_IN_SESSION = "permission_in_session";
	public final static String MENU_IN_SESSION = "menu_in_session";
	private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();
	
	private UserContext(){}
	
	public static void set(HttpServletRequest request){
		local.set(request);
	}
	
	public static HttpServletRequest get(){
		return local.get();
	}
}
