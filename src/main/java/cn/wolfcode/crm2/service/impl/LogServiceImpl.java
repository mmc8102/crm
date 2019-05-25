package cn.wolfcode.crm2.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Log;
import cn.wolfcode.crm2.mapper.LogMapper;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;
import cn.wolfcode.crm2.service.ILogService;
@Service
public class LogServiceImpl implements ILogService {
	@Autowired
	private LogMapper logMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return logMapper.deleteByPrimaryKey(id);
	}

	public int insert(Log record) {
		return logMapper.insert(record);
	}

	public Log selectByPrimaryKey(Long id) {
		return logMapper.selectByPrimaryKey(id);
	}

	public List<Log> selectAll() {
		return logMapper.selectAll();
	}

	public int updateByPrimaryKey(Log record) {
		return logMapper.updateByPrimaryKey(record);
	}

	public PageResult queryForList(QueryObject qo) {
		Long total = logMapper.queryForCount(qo);
		if(total == 0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Log> rows = logMapper.queryForList(qo);
		return new PageResult(total.intValue(), rows);
	}

}
