package com.ai.spring.sofa.test;

import com.ai.spring.SpringBootDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/10
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AbstractTestBase {
    public static final Logger LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    @Autowired
    protected TestRestTemplate testRestTemplate;
    protected String urlHttpPrefix;
    @LocalServerPort
    public int definedPort;
    @Before
    public void setUp() {
        urlHttpPrefix = "http://localhost:" + definedPort;
    }
}
