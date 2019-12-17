package com.woniu.netmonitor.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerEndpointBean {

    private String rootUrlPath;
    private String serverRootPathEndpoint;
    private String netUrlSaveEndpoint;
    private String netUrlTestEndpoint;
    private String netServerLoginEndPoint;
    private String netUrlEntityServerEndpoint;
    private String netArticleRecordServerEndPoint;
    private String netArticlePersistenceServerEndPoint;
    private String netLabelAddOrUpdEndpoint;
    private String netLabelQueryEndpoint;
    private String netLabelDelEndpoint;

    public String getEndpointWithRootUrl(String baseUrl, String endpoint){
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append(rootUrlPath).append(endpoint);
        return sb.toString();
    }
}
