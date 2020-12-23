package com.ai.spring.boot.svc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private Long Id;
    private String userName;
    private String address;
    private Integer age;
}
