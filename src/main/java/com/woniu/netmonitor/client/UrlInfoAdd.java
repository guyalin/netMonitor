/*
 * Created by JFormDesigner on Wed Nov 13 10:21:19 CST 2019
 */

package com.woniu.netmonitor.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.woniu.netmonitor.dictionary.MessageBoxType;
import com.woniu.netmonitor.entity.ArticleRecord;
import com.woniu.netmonitor.entity.ArticleRecordFilter;
import com.woniu.netmonitor.entity.NetChildFilter;
import com.woniu.netmonitor.entity.UrlMonitorEntity;
import com.woniu.netmonitor.util.*;
import com.woniu.netmonitor.vo.JsonResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @author gyl
 */
public class UrlInfoAdd {

    private NetMonitorClinet parentComponent;

    private WebClientUtil webClientBean = (WebClientUtil) SpringUtil.getBean("webClientBean");
    private ServerEndpointBean serverEndpointBean = (ServerEndpointBean) SpringUtil.getBean("serverEndpointBean");
    private LocalPropertyUtil localPropertyBean = (LocalPropertyUtil) SpringUtil.getBean("localPropertyBean");

    private String serverBaseUrl;

    private UrlMonitorEntity urlMonitorEntity;
    private NetChildFilter netChildFilter;
    private ArticleRecordFilter articleRecordFilter;

    //private List<ArticleRecord> articleRecords = new ArrayList<>();

