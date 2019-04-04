package com.colacoding.tx.dao;

import com.colacoding.tx.entity.po.BillDetail;
import com.colacoding.tx.entity.po.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 10:33 AM 2019/4/4
 * @Modified By:
 */
public interface BillDetailDao extends JpaRepository<BillDetail,Long> {
}
