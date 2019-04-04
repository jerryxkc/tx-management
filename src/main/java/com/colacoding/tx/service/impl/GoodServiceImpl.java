package com.colacoding.tx.service.impl;

import com.colacoding.tx.dao.GoodsDao;
import com.colacoding.tx.entity.po.Goods;
import com.colacoding.tx.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 11:42 AM 2019/4/4
 * @Modified By:
 */
@Component
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodsDao goodsDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Goods addGood(Goods goods) {
        goods = goodsDao.save(goods);
        if(goods.getStatus()==null||goods.getStatus()<0){
            throw new RuntimeException("抛出异常");
        }
        return goods;
    }


    @Transactional(noRollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @Override
    public Goods addGood2(Goods goods) {
        goods = goodsDao.save(goods);
        if(goods.getStatus()==null||goods.getStatus()<0){
            throw new RuntimeException("抛出异常");
        }
        return goods;
    }
}
