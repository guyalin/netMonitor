package com.woniu.netmonitor.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class NetInfoQueryParamVo implements Serializable {
    private static final long serialVersionUID = 6498135726704429527L;

    private String area;
    private String netName;
    private String rootUrl;
    private Integer latestDays;
    private Integer descType;  //0时间优先， 1网址类型优先排序
    private String netList; //标签id对应的网页id集合
    private List<String> articleTitleRegex;

    public Map<String, Object> convertToMap(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("area",area);
        paramMap.put("netName",netName);
        paramMap.put("rootUrl",rootUrl);
        paramMap.put("latestDays",latestDays);
        paramMap.put("descType",descType);
        paramMap.put("articleTitleRegex",articleTitleRegex);
        return paramMap;
    }
}
