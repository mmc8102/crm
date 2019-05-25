package cn.wolfcode.crm2.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class Employee {
    private Long id;
    private String username;
    private String realname;
    private String password;
    private String tel;
    private String email;
    private Department dept;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputtime;
    private Boolean state;
    private Boolean admin;
    private List<Role> roles = new ArrayList<>();
    
    
}