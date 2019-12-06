package com.woniu.netmonitor.util;

import com.woniu.netmonitor.vo.AuthUserInfo;
import com.woniu.netmonitor.vo.JsonResult;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientUtil {

    private WebClient webClient;

    private AuthUserInfo authUserInfo;

    public WebClientUtil() {
        this.webClient = WebClient.create();
    }

    public void setAuthUserInfo(AuthUserInfo authUserInfo){
        this.authUserInfo = authUserInfo;
    }

    public AuthUserInfo getAuthUserInfo(){
        return this.authUserInfo;
    }


    public <T> T webClientGetMethodAsync(String urlPath, Class<T> tClass, Object variableObject) {
        Mono<T> mono;
        if (variableObject != null)
            if (authUserInfo == null)
                mono = webClient.get().uri(urlPath, variableObject).retrieve().bodyToMono(tClass);
            else
                mono = webClient.get().uri(urlPath, variableObject).header(HttpHeaders.AUTHORIZATION, authUserInfo.getToken())
                        .retrieve().bodyToMono(tClass);
        else
            if (authUserInfo == null)
                mono = webClient.get().uri(urlPath).retrieve().bodyToMono(tClass);
            else
                mono = webClient.get().uri(urlPath).header(HttpHeaders.AUTHORIZATION, authUserInfo.getToken())
                        .retrieve().bodyToMono(tClass);
        T resultEntity = mono.block();
        return resultEntity;
    }

    public <T> T webClientGetMethodAsync(String urlPath, Class<T> tClass) throws Exception {
        return webClientGetMethodAsync(urlPath, tClass, null);
    }


    public <T> T webClientPostMethodAsync(String urlPath, Class<T> tClass, Object variableObject) {
        Mono<T> mono;
        if (authUserInfo == null) {
            mono = webClient.post().uri(urlPath).syncBody(variableObject).retrieve().bodyToMono(tClass);
        }else {
            String basicAuth = authUserInfo.getToken();
            mono = webClient.post().uri(urlPath).header(HttpHeaders.AUTHORIZATION, basicAuth).syncBody(variableObject).retrieve().bodyToMono(tClass);
        }

        T resultEntity = mono.block();
        return resultEntity;
    }
}
