package cn.wolfcode.crm2.service;

import java.util.List;

import cn.wolfcode.crm2.domain.Department;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;

public interface IDepartmentService {
	int deleteByPrimaryKey(Long id);

	int insert(Department record);

	Department selectByPrimaryKey(Long id);

	List<Department> selectAll();

	int updateByPrimaryKey(Department record);

	List<Department> queryForEmp();

	PageResult queryForPage(QueryObject qo);

	void updateState(Long id);
}
