package com.wxy.spring.boot.modules.demo.user.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 用户模块配置类
 *
 * @author 石头
 * @Date 2020/12/25
 * @Version 1.0
 **/
@Configuration
@EnableJpaRepositories(basePackages = {"com.wxy.spring.boot.modules.demo.user.repository"})
public class UserConf {
}
