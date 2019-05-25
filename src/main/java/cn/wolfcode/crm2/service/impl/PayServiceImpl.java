package cn.wolfcode.crm2.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Pay;
import cn.wolfcode.crm2.mapper.PayMapper;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.PayQueryObject;
import cn.wolfcode.crm2.query.QueryObject;
import cn.wolfcode.crm2.service.IPayService;
@Service
public class PayServiceImpl implements IPayService {
	@Autowired
	private PayMapper payMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return payMapper.deleteByPrimaryKey(id);
	}

	public int insert(Pay record) {
		return payMapper.insert(record);
	}

	public Pay selectByPrimaryKey(Long id) {
		return payMapper.selectByPrimaryKey(id);
	}

	public List<Pay> selectAll() {
		return payMapper.selectAll();
	}

	public int updateByPrimaryKey(Pay record) {
		return payMapper.updateByPrimaryKey(record);
	}

	public PageResult queryForPage(QueryObject qo) {
		Long total = payMapper.queryForCount(qo);
		if(total == 0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Pay> rows = payMapper.queryForList(qo);
		return new PageResult(total.intValue(), rows);
	}

	public List<Pay> queryForList(){
		PayQueryObject qo = new PayQueryObject();
		return payMapper.queryForList(qo);
	}
	public void insertAll(List<Pay> list) {
		for (Pay pay : list) {
			String username = pay.getEmp().getUsername();
			System.out.println("用户名="+username);
			long eid = payMapper.getEidByUsername(username);
			pay.getEmp().setId(eid);
			payMapper.insert(pay);
		}
	}

}
