package cn.wolfcode.crm2.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.domain.Log;
import cn.wolfcode.crm2.service.ILogService;

public class LogUtil {
	@Autowired
	private ILogService logService;
	
	public void writeLog(JoinPoint joinPoint){
		//出现自己切自己的情况
		if(joinPoint.getTarget() instanceof ILogService){
			return;
		}
		Log log = new Log();
		HttpServletRequest request = UserContext.get();
		Employee current = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
		String ip = request.getRemoteAddr();
		log.setOpuser(current);
		log.setOpip(ip);
		log.setOptime(new Date());
		String function = joinPoint.getTarget().getClass().getName() +":"+ joinPoint.getSignature().getName();
		log.setFunction(function);
		ObjectMapper mapper = new ObjectMapper();
		String params = null;
		try {
			params = mapper.writeValueAsString(joinPoint.getArgs());
			log.setParams(params);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logService.insert(log);
	}
}
