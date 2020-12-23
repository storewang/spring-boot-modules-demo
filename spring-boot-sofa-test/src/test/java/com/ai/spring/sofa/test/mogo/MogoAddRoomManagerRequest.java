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
public class MogoAddRoomManagerRequest implements MogoApiRequest<MogoRoomManagerResponse>{
    /** 管家姓名 */
    private String name;
    /** 管家电话 */
    private String phone;
    /** 公司电话 */
    private String companyTel;
    @Override
    public String getApiMethodName() {
        return "com.mogoroom.addRoomManager";
    }

    @Override
    public Class<MogoRoomManagerResponse> getResponseClass() {
        return MogoRoomManagerResponse.class;
    }
}
