package cn.wolfcode.crm2.service;

import java.util.List;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

	Employee getEmployeeForLogin(String username, String password);

	PageResult queryForList(QueryObject qo);

	void updateState(Long id);

	List<Long> queryByEid(Long id);

	List<Employee> queryForDept();
}
