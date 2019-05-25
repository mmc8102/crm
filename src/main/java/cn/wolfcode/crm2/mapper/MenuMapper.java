package cn.wolfcode.crm2.mapper;

import java.util.List;

import cn.wolfcode.crm2.domain.Menu;

public interface MenuMapper {
	List<Menu> queryForRoot();
}