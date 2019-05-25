package cn.wolfcode.crm2.service;

import java.util.List;

import cn.wolfcode.crm2.domain.Pay;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.QueryObject;

public interface IPayService {
    int deleteByPrimaryKey(Long id);

    int insert(Pay record);

    Pay selectByPrimaryKey(Long id);

    List<Pay> selectAll();

    int updateByPrimaryKey(Pay record);

	PageResult queryForPage(QueryObject qo);

	List<Pay> queryForList();
	
	void insertAll(List<Pay> list);

}
