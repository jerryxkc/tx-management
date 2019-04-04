package com.colacoding.tx.entity.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 10:17 AM 2019/4/4
 * @Modified By:
 */
@Entity
@Table(name = "t_bill_detail")
@Data
public class BillDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long billId;

    private Long goodId;

    private Integer status;

    @Version
    private Integer version;

    public BillDetail() {
    }
}
