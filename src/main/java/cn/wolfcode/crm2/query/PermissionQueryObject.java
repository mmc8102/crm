package cn.wolfcode.crm2.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PermissionQueryObject extends QueryObject{
	private String keyWord;
	private Long rid;
	
	public String getKeyWord(){
		return empty2null(keyWord);
	}
}
