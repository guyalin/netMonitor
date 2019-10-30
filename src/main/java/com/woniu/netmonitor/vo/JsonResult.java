package com.woniu.netmonitor.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -1088187960858323030L;
    public static final String SUCC="0";
    public static final String FAIL="1";


    private String returnCode=SUCC;
    private String returnMsg;
    private Object data;

}