package com.colacoding.tx.service;

import com.colacoding.tx.entity.po.Bill;
import com.colacoding.tx.entity.po.BillDetail;
import com.colacoding.tx.entity.po.Goods;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 11:01 AM 2019/4/4
 * @Modified By:
 */
public interface BillService {

    Goods addGood(Goods goods);

    Bill addBill(Bill bill);

    BillDetail addBillDetail(BillDetail billDetail);

}
