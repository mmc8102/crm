package cn.wolfcode.crm2.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Role {
    private Long id;	///角色ID
    private String name;	//角色名称
    private String sn;		//角色编号
    private List<Permission> permissions = new ArrayList<>();
}