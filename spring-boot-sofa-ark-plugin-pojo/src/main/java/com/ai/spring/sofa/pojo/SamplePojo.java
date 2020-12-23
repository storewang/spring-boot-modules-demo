package com.ai.spring.sofa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/11
 * @Version 1.0
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SamplePojo implements Serializable {
    /**名称*/
    private String name;
    /**用户ID*/
    private Integer userId;
}
