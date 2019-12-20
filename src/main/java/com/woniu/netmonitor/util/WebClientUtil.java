package com.woniu.netmonitor.util;

import com.woniu.netmonitor.vo.AuthUserInfo;
import com.woniu.netmonitor.vo.JsonResult;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientUtil {

    private WebClient webClient;
    private String serverRootPath;

    private AuthUserInfo authUserInfo;

    private String baseUrl;

    public WebClientUtil() {
        this.webClient = WebClient.create();
    }

    public void setServerRootPath(String serverRootPath){
        this.serverRootPath = serverRootPath;
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo){
        this.authUserInfo = authUserInfo;
    }

    public AuthUserInfo getAuthUserInfo(){
        return this.authUserInfo;
    }

    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    private String getUrlPath(String endpointPath){
        StringBuilder sb = new StringBuilder(baseUrl);
        return sb.append(serverRootPath).append(endpointPath).toString();
    }

    public <T> T webClientGetMethodAsync(String endpointPath, Class<T> tClass, Object variableObject) {
        Mono<T> mono;
        String urlPathStr = getUrlPath(endpointPath);
        if (variableObject != null)
            if (authUserInfo == null)
                mono = webClient.get().uri(urlPathStr, variableObject).retrieve().bodyToMono(tClass);
            else
                mono = webClient.get().uri(urlPathStr, variableObject).header(HttpHeaders.AUTHORIZATION, authUserInfo.getToken())
                        .retrieve().bodyToMono(tClass);
        else
            if (authUserInfo == null)
                mono = webClient.get().uri(urlPathStr).retrieve().bodyToMono(tClass);
            else
                mono = webClient.get().uri(urlPathStr).header(HttpHeaders.AUTHORIZATION, authUserInfo.getToken())
                        .retrieve().bodyToMono(tClass);
        T resultEntity = mono.block();
        return resultEntity;
    }

    public <T> T webClientGetMethodAsync(String endpointPath, Class<T> tClass) {
        return webClientGetMethodAsync(endpointPath, tClass, null);
    }


    public <T> T webClientPostMethodAsync(String endpointPath, Class<T> tClass, Object variableObject) {
        Mono<T> mono;
        String urlPathStr = getUrlPath(endpointPath);
        if (authUserInfo == null) {
            mono = webClient.post().uri(urlPathStr).syncBody(variableObject).retrieve().bodyToMono(tClass);
        }else {
            String basicAuth = authUserInfo.getToken();
            mono = webClient.post().uri(urlPathStr).header(HttpHeaders.AUTHORIZATION, basicAuth).syncBody(variableObject).retrieve().bodyToMono(tClass);
        }

        T resultEntity = mono.block();
        return resultEntity;
    }

    public <T> T webClientDeleteMethodAsync(String endpointPath, Class<T> tClass, Object variableObject){
        Mono<T> mono;
        String urlPathStr = getUrlPath(endpointPath);
        if (authUserInfo == null) {
            mono = webClient.delete().uri(urlPathStr,variableObject).retrieve().bodyToMono(tClass);
        }else {
            String basicAuth = authUserInfo.getToken();
            mono = webClient.delete().uri(urlPathStr,variableObject).header(HttpHeaders.AUTHORIZATION, basicAuth).retrieve().bodyToMono(tClass);
        }

        T resultEntity = mono.block();
        return resultEntity;
    }
}
