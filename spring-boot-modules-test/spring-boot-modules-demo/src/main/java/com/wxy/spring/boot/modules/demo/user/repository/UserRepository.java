package com.wxy.spring.boot.modules.demo.user.repository;

import com.wxy.spring.boot.modules.demo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户数据库操作
 *
 * @author 石头
 * @Date 2020/12/25
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {
}
