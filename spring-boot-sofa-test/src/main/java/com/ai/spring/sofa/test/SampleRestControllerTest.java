package com.ai.spring.sofa.test;

import com.ai.spring.vo.Result;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
public class SampleRestControllerTest extends AbstractTestBase{
    @Test
    public void testRequestJson() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(urlHttpPrefix
                        + "/json",
                String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        String responseBody = responseEntity.getBody();
        LOGGER.info(responseBody);
        Assert.assertTrue(responseBody.contains("zhangsan"));
    }
    @Test
    public void testQuery(){
        ResponseEntity<Result> responseEntity = testRestTemplate.getForEntity(urlHttpPrefix + "/query/zhangsan",Result.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Result responseBody = responseEntity.getBody();
        LOGGER.info("rtnCode={}, result={} ",responseBody.getSucess(),responseBody.getResult());
        Assert.assertTrue(responseBody.getSucess());


    }
}
