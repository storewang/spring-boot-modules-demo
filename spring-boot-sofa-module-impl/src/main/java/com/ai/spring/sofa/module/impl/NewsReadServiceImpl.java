package com.ai.spring.sofa.module.impl;

import com.ai.spring.sofa.module.api.NewsReadService;
import com.ai.spring.sofa.module.bean.NewsDO;
import com.ai.spring.sofa.module.dal.api.NewsManageDao;
import com.alipay.sofa.runtime.api.annotation.SofaReference;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
public class NewsReadServiceImpl implements NewsReadService<NewsDO> {
    @SofaReference
    private NewsManageDao newManageDao;
    public NewsReadServiceImpl(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
        System.out.printf("----------初始化NewsReadServiceImpl完成------------"+Thread.currentThread().getName());
    }
    @Override
    public List<NewsDO> read(String author) throws SQLException {
        try {
            return newManageDao.query(author);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw ex;
        }
    }
}
