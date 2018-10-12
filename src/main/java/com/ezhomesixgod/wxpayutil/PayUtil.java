package com.ezhomesixgod.wxpayutil;

import com.ezhomesixgod.wxconfig.WxPayConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;

/**
 * @author renyang
 * @description
 * @createTime 2018-10-12 11:05
 */
public class PayUtil {
    public static String ssl(String url,String data){
        StringBuffer message = new StringBuffer();
        try {
            //  KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            SSLContext sslcontext = initSSLContext();
            SSLConnectionSocketFactory sslsf =
                    new SSLConnectionSocketFactory(sslcontext,
                            new String[] { "TLSv1" },
                            null,
                            SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().
                    setSSLSocketFactory(sslsf).build();

            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));

            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        message.append(text);
                    }
                }
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return message.toString();
    }

    public static SSLContext initSSLContext() {
        SSLContext sslContext=null;
        InputStream inputStream = null;
        try {
            inputStream =PayUtil.class.getClassLoader().getResourceAsStream(WxPayConfig.KeyPath);
        } catch (Exception e) {
            throw new RuntimeException("证书不正确！", e);
        }

        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            char[] partnerId2charArray = WxPayConfig.mchId.toCharArray();
            keystore.load(inputStream, partnerId2charArray);
            sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("秘钥不正确！", e);
        } finally {

        }
    }
}
