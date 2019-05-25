package cn.wolfcode.crm2.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Department;
import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.mapper.DepartmentMapper;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;
import cn.wolfcode.crm2.service.IDepartmentService;
@Service
public class DepartmentServiceImpl implements IDepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return departmentMapper.deleteByPrimaryKey(id);
	}

	public int insert(Department record) {
		return departmentMapper.insert(record);
	}

	public Department selectByPrimaryKey(Long id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

	public List<Department> selectAll() {
		return departmentMapper.selectAll();
	}

	public int updateByPrimaryKey(Department record) {
		return departmentMapper.updateByPrimaryKey(record);
	}

	public List<Department> queryForEmp() {
		return departmentMapper.queryForEmp();
	}

	public PageResult queryForPage(QueryObject qo) {
		Long count = departmentMapper.queryForCount(qo);
		if(count == 0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Employee> list = departmentMapper.queryForList(qo);
		return new PageResult(count.intValue(), list);
	}

	public void updateState(Long id) {
		departmentMapper.updateState(id);
	}
}
