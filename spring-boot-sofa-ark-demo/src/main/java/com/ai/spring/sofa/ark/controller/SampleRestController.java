package com.ai.spring.sofa.ark.controller;

import com.ai.spring.sofa.ark.module.Hessian3Service;
import com.ai.spring.sofa.ark.module.Hessian4Service;
import com.ai.spring.sofa.pojo.SamplePojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/11
 * @Version 1.0
 **/
@RestController
public class SampleRestController {
    @RequestMapping("/hello-hessian3")
    public String hessian3() throws IOException {
        SamplePojo samplePoJo = new SamplePojo("Hello, hessian3.",1);
        Hessian3Service hessian3Service = new Hessian3Service();
        byte[] bytes = hessian3Service.serialize(samplePoJo);
        Object pojo = hessian3Service.deserialize(bytes);
        return pojo.toString();
    }
    @RequestMapping("/hello-hessian4")
    public String hessian4() throws IOException {
        SamplePojo samplePoJo = new SamplePojo("Hello, hessian4.",2);
        Hessian4Service hessian4Service = new Hessian4Service();
        byte[] bytes = hessian4Service.serialize(samplePoJo);
        Object pojo = hessian4Service.deserialize(bytes);
        return pojo.toString();
    }
}
