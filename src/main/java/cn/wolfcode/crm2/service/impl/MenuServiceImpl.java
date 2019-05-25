package cn.wolfcode.crm2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm2.domain.Menu;
import cn.wolfcode.crm2.mapper.MenuMapper;
import cn.wolfcode.crm2.service.IMenuService;
@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private MenuMapper menuMapper;
	
	public List<Menu> queryForMenu() {
		return menuMapper.queryForRoot();
	}

}
