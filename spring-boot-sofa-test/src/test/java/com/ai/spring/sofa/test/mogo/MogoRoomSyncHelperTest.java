package com.ai.spring.sofa.test.mogo;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/9/3
 * @Version 1.0
 **/

public class MogoRoomSyncHelperTest {
    public static void main(String[] args) {
        MogoRoomSyncHelper syncService = new MogoRoomSyncHelper();
        syncService.setAccessid("223C35EB7C8C5404A47DA78968F11158");
        syncService.setGateWayUrl("http://oapiflat.mgzf.com/mogoroom-openapi/openapi/flats/%s/%s");
        syncService.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJE/7kd42uHPU8BC635LKaV4PJZBV9Uq8q5BS4RVZRnGkFDpqROCx/xDwUNv5esZAE0VqrIWJtZaijjSGWe5ZVnDOnr11frzNuLSqeCo6GXRKGk1RvL0yQw2Kle5qQPo/ZDGfK7fh+m5sEbjT7A+vCOXp9SH7c7VQk0L3pEmnHrYBmTqniWJxSJ6HL2t8mYbcowkLu0SVrZzzE6Pg5EQSizphBzuqkAytq48AqbipZLGjVYl/Q3gURIw9Z99xRe3sorNmSHbR7yS8xt2mtVhaGw4IasbQCTI8wUJlJ93NcxigYuCOzG+fPd7PUtpqSto0cKNYU69RWcvCFFrhDC+BJAgMBAAECggEAY8SU6Q4GpWnYJ0VYB2nBgEof1e0tItiSO2JjnjaA+8cePJsDbpx27ssRexkHjD4p9we0uQcET2EFG3x1rMPoiAi3I92j1Sd8BD2FXh+K2kpVWHNUqdPAI0DBxHWw6jlOkwfPZ0hKa0Ov0MqxbtzMs8EH4LVp+DwX2O/AuSk4qiYJ2b7Oo5HR96NTb3FMOnrGmAUk7kGcChXk2xspn01HFyh22aBBz46ej321aW/SeUe4JagXiUc5q02IIYjSYX8ZQMo5WsJawYD8h8UtRs05JJzeB/OwVAwz6HEpdSRaTGu17ySliYsVwjJxVJ+MSdGUueu1bVDdOPoku76Wl1haAQKBgQDuBPTpkNYDm+acGKh397+c3Mmlh4shhX6u/JAxVTVH3G+R/omkVRqgQF0S2EPE6uD9ur8U6AKWiohQMQl1yoBl4we7i7eJXtkIlRwArQO585GFWJuDq1tIe9pADeUHIE0wg9vm+Y8oy2mbsLm1HRI0EmSjT5mdnnOGsE9mnNBzwQKBgQCTbvEPidsOxyGwe/NWW5vKboO2HGKPhvz7HRCJ6Gj71E0IxiegA1l/I1q2ECEBJIpYEhkC2FVeuPj7vJqtCXy+2ngSRXk37L/Qx19wbeWYMp8h4JE7HNINITlU8d52benGIX5nCYD8R0wGyety31L1uYWv6EkXo60jlq+o0gFuiQKBgQC81kvkvpc818R3VYzbWUC5V+XXoRj+KGZguFpB+YNDFx2ha5uSOD7eoxH0sIXbxiloxG9zS7s56oDJdo412MpTi3KIFgowP4LbL0m/M4UAU8DBVZJUPXA3qqN2LE/nfnYfJo4hyx8MU43fCq/bhEv76hfUBYQvrBWl512nYIR1QQKBgAeTEJFyzK1Sr7lodNT/+d4JlXy6spmbHs4r7RiYtyQRATAtNgXzhhqRD20BISD05qKls2FBCgnGyQvt4ah4L0+C295ccWDdKfGo/I9DJiOOnjxYVD3MOkJwbYiesIY3GWaTe/IBO/46D37dytno/VMgh+zEq1SqlQ+aA0ZSG765AoGAcbPTELsYt6yF/yTv6an/TZmqVnz58KCLZIwUuDU7M4vJtTh0HjNRkssPIL/c5FS+4MpsWb/MiPugGXPrEyTCsdfFwx2vz/zbybwIVabncazJBopoDlhl8mIqmHn0zYiaNHsZL/6IV/IVOqeIBOG7DhKtbfsAB46bC+n1o38lu90=");
        syncService.setGateWayVersion("1.0.0");

//        MogoAddRoomManagerRequest request = new MogoAddRoomManagerRequest();
//        request.setName("寓团客服");
//        request.setPhone("17132148008");

//        MogoDeleteRoomManagerRequest request = new MogoDeleteRoomManagerRequest();
//        request.setName("上海墨果物业管理有限公司");
//        request.setPhone("13401066567");

        MogoEditRoomManagerRequest request = new MogoEditRoomManagerRequest();
        request.setPhone("17132148008");
        request.setUpdateName("上海墨果物业管理有限公司");
        request.setUpdatePhone("17132148008");

        MogoEditRoomManagerResponse roomResponse = syncService.excute(request);
        System.out.println(roomResponse);
    }
}
