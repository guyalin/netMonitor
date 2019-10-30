package com.woniu.netmonitor.util;

import com.woniu.netmonitor.vo.JsonResult;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientUtil {

    private WebClient webClient;

    public WebClientUtil() {
        this.webClient = WebClient.create();
    }

    public <T> T webClientGetMethodAsync(String urlPath, Class<T> tClass, Object variableObject) {
        Mono<T> mono;
        if (variableObject != null)
            mono = webClient.get().uri(urlPath, variableObject).retrieve().bodyToMono(tClass);
        else
            mono = webClient.get().uri(urlPath).retrieve().bodyToMono(tClass);
        T resultEntity = mono.block();
        return resultEntity;
    }

    public <T> T webClientGetMethodAsync(String urlPath, Class<T> tClass) throws Exception {
        return webClientGetMethodAsync(urlPath, tClass, null);
    }

    public <T> T webClientPostMethodAsync(String urlPath, Class<T> tClass, Object variableObject) {
        Mono<T> mono;
        mono = webClient.post().uri(urlPath).syncBody(variableObject).retrieve().bodyToMono(tClass);
        T resultEntity = mono.block();
        return resultEntity;
    }
}
