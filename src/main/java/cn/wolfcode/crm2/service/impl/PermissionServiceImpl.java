package cn.wolfcode.crm2.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Permission;
import cn.wolfcode.crm2.mapper.PermissionMapper;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;
import cn.wolfcode.crm2.service.IPermissionService;
@Service
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private PermissionMapper permissionMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	public int insert(Permission record) {
		return permissionMapper.insert(record);
	}

	public Permission selectByPrimaryKey(Long id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	public List<Permission> selectAll() {
		return permissionMapper.selectAll();
	}

	public int updateByPrimaryKey(Permission record) {
		return permissionMapper.updateByPrimaryKey(record);
	}

	public PageResult queryForList(QueryObject qo) {
		Long total = permissionMapper.queryForCount(qo);
		if(total == 0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Permission> rows = permissionMapper.queryForList(qo);
		return new PageResult(total.intValue(), rows);
	}

	@Override
	public List<String> queryByEid(Long id) {
		return permissionMapper.queryByEid(id);
	}
}
