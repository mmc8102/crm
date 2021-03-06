package cn.wolfcode.crm2.mapper;

import java.util.List;

import cn.wolfcode.crm2.domain.Log;
import cn.wolfcode.crm2.query.QueryObject;

public interface LogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    Log selectByPrimaryKey(Long id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);

	Long queryForCount(QueryObject qo);

	List<Log> queryForList(QueryObject qo);
}