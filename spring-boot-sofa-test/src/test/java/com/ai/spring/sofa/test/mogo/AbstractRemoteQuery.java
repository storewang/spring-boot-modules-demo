package com.ai.spring.sofa.test.mogo;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/6/20
 * @Version 1.0
 **/
public class AbstractRemoteQuery {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    /**POST请求字符集*/
    protected static final String POST_CHARSET  = "UTF-8";
    /**http client 实例*/
    private CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(getSocketFactory()).build();
    private SSLConnectionSocketFactory getSocketFactory(){
        SSLContext sslContext;
        try{
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
        }catch (Exception e){
            return null;
        }
        return new SSLConnectionSocketFactory(sslContext);
    }
    private TrustManager[] getTrustManager(){
        return new TrustManager[]{
                new X509TrustManager(){

                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        //do nothing
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        //do nothing
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    /**
     * Post请求
     * @brief   Post请求
     * @param   queryUrl
     * @param   params
     * @author  石头
     * @date   2018/10/24
     * @return
     */
    public String remotePost(String queryUrl,Map<String,String> params) throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(queryUrl);
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(2000)
                    .setConnectionRequestTimeout(1000)
                    .setSocketTimeout(8000).build();
            httpPost.setConfig(config);
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            if (params!=null){
                param = params.entrySet().stream().map(entity ->{
                    BasicNameValuePair valuePair = new BasicNameValuePair(entity.getKey(),entity.getValue());
                    return valuePair;
                }).collect(Collectors.toList());
            }

            UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(param, POST_CHARSET);
            httpPost.setEntity(postEntity);
            HttpResponse httpResponse = client.execute(httpPost);
            String json =  EntityUtils.toString(httpResponse.getEntity(),POST_CHARSET);
            return json;
        }catch (IOException e){
            throw  e;
        }finally {
            //释放连接
            if (httpPost!=null){
                httpPost.releaseConnection();
            }
        }
    }

    /**
     * 关闭httpClient
     * @brief   关闭httpClient
     * @author  石头
     * @date   2018/10/24
     * @return
     */
    public void closeClient(){
        try {
            client.close();
        } catch (IOException e) {
            log.info("http client close err.",e);
        }
    }
}
