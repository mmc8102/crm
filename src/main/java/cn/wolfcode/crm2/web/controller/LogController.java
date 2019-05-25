package cn.wolfcode.crm2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.LogQueryObject;
import cn.wolfcode.crm2.service.ILogService;

@Controller
public class LogController {
	@Autowired
	private ILogService logService;
	
	@RequestMapping("/log")
	public String index(){
		return "log";
	}
	
	@RequestMapping("/log_list")
	@ResponseBody
	public PageResult list(LogQueryObject qo){
		PageResult result = null;
		result = logService.queryForList(qo);
		return result;
	}
}
