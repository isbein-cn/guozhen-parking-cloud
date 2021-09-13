package com.isbein.cloud.common.basic.utils;

import okhttp3.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http客户端工具类
 */
public class HttpUtil {

    private static final OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    /**
     * http get
     * @return
     */
    public static Response get(String urlFormat,Object ... args) throws IOException {
        final Request request = new Request.Builder()
                .url(String.format(urlFormat, args))
                .get()
                .build();
        Call call = httpClient.newCall(request);
        return call.execute();
    }

    /**
     * http get
     * @return
     */
    public static Response getWithHeader(String url,Map<String,String> headers) throws IOException {
        final Request.Builder builder = new Request.Builder()
                .url(url);
        if (headers.size() > 0){
            headers.forEach(builder::addHeader);
        }
        final Request request = builder.get().build();
        Call call = httpClient.newCall(request);
        return call.execute();
    }

    /**
     * post json
     * @param url           提交url
     * @param jsonString     提交数据
     * @return
     */
    public static Response postJson(String url, String jsonString) throws IOException {
        final RequestBody requestBody = RequestBody.create(jsonString,MediaType.get("application/json;charset=UTF-8"));
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return httpClient.newCall(request).execute();
    }

    /**
     * post x-www-URLEncoded
     * @return
     */
    public static Response post(String url,Map<String,String> params) throws IOException{
        return post(url,params,null);
    }

    /**
     * post
     * @return
     */
    public static Response post(String url,Map<String,String> params,Map<String,String> headers)throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String v = URLEncoder.encode(entry.getValue(),"UTF-8");
            stringBuilder.append("&").append(entry.getKey()).append("=").append(v);
        }
        RequestBody requestBody = RequestBody.create(stringBuilder.toString(),MediaType.get("application/x-www-form-urlencoded"));
        Request request = null;
        if (headers != null && headers.size() > 0){
            request = new Request.Builder()
                    .url(url)
                    .headers(Headers.of(headers))
                    .post(requestBody)
                    .build();
        }else{
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }

        return httpClient.newCall(request).execute();
    }

    /**
     * post formData
     * @return
     */
    public static Response postFormData(String url,MultipartBody multipartBody)throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        return httpClient.newCall(request).execute();
    }

    /**
     * 提交xml
     * @param url   url
     * @param xml   xml
     * @return
     * @throws IOException
     */
    public static Response postXml(String url,String xml)throws IOException{
        final RequestBody requestBody = RequestBody.create(xml,MediaType.get("text/xml"));
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return httpClient.newCall(request).execute();
    }

}