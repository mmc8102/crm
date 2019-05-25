package cn.wolfcode.crm2.service;

import java.util.List;

import cn.wolfcode.crm2.domain.Log;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;

public interface ILogService {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    Log selectByPrimaryKey(Long id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);

	PageResult queryForList(QueryObject qo);

}
