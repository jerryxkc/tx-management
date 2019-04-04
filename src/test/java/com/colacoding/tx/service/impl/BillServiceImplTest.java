package com.colacoding.tx.service.impl;

import com.colacoding.tx.entity.po.Bill;
import com.colacoding.tx.entity.po.BillDetail;
import com.colacoding.tx.entity.po.Goods;
import com.colacoding.tx.service.BillService;
import com.colacoding.tx.service.GoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: jerryXia
 * @Description: 事务场景 1、各自不影响 2、统一回滚
 * @Date: Created in 11:07 AM 2019/4/4
 * @Modified By:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BillServiceImplTest {

    @Autowired
    private BillService billService;

    @Autowired
    private GoodService goodService;

    /**
     * 各自管理事务
     * @throws Exception
     */
    @Test
    public void addBillNormal() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        //good.setStatus(-1);//good保存抛出异常 good回滚 程序终止
        good = billService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(0);
        billDetail = billService.addBillDetail(billDetail);

    }

    /**
     * 各自管理自己事务 good事务抛出异常
     * @throws Exception
     */
    @Test
    public void addBillGoodException() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(-1);//good保存抛出异常 good回滚 程序终止
        good = billService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(0);
        billDetail = billService.addBillDetail(billDetail);

        /**
         * good 正常回滚 程序终止
         * bill  未执行
         * billdetail  未执行
         */
    }



    /**
     * 各自管理自己事务 bill事务抛出异常
     * @throws Exception
     */
    @Test
    public void addBillBillException() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        good = billService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(-1);//bill保存抛出异常 good回滚 程序终止
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(0);
        billDetail = billService.addBillDetail(billDetail);


        /**
         * good 保存正常
         * bill 正常回滚 程序终止
         * billdetail  未执行
         */

    }


    /**
     * 各自管理自己事务 billDetail抛出异常
     * @throws Exception
     */
    @Test
    public void addBillBillDetailException() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        good = billService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(-1);//billDetail保存抛出异常 good回滚 程序终止
        billDetail = billService.addBillDetail(billDetail);

        /**
         * good 保存正常
         * bill 保存正常
         * billdetail  正常回滚 程序终止
         */

    }


    /**
     * 同一个事务,子事务异常
     * @throws Exception
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void addBillTxException() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        good = billService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(-1);//billDetail保存抛出异常 good回滚 程序终止
        billDetail = billService.addBillDetail(billDetail);

        /**
         * good 正常回滚
         * bill 正常回滚
         * billdetail  正常回滚 程序终止
         */

    }


    /**
     * 同一个事务 当前事务异常
     * @throws Exception
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void addBillTxChildException() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        good = billService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(0);//billDetail保存抛出异常 good回滚 程序终止
        billDetail = billService.addBillDetail(billDetail);


        throw new  RuntimeException("主事务抛出异常");

        /**
         * good 正常回滚
         * bill 正常回滚
         * billdetail  正常回滚
         * 程序终止 抛出异常
         */

    }



    /**
     * 同一个事务 不同service当前事务异常
     * @throws Exception
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void addBillTxChild2Exception() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        good = goodService.addGood(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(0);//billDetail保存抛出异常 good回滚 程序终止
        billDetail = billService.addBillDetail(billDetail);


        throw new  RuntimeException("主事务抛出异常");

        /**
         * good 正常回滚
         * bill 正常回滚
         * billdetail  正常回滚
         * 程序终止 抛出异常
         * 跟是不是同一个实例没有关系
         */

    }


    /**
     * 同一个事务 不同goodservice不回滚
     * addGood2 设置独立事务 propagation = Propagation.REQUIRES_NEW
     * @throws Exception
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void addBillTxChild3Exception() throws Exception {

        Goods good = new Goods();
        good.setName("good1");
        good.setPrice(new BigDecimal(100));
        good.setVersion(1);
        good.setStatus(0);
        good = goodService.addGood2(good);

        Bill bill = new Bill();
        bill.setBillNo("good-bill-001");
        bill.setVersion(1);
        bill.setStatus(0);
        bill = billService.addBill(bill);

        BillDetail billDetail =new BillDetail();
        billDetail.setGoodId(good.getId());
        billDetail.setBillId(bill.getId());
        billDetail.setVersion(-100000);
        billDetail.setStatus(0);//billDetail保存抛出异常 good回滚 程序终止
        billDetail = billService.addBillDetail(billDetail);


        throw new RuntimeException("主事务抛出异常");

        /**
         * good 正常回滚
         * bill 正常回滚
         * billdetail  正常回滚
         * 程序终止 抛出异常
         * 跟是不是同一个实例没有关系
         */

    }

}