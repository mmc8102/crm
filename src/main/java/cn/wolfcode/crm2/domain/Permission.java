package cn.wolfcode.crm2.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Permission {
    private Long id;	//权限ID
    private String name;	//权限名
    private String resource;	//
}