package com.ai.spring.controller;

import com.ai.spring.sofa.module.api.NewsReadService;
import com.ai.spring.sofa.module.api.NewsWriteService;
import com.ai.spring.sofa.module.bean.NewsDO;
import com.ai.spring.vo.Result;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
@RestController
public class SampleRestController {
    @SofaReference
    private DataSource dataSource;

    @SofaReference
    private NewsReadService newReadService;

    @SofaReference
    private NewsWriteService newWriteService;

    @RequestMapping("/create")
    public Result create() {
        Result result = new Result();
        try {
            Connection cn = dataSource.getConnection();
            Statement st = cn.createStatement();
            st.execute("DROP TABLE IF EXISTS NewsTable;"
                    + "CREATE TABLE NewsTable(ID INT AUTO_INCREMENT, PRIMARY KEY (ID), AUTHOR VARCHAR(50),TITLE VARCHAR(255));");
            result.setSucess(true);
            result.setResult("CREATE TABLE NewsTable(ID INT AUTO_INCREMENT, PRIMARY KEY (ID), AUTHOR VARCHAR(50), TITLE VARCHAR(255))");
        } catch (Throwable throwable) {
            result.setSucess(false);
            result.setResult(throwable.getMessage());
        }
        return result;
    }
    @RequestMapping("/insert/{author}/{title}")
    public Result insert(@PathVariable("author") String author, @PathVariable("title") String title) {
        Result result = new Result();
        try {
            newWriteService.addNews(author, title);
            result.setSucess(true);
        } catch (Throwable throwable) {
            result.setSucess(false);
            result.setResult(throwable.getMessage());
        }
        return result;
    }

    @RequestMapping("/delete/{author}")
    public Result delete(@PathVariable("author") String author) {
        Result result = new Result();
        try {
            newWriteService.deleteNews(author);
            result.setSucess(true);
        } catch (Throwable throwable) {
            result.setSucess(false);
            result.setResult(throwable.getMessage());
        }
        return result;
    }
    @RequestMapping("/query/{author}")
    public Result query(@PathVariable("author") String author) {
        Result result = new Result();
        try {
            List<NewsDO> ret = newReadService.read(author);
            result.setSucess(true);
            result.setCount(ret.size());
            StringBuffer sbf = new StringBuffer();
            int i = 0;
            for (NewsDO newDO : ret) {
                if (i>0){
                    sbf.append("<br />");
                }
                sbf.append(String.valueOf(++i) + " == > ").append(newDO.getAuthor() + "-" + newDO.getTitle());
            }
            result.setResult(sbf.toString());
        } catch (Throwable throwable) {
            result.setSucess(false);
            result.setResult(throwable.getMessage());
        }
        return result;
    }
    @RequestMapping("/json")
    public String sampleController() {
        return "zhangsan";
    }


}
