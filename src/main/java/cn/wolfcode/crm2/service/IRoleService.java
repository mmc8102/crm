package cn.wolfcode.crm2.service;

import java.util.List;

import cn.wolfcode.crm2.domain.Role;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	PageResult queryForPage(QueryObject qo);

}
