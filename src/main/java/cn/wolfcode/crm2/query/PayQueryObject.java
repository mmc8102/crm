package cn.wolfcode.crm2.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PayQueryObject extends QueryObject{
	private Long year;
	private Long month;
	private String username;
	
	public String getUsername(){
		return empty2null(username);
	}
}
