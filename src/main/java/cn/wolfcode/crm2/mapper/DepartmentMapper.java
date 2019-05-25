package cn.wolfcode.crm2.mapper;

import cn.wolfcode.crm2.domain.Department;
import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

	List<Department> queryForEmp();

	Long queryForCount(QueryObject qo);

	List<Employee> queryForList(QueryObject qo);

	void updateState(Long id);
}