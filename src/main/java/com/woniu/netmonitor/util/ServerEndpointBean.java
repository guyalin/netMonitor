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
}
