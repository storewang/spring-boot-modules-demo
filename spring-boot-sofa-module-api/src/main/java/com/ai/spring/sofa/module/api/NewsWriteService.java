package com.ai.spring.sofa.module.api;

import java.sql.SQLException;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/

public interface NewsWriteService {
    int addNews(String author, String title) throws SQLException;
    void deleteNews(String author) throws SQLException;
}
