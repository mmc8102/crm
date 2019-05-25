package cn.wolfcode.crm2.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.domain.Role;
import cn.wolfcode.crm2.mapper.EmployeeMapper;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;
import cn.wolfcode.crm2.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return employeeMapper.deleteByPrimaryKey(id);
	}

	public int insert(Employee record) {
		int effectCount = employeeMapper.insert(record);
		for (Role r : record.getRoles()) {
			employeeMapper.insertRelation(record.getId(), r.getId());
		}
		return effectCount;
	}

	public Employee selectByPrimaryKey(Long id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	public List<Employee> selectAll() {
		return employeeMapper.selectAll();
	}

	public int updateByPrimaryKey(Employee record) {
		int effectCount = employeeMapper.updateByPrimaryKey(record);
		employeeMapper.deleteRelation(record.getId());
		for (Role r : record.getRoles()) {
			employeeMapper.insertRelation(record.getId(), r.getId());
		}
		return effectCount;
	}

	public Employee getEmployeeForLogin(String username, String password) {
		return employeeMapper.getEmployeeForLogin(username, password);
	}

	public PageResult queryForList(QueryObject qo) {
		Long count = employeeMapper.queryForCount(qo);
		if(count == 0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Employee> list = employeeMapper.queryForList(qo);
		return new PageResult(count.intValue(), list);
	}

	public void updateState(Long id) {
		employeeMapper.updateState(id);
	}

	public List<Long> queryByEid(Long id) {
		return employeeMapper.queryByEid(id);
	}

	public List<Employee> queryForDept() {
		return employeeMapper.queryForDept();
	}

}
