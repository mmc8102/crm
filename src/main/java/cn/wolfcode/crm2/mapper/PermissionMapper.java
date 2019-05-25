package cn.wolfcode.crm2.mapper;

import cn.wolfcode.crm2.domain.Permission;
import cn.wolfcode.crm2.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

	Long queryForCount(QueryObject qo);

	List<Permission> queryForList(QueryObject qo);

	List<String> queryByEid(Long id);
}