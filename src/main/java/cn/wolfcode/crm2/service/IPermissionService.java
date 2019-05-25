package cn.wolfcode.crm2.service;

import java.util.List;

import cn.wolfcode.crm2.domain.Permission;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
    
    PageResult queryForList(QueryObject qo);

	List<String> queryByEid(Long id);
}
