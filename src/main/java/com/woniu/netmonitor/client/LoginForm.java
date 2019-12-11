/*
 * Created by JFormDesigner on Sat Nov 30 10:26:34 CST 2019
 */

package com.woniu.netmonitor.client;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.woniu.netmonitor.dictionary.MessageBoxType;
import com.woniu.netmonitor.util.*;
import com.woniu.netmonitor.vo.AuthUserInfo;
import com.woniu.netmonitor.vo.AuthUserVo;
import com.woniu.netmonitor.vo.JsonResult;

import org.apache.commons.lang.StringUtils;

/**
 * @author sa
 */
public class LoginForm {

    private HttpTransferBean transferBean = (HttpTransferBean) SpringUtil.getBean("httpTransferBean");
    private WebClientUtil webClientBean = (WebClientUtil) SpringUtil.getBean("webClientBean");
    private ServerEndpointBean serverEndpointBean = (ServerEndpointBean) SpringUtil.getBean("serverEndpointBean");
    private LocalPropertyUtil localPropertyBean = (LocalPropertyUtil) SpringUtil.getBean("localPropertyBean");

    private List<JComponent> localProperties;

    private SettingFrame settingFrame;
    private String serverIp;
    private String serverPort;
    private String baseUrl;

    private AuthUserInfo authUserInfo;

