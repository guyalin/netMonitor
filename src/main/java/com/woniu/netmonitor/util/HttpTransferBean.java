package com.woniu.netmonitor.util;

import com.woniu.netmonitor.vo.JsonResult;
import com.woniu.netmonitor.vo.NetInfoQueryParamVo;
import lombok.Data;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Data
public class HttpTransferBean {

    private String netUrlEntityServerEndpoint;
    private String netArticleRecordServerEndPoint;
    private String netArticlePersistenceServerEndPoint;

    private String rootUrlPath;

    private RestTemplate restTemplate;

    public HttpTransferBean(String rootUrlPath,
                            String netUrlEntityServerEndpoint,
                            String netArticleRecordServerEndPoint,
                            String netArticlePersistenceServerEndPoint,
                            RestTemplate restTemplate) {
        this.rootUrlPath = rootUrlPath;
        this.netUrlEntityServerEndpoint = netUrlEntityServerEndpoint;
        this.netArticleRecordServerEndPoint = netArticleRecordServerEndPoint;
        this.netArticlePersistenceServerEndPoint = netArticlePersistenceServerEndPoint;
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> doGetRequestMapping(Class<T> tClass, String url, Map<String, Object> urlVariables) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, tClass, urlVariables);

        return responseEntity;
    }

    public <T> ResponseEntity<T> doGetRequestMapping(Class<T> tClass, String url) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, tClass);
        return responseEntity;
    }

    public JSONObject doGetRequestMapping(String url) throws Exception{
        JSONObject resultEntity = restTemplate.getForObject(url, JSONObject.class);
        return resultEntity;
    }

    public JSONObject doPostRequestMapping(String url, NetInfoQueryParamVo infoQueryParamVo) throws Exception{
        JSONObject resultEntity = restTemplate.postForObject(url,infoQueryParamVo,JSONObject.class);
        return resultEntity;
    }


}
