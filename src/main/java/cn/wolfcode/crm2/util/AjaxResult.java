package cn.wolfcode.crm2.util;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AjaxResult {
	private Boolean success;
	private String msg;
	
	public AjaxResult(String msg) {
		this.msg = msg;
	}

	public AjaxResult(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
}
