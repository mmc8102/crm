package cn.wolfcode.crm2.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Menu {
    private Long id;
    private String text;
    private String iconCls;
    private Boolean checked;
    private String state;
    private String attributes;
    private String function;
    private List<Menu> children = new ArrayList<>();
}