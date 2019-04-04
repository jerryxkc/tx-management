package com.colacoding.tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 8:03 AM 2019/3/29
 * @Modified By:
 */
@SpringBootApplication
public class TxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class,args);
    }


}
