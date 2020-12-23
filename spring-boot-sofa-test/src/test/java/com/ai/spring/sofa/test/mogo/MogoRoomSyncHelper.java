package com.ai.spring.sofa.test.mogo;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通过openAPi同步房源操作
 *
 * @author 石头
 * @Date 2019/6/20
 * @Version 1.0
 **/
@Service("mogoRoomSyncHelper")
@Slf4j
@Setter
public class MogoRoomSyncHelper extends AbstractRemoteQuery{
    @Value("${ka.mogo.accessid:615C078FF44C547B1F3E032C5D9B5C60}")
    private String accessid;
    @Value("${ka.mogo.privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAx9clyCJ9zQyd3zC4qhP5YbMChDk5+okpcHSGw+aEudpN3dUDctgaJrRJfE1laZkmM6uWb1hEuAfgew+t5tqKrQIDAQABAkEAsXuuLYIckmkbKScfSsY0nQWcPYyJpq385MhJDGGGDXx3vtAT6Zh7uT/P3Wbjdk49cZqgnVN+MmhREBI9rEPMwQIhAOvlEVJFHrPWc6+92Pbb/hzji8ee0EqP9IFNPhOzjkiRAiEA2N9rCI9eM1ono9dl+tR688ekVJwGP6G3OxgTrsguTl0CIQCEyTYd5HCq9O28IJbwJtf9NQ1q+ffv8ObGnk9yUaGnMQIgSTVqITKcXwNg/DrbDwgpFR/ghjtBFLvXaZhgFJE4qHUCIHJmsGZLZ1TMGfidB6BZbQq2KGdWU5bMoDB8jHKZFsyk}")
    private String privateKey;
    @Value("${ka.mogo.gateWayUrl:http://test.callback.mgzf.com/60_120_8086/mogoroom-openapi/openapi/flats/%s/%s}")
    private String gateWayUrl;
    @Value("${ka.mogo.gateWayVersion:1.0.0}")
    private String gateWayVersion;
    @Value("${ka.mogo.defaultSign:}")
    private String defaultSign;

    @PreDestroy
    public void destroy(){
        closeClient();
    }

    public  <T extends MogoApiResponse,M extends MogoApiRequest<T>> T excute(M request){
        try{
            log.info("-------------------------------");
            log.info("-----------OPENAPI请求开始------");
            log.info("-----------请求参数：{}---------",request);
            log.info("-------------------------------");
            // 获取请求文本
            String params = getParams(request);
            // base64编码后的请求文本
            String base64Params = getBase64Params(params);
            // 签名后的请求文本
            String sign = getSignParams(params);

            // 拼装参数
            Map<String,String> paramsMap = new HashedMap(10);
            paramsMap.put("accessid", accessid);
            paramsMap.put("charset", POST_CHARSET);
            paramsMap.put("sign", sign);
            paramsMap.put("params", base64Params);

            log.info("-----------params：{}--------",params);
            log.info("-----------base64Params：{}--",base64Params);
            log.info("-----------sign：{}----------",sign);

            String queryString = String.format(gateWayUrl,gateWayVersion,request.getApiMethodName());
            String result = remotePost(queryString,paramsMap);

            log.info("-------------------------------");
            log.info("-----------OPENAPI请求结束------");
            log.info("------------queryString:{}------",queryString);
            log.info("------------result:{}-----------",result);

            return GsonUtil.create().fromJson(result,request.getResponseClass());
        }catch (Exception e){
            log.info("-------------------------------");
            log.info("-----------OPENAPI请求失败------",e);

            return buildErrResult(request,e,null);
        }
    }

    public  <T extends MogoApiResponse,M extends MogoApiRequest<T>> T buildErrResult(M request,Exception e,String msg){
        String errorMsg  = "客户端错误";
        if (e!=null && !StringUtils.isEmpty(e.getMessage())){
            errorMsg = e.getMessage();
        }else if (msg !=null){
            errorMsg = msg;
        }
        String json = "{\"errorCode\":\"S00001\",\"errorMessage\":\""+errorMsg+"\"}";
        return GsonUtil.create().fromJson(json,request.getResponseClass());
    }
    private String getSignParams(String params) throws Exception{
        if (!StringUtils.isEmpty(defaultSign)){
            return defaultSign;
        }else {
            byte[] keyBytes = Base64Util.base64ToByteArray(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey2);
            signature.update(params.getBytes(POST_CHARSET));
            return Base64Util.byteArrayToBase64(signature.sign());
        }
    }
    private String getBase64Params(String params) throws UnsupportedEncodingException {
        return Base64Util.byteArrayToBase64(params.getBytes(POST_CHARSET));
    }

    private String getParams(MogoApiRequest request) throws Exception{
        Map<String, String> params = ClassUtil.beanToMap(request);

        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        final AtomicBoolean hasParam = new AtomicBoolean(false);
        params.entrySet().stream().forEach(en -> {
            String name  = en.getKey();
            String value = en.getValue();
            if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)){
                if (hasParam.get()){
                    query.append("&");
                }else {
                    hasParam.compareAndSet(false,true);
                }
                query.append(name).append("=").append(value);

            }
        });

        return query.toString();
    }

}
