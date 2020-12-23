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
public class MogoEditRoomManagerRequest implements MogoApiRequest<MogoEditRoomManagerResponse>{
    /** 管家电话 */
    private String phone;

    /** 更新管家姓名 */
    private String updateName;
    /** 更新管家电话 */
    private String updatePhone;
    /** 公司电话 */
    private String companyTel;
    @Override
    public String getApiMethodName() {
        return "com.mogoroom.updateRoomManager";
    }

    @Override
    public Class<MogoEditRoomManagerResponse> getResponseClass() {
        return MogoEditRoomManagerResponse.class;
    }
}