    public UrlInfoAdd(String urlPath) {
        initComponents();
        this.serverBaseUrl = urlPath;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        URLAddFrame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        lb_subTitle = new JLabel();
        hSpacer1 = new JPanel(null);
        label8 = new JLabel();
        txt_area = new JTextField();
        label9 = new JLabel();
        txt_urlName = new JTextField();
        label10 = new JLabel();
        txt_connectUrl = new JTextField();
        label11 = new JLabel();
        textField4 = new JTextField();
        hSpacer3 = new JPanel(null);
        hSpacer5 = new JPanel(null);
        hSpacer6 = new JPanel(null);
        hSpacer4 = new JPanel(null);
        hSpacer7 = new JPanel(null);
        hSpacer8 = new JPanel(null);
        hSpacer9 = new JPanel(null);
        hSpacer10 = new JPanel(null);
        hSpacer11 = new JPanel(null);
        hSpacer12 = new JPanel(null);
        hSpacer13 = new JPanel(null);
        hSpacer14 = new JPanel(null);
        hSpacer15 = new JPanel(null);
        hSpacer24 = new JPanel(null);
        hSpacer25 = new JPanel(null);
        hSpacer26 = new JPanel(null);
        hSpacer27 = new JPanel(null);
        hSpacer28 = new JPanel(null);
        hSpacer30 = new JPanel(null);
        hSpacer31 = new JPanel(null);
        hSpacer32 = new JPanel(null);
        hSpacer33 = new JPanel(null);
        panel3 = new JPanel();
        chb_ifChildFilter = new JCheckBox();
        label15 = new JLabel();
        label16 = new JLabel();
        txt_childFilterId = new JTextField();
        label17 = new JLabel();
        txt_pageRootTag = new JTextField();
        label18 = new JLabel();
        txt_recordTag = new JTextField();
        label19 = new JLabel();
        txt_urlTag = new JTextField();
        label20 = new JLabel();
        txt_urlTagIndex = new JTextField();
        label21 = new JLabel();
        txt_urlLocation = new JTextField();
        label22 = new JLabel();
        txt_urlAttrName = new JTextField();
        label23 = new JLabel();
        txt_nameTag = new JTextField();
        label24 = new JLabel();
        txt_nameTagIndex = new JTextField();
        label25 = new JLabel();
        txt_nameLocation = new JTextField();
        label26 = new JLabel();
        txt_nameAttrName = new JTextField();
        hSpacer16 = new JPanel(null);
        hSpacer17 = new JPanel(null);
        hSpacer18 = new JPanel(null);
        hSpacer19 = new JPanel(null);
        hSpacer20 = new JPanel(null);
        hSpacer21 = new JPanel(null);
        hSpacer22 = new JPanel(null);
        hSpacer23 = new JPanel(null);
        panel4 = new JPanel();
        label36 = new JLabel();
        hSpacer29 = new JPanel(null);
        label37 = new JLabel();
        txt_recordFilterId = new JTextField();
        label38 = new JLabel();
        txt_ifActiveAsync = new JTextField();
        label39 = new JLabel();
        txt_ifExcludeCdata = new JTextField();
        label40 = new JLabel();
        txt_recordRootTag = new JTextField();
        label41 = new JLabel();
        txt_recordBodyTag = new JTextField();
        label42 = new JLabel();
        txt_titleTag = new JTextField();
        label43 = new JLabel();
        txt_titleTagIndex = new JTextField();
        label44 = new JLabel();
        txt_isOwnerText = new JTextField();
        label45 = new JLabel();
        txt_isAttr = new JTextField();
        label46 = new JLabel();
        txt_attrName = new JTextField();
        label47 = new JLabel();
        txt_dateTag = new JTextField();
        label48 = new JLabel();
        txt_dateTagIndex = new JTextField();
        label49 = new JLabel();
        txt_dateTagLocation = new JTextField();
        label50 = new JLabel();
        txt_dateFilterStr = new JTextField();
        label51 = new JLabel();
        txt_urlRecordTag = new JTextField();
        panel5 = new JPanel();
        btn_test = new JButton();
        scrollPane1 = new JScrollPane();
        txta_test = new JTextArea();
        panel6 = new JPanel();
        btn_save = new JButton();

        //======== URLAddFrame ========
        {
            Container URLAddFrameContentPane = URLAddFrame.getContentPane();
            URLAddFrameContentPane.setLayout(new FormLayout(
                "default:grow",
                "2*(fill:default:grow, $lgap), default"));

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout(1, 3, 3, 1));

                //======== panel2 ========
                {
                    panel2.setLayout(new GridLayout(16, 2));

                    //---- lb_subTitle ----
                    lb_subTitle.setText("\u7f51\u5740\u4fe1\u606f");
                    lb_subTitle.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel2.add(lb_subTitle);
                    panel2.add(hSpacer1);

                    //---- label8 ----
                    label8.setText("\u5730\u533a");
                    label8.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel2.add(label8);
                    panel2.add(txt_area);

                    //---- label9 ----
                    label9.setText("\u7f51\u5740\u540d");
                    label9.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel2.add(label9);
                    panel2.add(txt_urlName);

                    //---- label10 ----
                    label10.setText("\u7f51\u5740\u76ee\u6807url");
                    label10.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel2.add(label10);
                    panel2.add(txt_connectUrl);

                    //---- label11 ----
                    label11.setText("\u662f\u5426\u6fc0\u6d3b");
                    label11.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel2.add(label11);
                    panel2.add(textField4);
                    panel2.add(hSpacer3);
                    panel2.add(hSpacer5);
                    panel2.add(hSpacer6);
                    panel2.add(hSpacer4);
                    panel2.add(hSpacer7);
                    panel2.add(hSpacer8);
                    panel2.add(hSpacer9);
                    panel2.add(hSpacer10);
                    panel2.add(hSpacer11);
                    panel2.add(hSpacer12);
                    panel2.add(hSpacer13);
                    panel2.add(hSpacer14);
                    panel2.add(hSpacer15);
                    panel2.add(hSpacer24);
                    panel2.add(hSpacer25);
                    panel2.add(hSpacer26);
                    panel2.add(hSpacer27);
                    panel2.add(hSpacer28);
                    panel2.add(hSpacer30);
                    panel2.add(hSpacer31);
                    panel2.add(hSpacer32);
                    panel2.add(hSpacer33);
                }
                panel1.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(16, 2));

                    //---- chb_ifChildFilter ----
                    chb_ifChildFilter.setText("\u5b50\u7f51\u9875\u8fc7\u6ee4");
                    chb_ifChildFilter.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(chb_ifChildFilter);
                    panel3.add(label15);

