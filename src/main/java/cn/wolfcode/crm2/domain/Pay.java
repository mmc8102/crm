package cn.wolfcode.crm2.domain;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class Pay {
    private Long id;
    private Long sn; //编号
    private BigDecimal pay;
    private Long year;
    private Long month;
    private Employee emp;
}