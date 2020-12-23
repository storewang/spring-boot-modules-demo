package com.ai.spring.sofa.test.mogo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/6/20
 * @Version 1.0
 **/
@Data
@ToString
public abstract class MogoApiResponse implements Serializable {
    /**错误代码*/
    private String errorCode;
    /**错误信息*/
    private String errorMessage;

}
