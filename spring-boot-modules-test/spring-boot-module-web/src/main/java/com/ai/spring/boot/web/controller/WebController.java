package com.ai.spring.boot.web.controller;

import com.ai.spring.boot.plugin.annotations.Reference;
import com.ai.spring.boot.svc.api.UserInfoSvc;
import com.ai.spring.boot.svc.vo.UserInfoDTO;
import com.ai.spring.boot.web.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
@Controller
public class WebController {
    @Reference("userInfoSvcImpl")
    private UserInfoSvc userInfoSvc;

    @RequestMapping(path = "/",method= RequestMethod.GET)
    @ResponseBody
    public ResultVo index(){
        return new ResultVo(1001,"Hello world !!");
    }

    @RequestMapping(path = "/save",method= RequestMethod.POST)
    @ResponseBody
    public ResultVo save(String userName,String address,Integer age){
        Long userId = userInfoSvc.save(new UserInfoDTO(100001L,userName,address,age));
        return new ResultVo(1001,userId);
    }

    @RequestMapping(path = "/find",method= RequestMethod.GET)
    @ResponseBody
    public ResultVo findByUserName(String userName){
        UserInfoDTO userInfoDTO = userInfoSvc.findByUserName(userName);
        if (userInfoDTO!=null){
            return new ResultVo(1001,userInfoDTO);
        }
        return new ResultVo(0001,"没有查到用户信息");
    }
}
