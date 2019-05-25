package cn.wolfcode.crm2.mapper;

import cn.wolfcode.crm2.domain.Role;
import cn.wolfcode.crm2.query.QueryObject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	Long queryForCount(QueryObject qo);

	List<Role> queryForList(QueryObject qo);
	
	void insertRelation(@Param("rid")Long rid, @Param("pid")Long pid);

	void deletePermissionRelation(Long id);

	void deleteEmployeeRelation(Long id);
}