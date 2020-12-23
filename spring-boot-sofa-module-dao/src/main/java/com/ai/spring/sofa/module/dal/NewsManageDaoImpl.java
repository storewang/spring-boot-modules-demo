package com.ai.spring.sofa.module.dal;

import com.ai.spring.sofa.module.bean.NewsDO;
import com.ai.spring.sofa.module.dal.api.NewsManageDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
public class NewsManageDaoImpl implements NewsManageDao{
    @Autowired
    private DataSource dataSource;
    public NewsManageDaoImpl(){
        System.out.printf("----------初始化NewsManageDaoImpl完成------------"+Thread.currentThread().getName());
    }
    @Override
    public int insert(NewsDO newDO) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(String.format(
                "INSERT INTO NewsTable (AUTHOR, TITLE) VALUES('%s', '%s');", newDO.getAuthor(),
                newDO.getTitle()));
    }

    @Override
    public List<NewsDO> query(String author) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format(
                "SELECT * FROM NewsTable WHERE AUTHOR='%s';", author));
        List<NewsDO> answer = new LinkedList<>();
        while (resultSet.next()) {
            NewsDO newDO = new NewsDO();
            newDO.setAuthor(resultSet.getString(2));
            newDO.setTitle(resultSet.getString(3));
            answer.add(newDO);
        }
        return answer;
    }

    @Override
    public void delete(String author) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(String.format("DELETE FROM NewsTable WHERE AUTHOR='%s';", author));
    }
}
