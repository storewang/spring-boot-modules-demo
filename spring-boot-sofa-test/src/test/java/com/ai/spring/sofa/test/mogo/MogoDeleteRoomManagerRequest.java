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
public class MogoDeleteRoomManagerRequest implements MogoApiRequest<MogoRoomManagerResponse>{
    /** 管家姓名 */
    private String name;
    /** 管家电话 */
    private String phone;
    @Override
    public String getApiMethodName() {
        return "com.mogoroom.deleteRoomManager";
    }

    @Override
    public Class<MogoRoomManagerResponse> getResponseClass() {
        return MogoRoomManagerResponse.class;
    }
}
