package cn.wolfcode.crm2.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class LogQueryObject extends QueryObject{
	private String keyWord;
	
	public String getKeyWord(){
		return empty2null(keyWord);
	}
}
