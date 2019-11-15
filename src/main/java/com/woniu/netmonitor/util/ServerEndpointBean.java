package com.woniu.netmonitor.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerEndpointBean {

    private String serverRootPathEndpoint;
    private String netUrlSaveEndpoint;
    private String netUrlTestEndpoint;
}
