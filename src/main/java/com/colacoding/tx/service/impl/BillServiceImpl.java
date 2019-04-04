package com.colacoding.tx.service.impl;

import com.colacoding.tx.dao.BillDao;
import com.colacoding.tx.dao.BillDetailDao;
import com.colacoding.tx.dao.GoodsDao;
import com.colacoding.tx.entity.po.Bill;
import com.colacoding.tx.entity.po.BillDetail;
import com.colacoding.tx.entity.po.Goods;
import com.colacoding.tx.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 11:03 AM 2019/4/4
 * @Modified By:
 */
@Component
public class BillServiceImpl implements BillService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private BillDao billDao;

    @Autowired
    private BillDetailDao billDetailDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Goods addGood(Goods goods) {
        goods = goodsDao.save(goods);
        if(goods.getStatus()==null||goods.getStatus()<0){
            throw new RuntimeException("抛出异常");
        }
        return goods;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Bill addBill(Bill bill) {
        bill = billDao.save(bill);
        if(bill.getStatus()==null||bill.getStatus()<0){
            throw new RuntimeException("抛出异常");
        }

        return bill;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BillDetail addBillDetail(BillDetail billDetail) {
        billDetail = billDetailDao.save(billDetail);
        if(billDetail.getStatus()==null||billDetail.getStatus()<0){
            throw new RuntimeException("抛出异常");
        }
        return billDetail;
    }

}
