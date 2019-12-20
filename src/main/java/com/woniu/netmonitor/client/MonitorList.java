/*
 * Created by JFormDesigner on Sun Nov 17 22:31:16 CST 2019
 */

package com.woniu.netmonitor.client;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author gyl
 */
public class MonitorList {

    private String urlList;
    private int urlListSize;

    public MonitorList() {
        initComponents();
        urlListFrame.setDefaultCloseOperation(1);
    }

    public void setUrlList(String urlList, int urlListSize){
        this.urlList = urlList;
        this.urlListSize = urlListSize;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        urlListFrame = new JFrame();
        panel1 = new JPanel();
        panel5 = new JPanel();
        monitorList = new JLabel();
        lb_urlNum = new JLabel();
        panel4 = new JPanel();
        scrollPane1 = new JScrollPane();
        txt_monitorList = new JTextArea();

        //======== urlListFrame ========
        {
            urlListFrame.setMinimumSize(new Dimension(400, 400));
            urlListFrame.setTitle("\u76d1\u63a7\u5217\u8868");
            Container urlListFrameContentPane = urlListFrame.getContentPane();
            urlListFrameContentPane.setLayout(new FlowLayout());

            //======== panel1 ========
            {
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout());

                    //---- monitorList ----
                    monitorList.setText("\u5df2\u76d1\u63a7\u7f51\u7ad9:");
                    monitorList.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel5.add(monitorList);

                    //---- lb_urlNum ----
                    lb_urlNum.setText("0\u4e2a");
                    panel5.add(lb_urlNum);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

                        //======== scrollPane1 ========
                        {
                            scrollPane1.setViewportView(txt_monitorList);
                        }
                        panel4.add(scrollPane1);
                    }
                    panel5.add(panel4);
                }
                panel1.add(panel5);
            }
            urlListFrameContentPane.add(panel1);
            urlListFrame.pack();
            urlListFrame.setLocationRelativeTo(urlListFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void initTextArea(){
        txt_monitorList.setText(urlList);
        lb_urlNum.setText(urlListSize + "个");
    }

    public void showFrame(){
        initTextArea();
        urlListFrame.setVisible(true);
    }

    public void closeFrame(){
        urlListFrame.dispose();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame urlListFrame;
    private JPanel panel1;
    private JPanel panel5;
    private JLabel monitorList;
    private JLabel lb_urlNum;
    private JPanel panel4;
    private JScrollPane scrollPane1;
    private JTextArea txt_monitorList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