    public LoginForm() {
        initComponents();
        listenEventRegister();
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        loginFrame = new JFrame();
        panel1 = new JPanel();
        lb_title = new JLabel();
        panel2 = new JPanel();
        label1 = new JLabel();
        panel4 = new JPanel();
        txt_userName = new JTextField();
        label4 = new JLabel();
        panel5 = new JPanel();
        txt_pwd = new JPasswordField();
        panel3 = new JPanel();
        btn_setting = new JButton();
        btn_login = new JButton();

        //======== loginFrame ========
        {
            loginFrame.setTitle("LOGIN");
            loginFrame.setForeground(Color.gray);
            Container loginFrameContentPane = loginFrame.getContentPane();
            loginFrameContentPane.setLayout(new FormLayout(
                    "221dlu",
                    "fill:23dlu, $lgap, default, $lgap, 56dlu, $lgap, 23dlu"));

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout());

                //---- lb_title ----
                lb_title.setText("\u653f\u52a1\u4fe1\u606f\u8ffd\u8e2a\u7cfb\u7edf");
                lb_title.setPreferredSize(new Dimension(150, 25));
                lb_title.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
                lb_title.setMaximumSize(new Dimension(240, 20));
                lb_title.setHorizontalAlignment(SwingConstants.CENTER);
                panel1.add(lb_title);
            }
            loginFrameContentPane.add(panel1, CC.xy(1, 1));

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout(2, 2));

                //---- label1 ----
                label1.setText("\u7528\u6237\u540d\uff1a");
                label1.setHorizontalAlignment(SwingConstants.RIGHT);
                panel2.add(label1);

                //======== panel4 ========
                {
                    panel4.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- txt_userName ----
                    txt_userName.setHorizontalAlignment(SwingConstants.LEFT);
                    txt_userName.setColumns(10);
                    panel4.add(txt_userName);
                }
                panel2.add(panel4);

                //---- label4 ----
                label4.setText("\u5bc6\u7801\uff1a");
                label4.setHorizontalAlignment(SwingConstants.RIGHT);
                panel2.add(label4);

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- txt_pwd ----
                    txt_pwd.setColumns(10);
                    panel5.add(txt_pwd);
                }
                panel2.add(panel5);
            }
            loginFrameContentPane.add(panel2, CC.xy(1, 5));

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));

                //---- btn_setting ----
                btn_setting.setText("\u8bbe\u7f6e");
                panel3.add(btn_setting);

                //---- btn_login ----
                btn_login.setText("\u767b\u5f55");
                panel3.add(btn_login);
            }
            loginFrameContentPane.add(panel3, CC.xy(1, 7));
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(loginFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        loginFrame.setName("loginFrame");
        txt_userName.setName("txt_userName");
        txt_pwd.setName("txt_userPwd");

        add2LocalProperty();
        String name = txt_userName.getName();
        localPropertyBean.loadProperties(loginFrame, localProperties);
        this.settingFrame = new SettingFrame(this);
    }

    public void setServerIPAndPort(String serverIp, String serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    private void add2LocalProperty() {
        localProperties = new ArrayList<>();
        localProperties.add(txt_userName);
        localProperties.add(txt_pwd);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame loginFrame;
    private JPanel panel1;
    private JLabel lb_title;
    private JPanel panel2;
    private JLabel label1;
    private JPanel panel4;
    private JTextField txt_userName;
    private JLabel label4;
    private JPanel panel5;
    private JPasswordField txt_pwd;
    private JPanel panel3;
    private JButton btn_setting;
    private JButton btn_login;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    private void listenEventRegister() {
        /**
         * 增加设置按钮事件注册
         */
        btn_setting.addActionListener(e -> {
            settingFrame.showFrame();
        });

        /**
         * 增加登录按钮事件注册
         */
        btn_login.addActionListener(e -> {
            //保存输入变量到本地文件。
            localPropertyBean.saveProperties(loginFrame, localProperties);
            if (StringUtils.isEmpty(txt_userName.getText()) ||
                    StringUtils.isEmpty(new String(txt_pwd.getPassword()))) {
                JFrameUtil.messageFrame(loginFrame, MessageBoxType.ALERT, "请输入用户名或密码");
                return;
            }
            if (StringUtils.isBlank(serverIp) || StringUtils.isBlank(serverPort)){
                JFrameUtil.messageFrame(loginFrame, MessageBoxType.ERROR, "请先输入服务器信息");
                return;
            }
            AuthUserVo userVo = new AuthUserVo(txt_userName.getText(), new String(txt_pwd.getPassword()));
            //连接服务器查询用户信息是否存在。开始鉴权
            //获取用户名，密码
            try {
                Boolean loginRes = connectServer(userVo);
                if (loginRes) {
                    NetMonitorClinet netMonitorClient = new NetMonitorClinet(baseUrl);
                    netMonitorClient.showFrame();
                    closeFrame();
                }

            } catch (Exception e1) {
                //e1.printStackTrace();
                JFrameUtil.messageFrame(loginFrame, MessageBoxType.ERROR, "登录异常:" + e1.toString());
            }
        });

    }

    /**
     * 连接服务器,获得用户权限信息
     *
     * @param userVo
     * @return
     * @throws Exception
     */
    private Boolean connectServer(AuthUserVo userVo) throws Exception {
        baseUrl = new StringBuilder("http://").append(serverIp).append(":").append(serverPort).toString();

        StringBuilder urlPath = new StringBuilder(baseUrl);
        urlPath = urlPath.append(serverEndpointBean.getRootUrlPath()).
                append(serverEndpointBean.getNetServerLoginEndPoint());
        JsonResult jsonResult = webClientBean.webClientPostMethodAsync(urlPath.toString(), JsonResult.class, userVo);
        if (jsonResult == null) {
            JFrameUtil.messageFrame(loginFrame, MessageBoxType.ERROR, "登录异常，无法连接服务器");
            return false;
        }
        if (jsonResult.getReturnCode().equals("-1")
                || jsonResult.getReturnCode().equals("-2")) {
            JFrameUtil.messageFrame(loginFrame, MessageBoxType.ERROR, jsonResult.getReturnMsg());
            return false;
        }
        if (jsonResult.getReturnCode().equals("200")) {
            //JSONObject jsonObject = new JSONObject();
            authUserInfo = JSONObject.parseObject(JSONObject.toJSONString(jsonResult.getData()), AuthUserInfo.class);
            //authUserInfo = (AuthUserInfo) JSONObject.toBean(jsonObject, AuthUserInfo.class);
            webClientBean.setAuthUserInfo(authUserInfo);
            return true;
        }
        JFrameUtil.messageFrame(loginFrame, MessageBoxType.ERROR, "未知错误:" + jsonResult.getReturnMsg());
        return false;
    }

    private void closeFrame() {
        loginFrame.dispose();
    }

}
