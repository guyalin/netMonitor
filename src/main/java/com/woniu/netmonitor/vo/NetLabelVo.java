package com.woniu.netmonitor.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: guyalin
 * @date: 2019/12/6
 */
@Data
@NoArgsConstructor
public class NetLabelVo {
    private String labelId;
    private String labelName;
    private String netList;
    private String createTime;

    public NetLabelVo(String labelName, String netList){
        this.labelName = labelName;
        this.netList = netList;
    }

    @Override
    public String toString() {
        return this.labelName;
    }
}
