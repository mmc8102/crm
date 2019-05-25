package cn.wolfcode.crm2.mapper;

import cn.wolfcode.crm2.domain.Pay;
import cn.wolfcode.crm2.query.QueryObject;

import java.util.List;

public interface PayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Pay record);

    Pay selectByPrimaryKey(Long id);

    List<Pay> selectAll();

    int updateByPrimaryKey(Pay record);

	Long queryForCount(QueryObject qo);

	List<Pay> queryForList(QueryObject qo);

	long getEidByUsername(String username);
}