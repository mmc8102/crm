package cn.wolfcode.crm2.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Permission;
import cn.wolfcode.crm2.domain.Role;
import cn.wolfcode.crm2.mapper.RoleMapper;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;
import cn.wolfcode.crm2.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	public int deleteByPrimaryKey(Long id) {
		roleMapper.deletePermissionRelation(id);
		roleMapper.deleteEmployeeRelation(id);
		return roleMapper.deleteByPrimaryKey(id);
	}

	public int insert(Role record) {
		int effectCount = roleMapper.insert(record);
		//插入中间表信息
		for (Permission p : record.getPermissions()) {
			roleMapper.insertRelation(record.getId(), p.getId());
		}
		return effectCount;
	}

	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	public int updateByPrimaryKey(Role record) {
		int effectCount = roleMapper.updateByPrimaryKey(record);
		roleMapper.deletePermissionRelation(record.getId());
		for (Permission p : record.getPermissions()) {
			roleMapper.insertRelation(record.getId(), p.getId());
		}
		return effectCount;
	}

	public PageResult queryForPage(QueryObject qo) {
		Long total = roleMapper.queryForCount(qo);
		if(total == 0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Role> rows = roleMapper.queryForList(qo);
		return new PageResult(total.intValue(), rows);
	}

}
