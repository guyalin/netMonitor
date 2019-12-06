package com.woniu.netmonitor.configuration;

import com.woniu.netmonitor.util.HttpTransferBean;
import com.woniu.netmonitor.util.LocalPropertyUtil;
import com.woniu.netmonitor.util.ServerEndpointBean;
import com.woniu.netmonitor.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
public class CommonBeanConfiguration {
    //声明 bean
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "webClientBean")
    public WebClientUtil webClient(){
        return new WebClientUtil();
    }

    @Bean(name = "localPropertyBean")
    public LocalPropertyUtil localPropertyUtil(){
        LocalPropertyUtil propertyUtil = new LocalPropertyUtil(new Properties(), "formProperty.txt");
        return propertyUtil;
    }

    @Bean(name = "httpTransferBean")
    public HttpTransferBean httpTransferBean(@Value("${net.server.endpoint.rootpath}") String rootUrlPath,
                                             @Value("${net.server.endpoint.urlentities}") String netUrlEntityServerEndpoint,
                                             @Value("${net.server.endpoint.articlerecords}") String netArticleRecordServerEndPoint,
                                             @Value("${net.server.endpoint.articlepersistence}") String netArticlePersistenceServerEndPoint,
                                             @Qualifier("restTemplate") RestTemplate restTemplate){
        HttpTransferBean httpTransferBean = new HttpTransferBean(rootUrlPath, netUrlEntityServerEndpoint, netArticleRecordServerEndPoint, netArticlePersistenceServerEndPoint, restTemplate);
        return httpTransferBean;
    }

    @Bean(name = "serverEndpointBean")
    public ServerEndpointBean serverEndpointBean(@Value("${net.server.endpoint.rootpath}") String rootUrlPath,
                                                 @Value("${net.server.endpoint.rootpath}") String serverRootPathEndpoint,
                                                 @Value("${net.server.endpoint.neturlsave}") String netUrlSaveEndpoint,
                                                 @Value("${net.server.endpoint.neturltest}") String netUrlTestEndpoint,
                                                 @Value("${net.server.endpoint.login}") String netServerLoginEndPoint,
                                                 @Value("${net.server.endpoint.urlentities}") String netUrlEntityServerEndpoint,
                                                 @Value("${net.server.endpoint.articlerecords}") String netArticleRecordServerEndPoint,
                                                 @Value("${net.server.endpoint.articlepersistence}") String netArticlePersistenceServerEndPoint
                                                 ){
        ServerEndpointBean serverEndpointBean = new ServerEndpointBean(
                rootUrlPath,
                serverRootPathEndpoint,
                netUrlSaveEndpoint,
                netUrlTestEndpoint,
                netServerLoginEndPoint,
                netUrlEntityServerEndpoint,
                netArticleRecordServerEndPoint,
                netArticlePersistenceServerEndPoint);
        return serverEndpointBean;
    }

}
