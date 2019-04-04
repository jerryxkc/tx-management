package com.colacoding.tx.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: jerryXia
 * @Description:
 * @Date: Created in 10:13 AM 2019/4/4
 * @Modified By:
 */
@Configuration
@EntityScan("com.colacoding.tx.entity.po")
@EnableJpaRepositories(basePackages = "com.colacoding.tx.dao")
public class EntityConfig {

}
