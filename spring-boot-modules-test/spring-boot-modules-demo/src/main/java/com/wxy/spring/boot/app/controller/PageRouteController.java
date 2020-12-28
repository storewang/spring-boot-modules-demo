package com.wxy.spring.boot.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面控制
 *
 * @author 石头
 * @Date 2020/12/28
 * @Version 1.0
 **/
@Controller
public class PageRouteController {
    @GetMapping(value = {"/home","/"})
    public String home(){
        return "index.html";
    }

    @GetMapping(value = {"/pay"})
    public String payback(){
        return "paycallback.html";
    }
}