                    //---- label16 ----
                    label16.setText("childFilterId");
                    label16.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label16);
                    panel3.add(txt_childFilterId);

                    //---- label17 ----
                    label17.setText("rootTag");
                    label17.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label17);
                    panel3.add(txt_pageRootTag);

                    //---- label18 ----
                    label18.setText("recordTag");
                    label18.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label18);
                    panel3.add(txt_recordTag);

                    //---- label19 ----
                    label19.setText("urlTag");
                    label19.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label19);
                    panel3.add(txt_urlTag);

                    //---- label20 ----
                    label20.setText("urlTagIndex");
                    label20.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label20);
                    panel3.add(txt_urlTagIndex);

                    //---- label21 ----
                    label21.setText("urlLocation");
                    label21.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label21);
                    panel3.add(txt_urlLocation);

                    //---- label22 ----
                    label22.setText("urlAttrName");
                    label22.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label22);
                    panel3.add(txt_urlAttrName);

                    //---- label23 ----
                    label23.setText("nameTag");
                    label23.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label23);
                    panel3.add(txt_nameTag);

                    //---- label24 ----
                    label24.setText("nameTagIndex");
                    label24.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label24);
                    panel3.add(txt_nameTagIndex);

                    //---- label25 ----
                    label25.setText("nameLocation");
                    label25.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label25);
                    panel3.add(txt_nameLocation);

                    //---- label26 ----
                    label26.setText("nameAttrName");
                    label26.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel3.add(label26);
                    panel3.add(txt_nameAttrName);
                    panel3.add(hSpacer16);
                    panel3.add(hSpacer17);
                    panel3.add(hSpacer18);
                    panel3.add(hSpacer19);
                    panel3.add(hSpacer20);
                    panel3.add(hSpacer21);
                    panel3.add(hSpacer22);
                    panel3.add(hSpacer23);
                }
                panel1.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new GridLayout(16, 2));

                    //---- label36 ----
                    label36.setText("\u5217\u8868\u8bb0\u5f55\u8fc7\u6ee4");
                    label36.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label36);
                    panel4.add(hSpacer29);

                    //---- label37 ----
                    label37.setText("filterId");
                    label37.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label37);
                    panel4.add(txt_recordFilterId);

                    //---- label38 ----
                    label38.setText("ifActiveAsync");
                    label38.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label38);
                    panel4.add(txt_ifActiveAsync);

                    //---- label39 ----
                    label39.setText("ifExcludeCdata");
                    label39.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label39);
                    panel4.add(txt_ifExcludeCdata);

                    //---- label40 ----
                    label40.setText("rootTag");
                    label40.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label40);
                    panel4.add(txt_recordRootTag);

                    //---- label41 ----
                    label41.setText("recordBodyTag");
                    label41.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label41);
                    panel4.add(txt_recordBodyTag);

                    //---- label42 ----
                    label42.setText("titleTag");
                    label42.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label42);
                    panel4.add(txt_titleTag);

                    //---- label43 ----
                    label43.setText("titleTagIndex");
                    label43.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label43);
                    panel4.add(txt_titleTagIndex);

                    //---- label44 ----
                    label44.setText("isOwnerText");
                    label44.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label44);
                    panel4.add(txt_isOwnerText);

                    //---- label45 ----
                    label45.setText("isAttr");
                    label45.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label45);
                    panel4.add(txt_isAttr);

                    //---- label46 ----
                    label46.setText("attrName");
                    label46.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label46);
                    panel4.add(txt_attrName);

                    //---- label47 ----
                    label47.setText("dateTag");
                    label47.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label47);
                    panel4.add(txt_dateTag);

                    //---- label48 ----
                    label48.setText("dateTagIndex");
                    label48.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label48);
                    panel4.add(txt_dateTagIndex);

                    //---- label49 ----
                    label49.setText("dateTagLocation");
                    label49.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label49);
                    panel4.add(txt_dateTagLocation);

                    //---- label50 ----
                    label50.setText("dateFilterStr");
                    label50.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label50);
                    panel4.add(txt_dateFilterStr);

                    //---- label51 ----
                    label51.setText("urlTag");
                    label51.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel4.add(label51);
                    panel4.add(txt_urlRecordTag);
                }
                panel1.add(panel4);
            }
            URLAddFrameContentPane.add(panel1, CC.xy(1, 1));

            //======== panel5 ========
            {
                panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

                //---- btn_test ----
                btn_test.setText("\u6d4b\u8bd5");
                panel5.add(btn_test);

                //======== scrollPane1 ========
                {

                    //---- txta_test ----
                    txta_test.setRows(5);
                    scrollPane1.setViewportView(txta_test);
                }
                panel5.add(scrollPane1);
            }
            URLAddFrameContentPane.add(panel5, CC.xy(1, 3));

            //======== panel6 ========
            {
                panel6.setLayout(new FlowLayout());

                //---- btn_save ----
                btn_save.setText("\u4fdd\u5b58\u5e76\u6fc0\u6d3b");
                panel6.add(btn_save);
            }
            URLAddFrameContentPane.add(panel6, CC.xy(1, 5));
            URLAddFrame.pack();
            URLAddFrame.setLocationRelativeTo(URLAddFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    /**
     * 按钮等事件注册
     */
    private void listenEventRegister() {
        /**
         * 测试网址添加
         */
        btn_test.addActionListener(e -> {
            saveLocalProperty();
            initNetUrlVo();
            //String serverUrlSavePath = serverBaseUrl + serverEndpointBean.getServerRootPathEndpoint() + serverEndpointBean.getNetUrlSaveEndpoint();
            String serverUrlTestPath = serverBaseUrl + serverEndpointBean.getServerRootPathEndpoint() + serverEndpointBean.getNetUrlTestEndpoint();
            try {
                JSONObject responseEntity = webClientBean.webClientPostMethodAsync(serverUrlTestPath, JSONObject.class, urlMonitorEntity);
                JSONArray jsonArray = responseEntity.getJSONArray("data");
                List<ArticleRecord> articleRecords = JSONArray.toList(jsonArray, new ArticleRecord(), new JsonConfig());
                StringBuilder testRecord = new StringBuilder();
                for (ArticleRecord entity : articleRecords) {
                    testRecord.append(entity.getArticleName()).append(":").
                            append(entity.getArticleTitle()).append(":").
                            append(entity.getTargetUrl()).append("\n");
                }
                txta_test.setText(testRecord.toString());
            }catch (Exception ex){
                txta_test.setText("解析异常："+ ex.toString());
            }

        });

        /**
         * 保存激活按钮事件
         */
        btn_save.addActionListener(e -> {
            InetAddress addr = null; //获取本机ip
            try {
                addr = InetAddress.getLocalHost();
                String hostName = addr.getHostName(); //获取本机计算机名称
                String serverUrlSavePath = serverBaseUrl + serverEndpointBean.getServerRootPathEndpoint()
                        + serverEndpointBean.getNetUrlSaveEndpoint() + "/" + hostName;
                JSONObject responseEntity = webClientBean.webClientPostMethodAsync(serverUrlSavePath, JSONObject.class, urlMonitorEntity);
                String resultCode = responseEntity.getString("returnCode");
                if (resultCode.equals("0")){
                    //JFrameUtil.messageFrame(URLAddFrame,MessageBoxType.INFO, responseEntity.getString("returnMsg"));
                    int response = JFrameUtil.messageFrame(URLAddFrame, MessageBoxType.CONFIRM, responseEntity.getString("returnMsg"));
                    if (response == 0) {
                        closeFrame();
                    }
                }else {
                    JFrameUtil.messageFrame(URLAddFrame,MessageBoxType.ERROR, responseEntity.getString("returnMsg"));
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }

        });

    }

    private void saveLocalProperty() {
        Map<String, String> stringMap = new HashMap<>();
        //urlEntity
        stringMap.put("txt_area", txt_area.getText());
        stringMap.put("txt_urlName", txt_urlName.getText());
        stringMap.put("txt_connectUrl", txt_connectUrl.getText());
        //childFilter
        stringMap.put("chb_ifChildFilter", Boolean.toString(chb_ifChildFilter.isSelected()));
        stringMap.put("txt_pageRootTag", txt_pageRootTag.getText());
        stringMap.put("txt_recordTag", txt_recordTag.getText());
        stringMap.put("txt_urlTag", txt_urlTag.getText());
        stringMap.put("txt_urlTagIndex", txt_urlTagIndex.getText());
        stringMap.put("txt_urlLocation", txt_urlLocation.getText());
        stringMap.put("txt_urlAttrName", txt_urlAttrName.getText());
        stringMap.put("txt_nameTag", txt_nameTag.getText());
        stringMap.put("txt_nameTagIndex", txt_nameTagIndex.getText());
        stringMap.put("txt_nameLocation", txt_nameLocation.getText());
        stringMap.put("txt_nameAttrName", txt_nameAttrName.getText());
        //recordFilter
        stringMap.put("txt_recordFilterId", txt_recordFilterId.getText());
        stringMap.put("txt_ifActiveAsync", txt_ifActiveAsync.getText());
        stringMap.put("txt_ifExcludeCdata", txt_ifExcludeCdata.getText());
        stringMap.put("txt_recordRootTag", txt_recordRootTag.getText());
        stringMap.put("txt_recordBodyTag", txt_recordBodyTag.getText());
        stringMap.put("txt_titleTag", txt_titleTag.getText());
        stringMap.put("txt_titleTagIndex", txt_titleTagIndex.getText());
        stringMap.put("txt_isOwnerText", txt_isOwnerText.getText());
        stringMap.put("txt_isAttr", txt_isAttr.getText());
        stringMap.put("txt_attrName", txt_attrName.getText());
        stringMap.put("txt_dateTag", txt_dateTag.getText());
        stringMap.put("txt_dateTagIndex", txt_dateTagIndex.getText());
        stringMap.put("txt_dateTagLocation", txt_dateTagLocation.getText());
        stringMap.put("txt_dateFilterStr", txt_dateFilterStr.getText());
        stringMap.put("txt_urlRecordTag", txt_urlRecordTag.getText());

        localPropertyBean.saveProperty(stringMap);
    }

    private void loadLocalProperty() {
        //urlEntity
        txt_area.setText(localPropertyBean.getProperty("txt_area"));
        txt_urlName.setText(localPropertyBean.getProperty("txt_urlName"));
        txt_connectUrl.setText(localPropertyBean.getProperty("txt_connectUrl"));
        //childFilter
        Boolean ck_ifChildFilter = (new Boolean(localPropertyBean.getProperty("chb_ifChildFilter"))).booleanValue();
        if (ck_ifChildFilter) {
            chb_ifChildFilter.setSelected(true);
        }
        txt_pageRootTag.setText(localPropertyBean.getProperty("txt_pageRootTag"));
        txt_recordTag.setText(localPropertyBean.getProperty("txt_recordTag"));
        txt_urlTag.setText(localPropertyBean.getProperty("txt_urlTag"));
        txt_urlTagIndex.setText(localPropertyBean.getProperty("txt_urlTagIndex"));

        txt_urlLocation.setText(localPropertyBean.getProperty("txt_urlLocation"));
        txt_urlAttrName.setText(localPropertyBean.getProperty("txt_urlAttrName"));
        txt_nameTag.setText(localPropertyBean.getProperty("txt_nameTag"));
        txt_nameTagIndex.setText(localPropertyBean.getProperty("txt_nameTagIndex"));
        txt_nameLocation.setText(localPropertyBean.getProperty("txt_nameLocation"));
        txt_nameAttrName.setText(localPropertyBean.getProperty("txt_nameAttrName"));
        //recordFilter
        txt_recordFilterId.setText(localPropertyBean.getProperty("txt_recordFilterId"));
        txt_ifActiveAsync.setText(localPropertyBean.getProperty("txt_ifActiveAsync"));
        txt_ifExcludeCdata.setText(localPropertyBean.getProperty("txt_ifExcludeCdata"));
        txt_recordRootTag.setText(localPropertyBean.getProperty("txt_recordRootTag"));
        txt_recordBodyTag.setText(localPropertyBean.getProperty("txt_recordBodyTag"));
        txt_titleTag.setText(localPropertyBean.getProperty("txt_titleTag"));
        txt_titleTagIndex.setText(localPropertyBean.getProperty("txt_titleTagIndex"));
        txt_isOwnerText.setText(localPropertyBean.getProperty("txt_isOwnerText"));
        txt_isAttr.setText(localPropertyBean.getProperty("txt_isAttr"));
        txt_attrName.setText(localPropertyBean.getProperty("txt_attrName"));
        txt_dateTag.setText(localPropertyBean.getProperty("txt_dateTag"));
        txt_dateTagIndex.setText(localPropertyBean.getProperty("txt_dateTagIndex"));
        txt_dateTagLocation.setText(localPropertyBean.getProperty("txt_dateTagLocation"));
        txt_dateFilterStr.setText(localPropertyBean.getProperty("txt_dateFilterStr"));
        txt_urlRecordTag.setText(localPropertyBean.getProperty("txt_urlRecordTag"));
    }


    private void initNetUrlVo() {
        urlMonitorEntity = new UrlMonitorEntity(txt_area.getText(), txt_urlName.getText(), txt_connectUrl.getText());

        if (chb_ifChildFilter.isSelected()) {
            netChildFilter = new NetChildFilter();
            netChildFilter.setChildFilterId(txt_childFilterId.getText());
            netChildFilter.setRootTag(txt_pageRootTag.getText());
            netChildFilter.setRecordTag(txt_recordTag.getText());
            netChildFilter.setUrlTag(txt_urlTag.getText());
            netChildFilter.setUrlTagIndex(txt_urlTagIndex.getText().matches("[0-9]*") ?
                    Integer.valueOf(txt_urlTagIndex.getText()) : 0);
            netChildFilter.setUrlLocation(txt_urlLocation.getText());
            netChildFilter.setUrlAttrName(txt_urlAttrName.getText());
            netChildFilter.setNameTag(txt_nameTag.getText());
            netChildFilter.setNameTagIndex(txt_nameTagIndex.getText().matches("[0-9]*") ?
                    Integer.valueOf(txt_nameTagIndex.getText()) : 0);
            netChildFilter.setNameLocation(txt_nameLocation.getText());
            netChildFilter.setNameAttrName(txt_nameAttrName.getText());
        }

        articleRecordFilter = new ArticleRecordFilter();
        articleRecordFilter.setFilterId(txt_recordFilterId.getText());
        articleRecordFilter.setIfActiveAsync(txt_ifActiveAsync.getText().matches("[0-9]*") ?
                Integer.valueOf(txt_ifActiveAsync.getText()) : 0);
        articleRecordFilter.setIfExcludeCdata(txt_ifExcludeCdata.getText().matches("[0-9]*") ?
                Integer.valueOf(txt_ifExcludeCdata.getText()) : 0);
        articleRecordFilter.setRootTag(txt_recordRootTag.getText());
        articleRecordFilter.setRecordBodyTag(txt_recordBodyTag.getText());
        articleRecordFilter.setTitleTag(txt_titleTag.getText());
        articleRecordFilter.setTitleTagIndex(txt_titleTagIndex.getText().matches("[0-9]*") ?
                Integer.valueOf(txt_titleTagIndex.getText()) : 0);
        articleRecordFilter.setIsOwnerText(txt_isOwnerText.getText().matches("[0-9]*") ?
                Integer.valueOf(txt_isOwnerText.getText()) : 0);
        articleRecordFilter.setIsAttr(txt_isAttr.getText().matches("[0-9]*") ?
                Integer.valueOf(txt_isAttr.getText()) : 0);
        articleRecordFilter.setAttrName(txt_attrName.getText());
        articleRecordFilter.setDateTag(txt_dateTag.getText());
        articleRecordFilter.setDateTagIndex(txt_dateTagIndex.getText().matches("[0-9]*") ?
                Integer.valueOf(txt_dateTagIndex.getText()) : 0);
        articleRecordFilter.setDateTagLocation(txt_dateTagLocation.getText());
        articleRecordFilter.setDateFilterStr(txt_dateFilterStr.getText());
        articleRecordFilter.setUrlTag(txt_urlRecordTag.getText());

        urlMonitorEntity.setNetChildFilter(netChildFilter);
        urlMonitorEntity.setArticleRecordFilter(articleRecordFilter);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame URLAddFrame;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel lb_subTitle;
    private JPanel hSpacer1;
    private JLabel label8;
    private JTextField txt_area;
    private JLabel label9;
    private JTextField txt_urlName;
    private JLabel label10;
    private JTextField txt_connectUrl;
    private JLabel label11;
    private JTextField textField4;
    private JPanel hSpacer3;
    private JPanel hSpacer5;
    private JPanel hSpacer6;
    private JPanel hSpacer4;
    private JPanel hSpacer7;
    private JPanel hSpacer8;
    private JPanel hSpacer9;
    private JPanel hSpacer10;
    private JPanel hSpacer11;
    private JPanel hSpacer12;
    private JPanel hSpacer13;
    private JPanel hSpacer14;
    private JPanel hSpacer15;
    private JPanel hSpacer24;
    private JPanel hSpacer25;
    private JPanel hSpacer26;
    private JPanel hSpacer27;
    private JPanel hSpacer28;
    private JPanel hSpacer30;
    private JPanel hSpacer31;
    private JPanel hSpacer32;
    private JPanel hSpacer33;
    private JPanel panel3;
    private JCheckBox chb_ifChildFilter;
    private JLabel label15;
    private JLabel label16;
    private JTextField txt_childFilterId;
    private JLabel label17;
    private JTextField txt_pageRootTag;
    private JLabel label18;
    private JTextField txt_recordTag;
    private JLabel label19;
    private JTextField txt_urlTag;
    private JLabel label20;
    private JTextField txt_urlTagIndex;
    private JLabel label21;
    private JTextField txt_urlLocation;
    private JLabel label22;
    private JTextField txt_urlAttrName;
    private JLabel label23;
    private JTextField txt_nameTag;
    private JLabel label24;
    private JTextField txt_nameTagIndex;
    private JLabel label25;
    private JTextField txt_nameLocation;
    private JLabel label26;
    private JTextField txt_nameAttrName;
    private JPanel hSpacer16;
    private JPanel hSpacer17;
    private JPanel hSpacer18;
    private JPanel hSpacer19;
    private JPanel hSpacer20;
    private JPanel hSpacer21;
    private JPanel hSpacer22;
    private JPanel hSpacer23;
    private JPanel panel4;
    private JLabel label36;
    private JPanel hSpacer29;
    private JLabel label37;
    private JTextField txt_recordFilterId;
    private JLabel label38;
    private JTextField txt_ifActiveAsync;
    private JLabel label39;
    private JTextField txt_ifExcludeCdata;
    private JLabel label40;
    private JTextField txt_recordRootTag;
    private JLabel label41;
    private JTextField txt_recordBodyTag;
    private JLabel label42;
    private JTextField txt_titleTag;
    private JLabel label43;
    private JTextField txt_titleTagIndex;
    private JLabel label44;
    private JTextField txt_isOwnerText;
    private JLabel label45;
    private JTextField txt_isAttr;
    private JLabel label46;
    private JTextField txt_attrName;
    private JLabel label47;
    private JTextField txt_dateTag;
    private JLabel label48;
    private JTextField txt_dateTagIndex;
    private JLabel label49;
    private JTextField txt_dateTagLocation;
    private JLabel label50;
    private JTextField txt_dateFilterStr;
    private JLabel label51;
    private JTextField txt_urlRecordTag;
    private JPanel panel5;
    private JButton btn_test;
    private JScrollPane scrollPane1;
    private JTextArea txta_test;
    private JPanel panel6;
    private JButton btn_save;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showFrame(NetMonitorClinet parentComponent) {
        this.parentComponent = parentComponent;
        loadLocalProperty();
        listenEventRegister();
        URLAddFrame.setVisible(true);
        URLAddFrame.setDefaultCloseOperation(2);
    }

    private void closeFrame(){
        URLAddFrame.dispose();
    }


}
