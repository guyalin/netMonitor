package com.woniu.netmonitor;

import com.woniu.netmonitor.client.NetMonitorClinet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NetmonitorApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(NetmonitorApplication.class, args);
        /**
         * 开启可视化界面
         */
        NetMonitorClinet netMonitorClient = new NetMonitorClinet();
        netMonitorClient.showFrame();
    }

}
