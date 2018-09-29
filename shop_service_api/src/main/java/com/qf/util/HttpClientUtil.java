package com.qf.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {


    /**
     * httpclient工具方法 - 传递json字符串
     * http:
     * get传参, url?key=value
     * post传参， url?key=value, 请求体：key=value&key=value
     *
     * 传递json：
     * 请求体：json
     */
    public static String sendJsonPost(String url, String json){
        //1、获得HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            //2、设置post请求
            HttpPost httpPost = new HttpPost(url);
            
            httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
            httpPost.setEntity(new StringEntity(json, "utf-8"));



            //3、发送post请求
            CloseableHttpResponse response = httpClient.execute(httpPost);

            //4、获得响应体
            HttpEntity entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity);

            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 发送post请求并且自由设置参数和请求头
     * @return
     */
    public static String sendPostParamsAndHeader(String url, Map<String, String> params, Map<String, String> header){
        //1、获得HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            //2、设置post请求
            HttpPost httpPost = new HttpPost(url);

            //设置请求头
            if(header != null){
                for(Map.Entry<String, String> head : header.entrySet()){
                    System.out.println(head.getKey() + "  " + head.getValue());
                    httpPost.addHeader(head.getKey(), head.getValue());
                }
            }

            //设置请求体
            List<NameValuePair> paramsList = new ArrayList<>();
            if(params != null){
                for(Map.Entry<String, String> param : params.entrySet()){
                    paramsList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "utf-8"));


            //3、发送post请求
            CloseableHttpResponse response = httpClient.execute(httpPost);

            //4、获得响应体
            HttpEntity entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity);

            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public static void main(String[] args) throws Exception {
        //1、获得HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //2、get
        HttpGet get = new HttpGet("http://www.baidu.com");

        //3、执行get请求
        CloseableHttpResponse response = httpClient.execute(get);

        //4、获得响应体
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024 * 10];
        int len;
        while((len = in.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }

        byte[] bytes = out.toByteArray();
        System.out.println("获得的响应内容为：" + new String(bytes, "utf-8"));

        in.close();
        out.close();

    }
}
