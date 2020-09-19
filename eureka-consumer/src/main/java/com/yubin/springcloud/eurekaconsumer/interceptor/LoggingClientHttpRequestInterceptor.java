package com.yubin.springcloud.eurekaconsumer.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 自定义RestTemplate请求和响应拦截器
 *
 * @author YUBIN
 * @create 2020-09-19
 */
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        System.out.println("拦截啦！！！");
        System.out.println(request.getURI());

        ClientHttpResponse response = execution.execute(request, body);

        System.out.println(response.getHeaders());
        return response;
    }
}
