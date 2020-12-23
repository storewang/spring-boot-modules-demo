package com.ai.spring.sofa.module.dal.api;

import com.ai.spring.sofa.module.bean.NewsDO;

import java.sql.SQLException;
import java.util.List;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
public interface NewsManageDao {
    int insert(NewsDO newDO) throws SQLException;
    List<NewsDO> query(String author) throws SQLException;
    void delete(String author) throws SQLException;
}
