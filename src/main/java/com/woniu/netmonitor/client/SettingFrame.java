/*
 * Created by JFormDesigner on Sat Nov 30 11:23:53 CST 2019
 */

package com.woniu.netmonitor.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.woniu.netmonitor.util.LocalPropertyUtil;
import com.woniu.netmonitor.util.SpringUtil;

/**
 * @author sa
 */
public class SettingFrame {
    private LoginForm loginForm;

    private List<JComponent> jComponents;//保存到文件中

    private LocalPropertyUtil localPropertyBean = (LocalPropertyUtil) SpringUtil.getBean("localPropertyBean");

    public SettingFrame(LoginForm loginForm) {
        this.loginForm = loginForm;
        initComponents();
        add2LocalProperty();
        localPropertyBean.loadProperties(settingFrame, jComponents);
        loginForm.setServerIPAndPort(txt_serverIp.getText(), txt_serverPort.getText());
    }

    public void showFrame(){
        listenEventRegister();
        settingFrame.setDefaultCloseOperation(1); //关闭窗口时仅为不可见
        settingFrame.setVisible(true);
        settingFrame.setAlwaysOnTop(true);
    }

    private void add2LocalProperty(){
        jComponents = new ArrayList<>();
        jComponents.add(txt_serverIp);
        jComponents.add(txt_serverPort);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        settingFrame = new JFrame();
        panel1 = new JPanel();
        lb_set = new JLabel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        panel5 = new JPanel();
        txt_serverIp = new JTextField();
        panel4 = new JPanel();
        label2 = new JLabel();
        panel6 = new JPanel();
        txt_serverPort = new JTextField();
        panel7 = new JPanel();
        btn_saveSetting = new JButton();

        //======== settingFrame ========
        {
            Container settingFrameContentPane = settingFrame.getContentPane();
            settingFrameContentPane.setLayout(new FormLayout(
                "default:grow",
                "15dlu, 52dlu, 29dlu, fill:25dlu:grow"));

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout());

                //---- lb_set ----
                lb_set.setText("\u670d\u52a1\u5668\u8bbe\u7f6e");
                panel1.add(lb_set);
            }
            settingFrameContentPane.add(panel1, CC.xy(1, 1));

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout(2, 2));

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 12));

                    //---- label1 ----
                    label1.setText("\u670d\u52a1\u5668ip\uff1a");
                    panel3.add(label1);
                }
                panel2.add(panel3);

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- txt_serverIp ----
                    txt_serverIp.setColumns(10);
                    panel5.add(txt_serverIp);
                }
                panel2.add(panel5);

                //======== panel4 ========
                {
                    panel4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 12));

                    //---- label2 ----
                    label2.setText("\u7aef\u53e3\uff1a");
                    panel4.add(label2);
                }
                panel2.add(panel4);

                //======== panel6 ========
                {
                    panel6.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- txt_serverPort ----
                    txt_serverPort.setColumns(10);
                    panel6.add(txt_serverPort);
                }
                panel2.add(panel6);
            }
            settingFrameContentPane.add(panel2, CC.xy(1, 2));

            //======== panel7 ========
            {
                panel7.setLayout(new FlowLayout());

                //---- btn_saveSetting ----
                btn_saveSetting.setText("\u4fdd\u5b58");
                panel7.add(btn_saveSetting);
            }
            settingFrameContentPane.add(panel7, CC.xy(1, 3));
            settingFrame.pack();
            settingFrame.setLocationRelativeTo(settingFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        settingFrame.setName("settingFrame");
        txt_serverIp.setName("txt_serverIp");
        txt_serverPort.setName("txt_serverPort");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame settingFrame;
    private JPanel panel1;
    private JLabel lb_set;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label1;
    private JPanel panel5;
    private JTextField txt_serverIp;
    private JPanel panel4;
    private JLabel label2;
    private JPanel panel6;
    private JTextField txt_serverPort;
    private JPanel panel7;
    private JButton btn_saveSetting;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void listenEventRegister(){
        /**
         * 保存事件注册
         */
        btn_saveSetting.addActionListener(e -> {
            localPropertyBean.saveProperties(settingFrame, jComponents);
            loginForm.setServerIPAndPort(txt_serverIp.getText(), txt_serverPort.getText());
            closeFrame();
        });

    }

    private void closeFrame(){
        settingFrame.dispose();
    }

}
