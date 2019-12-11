package com.woniu.netmonitor.vo;

import lombok.Data;

/**
 * @description:
 * @author: guyalin
 * @date: 2019/12/6
 */
@Data
public class NetLabelVo {
    private String labelId;
    private String labelName;
    private String netList;
    private String createTime;

    @Override
    public String toString() {
        return this.labelName;
    }
}
