package com.woniu.netmonitor.client;

import org.apache.commons.lang.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

public class ClientMain {
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public static void main(String[] args) {

        long lo = SECURE_RANDOM.nextLong();
        long lo2 = SECURE_RANDOM.nextLong();
        System.out.println(lo+"");
        System.out.println(lo2+"");

        String ss = ClientMain.randomStrForLength(8);
        System.out.println(ss);
    }

    public static String randomStrForLength(int length){
        return RandomStringUtils.random(length,0,0,true,true,(char[])null,SECURE_RANDOM);
    }
}
