package com.ai.spring.sofa.module.api;

import java.sql.SQLException;
import java.util.List;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/

public interface NewsReadService<T> {
    List<T> read(String author) throws SQLException;

}
