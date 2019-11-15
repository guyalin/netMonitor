package com.woniu.netmonitor.entity;

import lombok.Data;

@Data
public class NetChildFilter {
    private String childFilterId;
    private String rootTag;
    private String recordTag;
    private String urlTag;
    private Integer urlTagIndex;
    private String urlLocation;
    private String urlAttrName;
    private String nameTag;
    private Integer nameTagIndex;
    private String nameLocation;
    private String nameAttrName;

    public NetChildFilter(){
    }

    public NetChildFilter(String childFilterId, String rootTag, String recordTag,
                          String urlTag, Integer urlTagIndex, String urlLocation, String urlAttrName,
                          String nameTag, Integer nameTagIndex, String nameLocation, String nameAttrName){
        this.childFilterId = childFilterId;
        this.rootTag = rootTag;
        this.recordTag = recordTag;
        this.urlTag = urlTag;
        this.urlTagIndex = urlTagIndex;
        this.urlLocation = urlLocation;
        this.urlAttrName = urlAttrName;
        this.nameTag = nameTag;
        this.nameTagIndex = nameTagIndex;
        this.nameLocation = nameLocation;
        this.nameAttrName = nameAttrName;
    }

}
