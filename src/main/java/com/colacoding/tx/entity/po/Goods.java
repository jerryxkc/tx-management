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
@Table(name = "t_goods")
@Data
public class Goods {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String goodDesc;

    private BigDecimal price;

    private Integer status;

    @Version
    private Integer version;

    public Goods() {
    }
}
