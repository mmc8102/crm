package cn.wolfcode.crm2.page;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class PageResult {
	private Integer total;
	private List<?> rows;
	
	public PageResult(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
}
