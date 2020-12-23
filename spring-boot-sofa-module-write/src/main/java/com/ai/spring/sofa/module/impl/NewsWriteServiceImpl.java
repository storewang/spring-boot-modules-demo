package com.ai.spring.sofa.module.impl;

import com.ai.spring.sofa.module.api.NewsWriteService;
import com.ai.spring.sofa.module.bean.NewsDO;
import com.ai.spring.sofa.module.dal.api.NewsManageDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
public class NewsWriteServiceImpl implements NewsWriteService{
    @Autowired
    private NewsManageDao newManageDao;

    public NewsWriteServiceImpl(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
        System.out.printf("----------初始化NewsWriteServiceImpl完成------------"+Thread.currentThread().getName());
    }
    @Override
    public int addNews(String author, String title) throws SQLException {
        try {
            NewsDO newDO = new NewsDO();
            newDO.setAuthor(author);
            newDO.setTitle(title);
            return newManageDao.insert(newDO);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void deleteNews(String author) throws SQLException {
        try {
            newManageDao.delete(author);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw ex;
        }
    }
}
