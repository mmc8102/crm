package cn.wolfcode.crm2.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class Log {
    private Long id;	//ID
    private Employee opuser;	//操作用户
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date optime;	//操作时间
    private String opip;	//登陆IP
    private String function;	//使用功能
    private String params;	//操作参数信息
}