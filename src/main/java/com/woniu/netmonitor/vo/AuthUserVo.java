package com.woniu.netmonitor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: guyalin
 * @date: 2019/11/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserVo {
    private String userId;
    private String passWd;
}
