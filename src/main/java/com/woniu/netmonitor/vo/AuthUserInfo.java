package com.woniu.netmonitor.vo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: guyalin
 * @date: 2019/11/30
 */
@Data
public class AuthUserInfo {
    private String userId;
    private String userName;
    private String token;
    private String lastLoginTime;
    private List<String> privileges;

}
