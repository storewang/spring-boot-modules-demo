package com.ai.spring.sofa.test.mogo;

import lombok.Data;
import lombok.ToString;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/6/26
 * @Version 1.0
 **/
@Data
@ToString
public class MogoEditRoomManagerResponse extends MogoApiResponse{
    /** 管家电话 */
    private String phone;
    /** 更新管家姓名 */
    private String updateName;
    /** 更新管家电话 */
    private String updatePhone;
}
