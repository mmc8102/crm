package cn.wolfcode.crm2.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Department {
    private Long id;	//ID
    private String sn;	//部门编号
    private String name;	//部门名称
    private Employee manager;	//部门经理
    private Department parent;	//上级部门
    private Boolean state;	//状态
}