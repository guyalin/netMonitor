package com.woniu.netmonitor.configuration;

import com.woniu.netmonitor.client.NetMonitorClinet;
import com.woniu.netmonitor.client.UrlInfoAdd;
import com.woniu.netmonitor.dictionary.MessageBoxType;
import com.woniu.netmonitor.entity.UrlMonitorEntity;
import com.woniu.netmonitor.util.JFrameUtil;
import com.woniu.netmonitor.util.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;

@Slf4j
@Component
public class WebSocketConfig {

    @Value("${net.server.endpoint.websocket}")
    private String netWebSocketEndPoint;
    @Value("${net.server.endpoint.rootpath}")
    private String serverRootPath;
    /*@Value("${net.server.ipandport}")
    private String remoteServerIpAndPort;*/

    @Autowired
    private WebClientUtil webClientBean;

    private WebSocketClient socketClient;

    private NetMonitorClinet monitorClient;

    //private UrlInfoAdd urlInfoAdd;

    public WebSocketClient getSocketClient(String remoteServerIpAndPort) {
        if (socketClient == null) {
            setWebSocketClient(remoteServerIpAndPort);
        } else
            log.info("当前已存在webSocket实例");
        return socketClient;
    }

    public void initFrameInstance(NetMonitorClinet monitorClient) {
        this.monitorClient = monitorClient;
    }

    private void setWebSocketClient(String remoteServerIpAndPort) {
        try {
            String webSocketServerIpAndPort = remoteServerIpAndPort.replace("http", "ws");
            StringBuilder path = new StringBuilder(webSocketServerIpAndPort);
            //InetAddress addr = InetAddress.getLocalHost(); //获取本机ip
            //String hostName = addr.getHostName(); //获取本机计算机名称
            //String hostName = "localPC"; //获取本机计算机名称
            String authName = webClientBean.getAuthUserInfo().getUserId();
            path = path.append(serverRootPath).append(netWebSocketEndPoint).append("/").append(authName);
            log.info("path:{}", path);

            URI uri = new URI(path.toString());
            WebSocketClient webSocketClient = new WebSocketClient(uri, new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    log.info("[websocket] 连接成功");
                }

                @Override
                public void onMessage(String s) {
                    log.info("[websocket] 收到消息={}", s);
                    JSONObject jsonObject = JSONObject.fromObject(s);
                    /**
                     * 更新是异步操作，个别网址更新失败应当采用服务端推送的形式的告知客户端
                     */
                    if (jsonObject.containsKey("failedUrls")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("failedUrls");
                        List<UrlMonitorEntity> failedUrlEntities = JSONArray.toList(jsonArray, new UrlMonitorEntity(), new JsonConfig());
                        monitorClient.alertFailedUrlsForOneQuery(failedUrlEntities);
                    }
                    /**
                     * 暂时不用
                     */
                    if (jsonObject.containsKey("messageStr")){
                        String msg = jsonObject.getString("messageStr");
                        //JFrameUtil.messageFrame(MessageBoxType.ALERT, msg);
                        log.info("msg", msg);
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    log.info("[websocket] 退出连接");
                    socketClient = null;
                }

                @Override
                public void onError(Exception e) {
                    log.info("[websocket] 连接错误={}", e.getMessage());
                    socketClient = null;
                }
            };
            webSocketClient.connect();
            socketClient = webSocketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            socketClient = null;
        }
    }

    public void disConnectServer(){
        if (socketClient != null){
            socketClient.close();
        }

    }


}
