package com.colacoding.tx.dao;

import com.colacoding.tx.entity.po.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 10:33 AM 2019/4/4
 * @Modified By:
 */
public interface GoodsDao extends JpaRepository<Goods,Long> {
}
