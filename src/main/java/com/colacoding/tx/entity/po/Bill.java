package com.colacoding.tx.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 10:17 AM 2019/4/4
 * @Modified By:
 */
@Entity
@Table(name = "t_bill")
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String billNo;

    private Integer status;

    @Version
    private Integer version;

    public Bill() {
    }
}
