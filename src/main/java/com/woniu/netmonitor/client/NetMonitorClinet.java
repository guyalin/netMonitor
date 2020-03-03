/*
 * Created by JFormDesigner on Mon Oct 14 16:27:40 CST 2019
 */

package com.woniu.netmonitor.client;

import com.alibaba.fastjson.JSONObject;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.woniu.netmonitor.configuration.WebSocketConfig;
import com.woniu.netmonitor.dictionary.MessageBoxType;
import com.woniu.netmonitor.entity.ArticleRecord;
import com.woniu.netmonitor.util.*;
import com.woniu.netmonitor.entity.UrlMonitorEntity;
import com.woniu.netmonitor.vo.AuthUserInfo;
import com.woniu.netmonitor.vo.JsonResult;
import com.woniu.netmonitor.vo.NetInfoQueryParamVo;
import com.woniu.netmonitor.vo.NetLabelVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyl
 */
@Slf4j
public class NetMonitorClinet {

    private static Integer lastDays;
    private static Integer descOrder;
    private static String titleContentFilter;
    private static Boolean ckArea;
    private static Boolean ckNetName;
    private static String area;
    private static String netName;
    private static Boolean ifDownload;
    private static String downloadPath;
    private static String downloadFileName;
    private static Integer netLabelIndex;

    private static volatile Boolean isUpdateReady = true;

    private List<ArticleRecord> articleRecordsForOneQuery;
    private List<UrlMonitorEntity> failedUrlsForOneQuery;

    private HttpTransferBean transferBean = (HttpTransferBean) SpringUtil.getBean("httpTransferBean");
    private ServerEndpointBean serverEndpointBean = (ServerEndpointBean) SpringUtil.getBean("serverEndpointBean");
    private LocalPropertyUtil localPropertyBean = (LocalPropertyUtil) SpringUtil.getBean("localPropertyBean");
    private WebClientUtil webClientBean = (WebClientUtil) SpringUtil.getBean("webClientBean");
    private WebSocketConfig webSocketConfig = (WebSocketConfig) SpringUtil.getBean("webSocketConfig");

    private MonitorList monitorList;
    private NetLabelManager netLabelManager;

    private Boolean connectedFlag = false;

    //private  String baseUrl;
    private AuthUserInfo authUserInfo;

    private List<NetLabelVo> netLabelVos;

    private List<UrlMonitorEntity> urlMonitorFullEntities;

    public NetMonitorClinet() {
        this.authUserInfo = webClientBean.getAuthUserInfo();

    }

    private void initComponents() throws Exception {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        monitorFrame = new JFrame();
        subpanel1 = new JPanel();
        panel9 = new JPanel();
        label4 = new JLabel();
        lb_userName = new JLabel();
        label5 = new JLabel();
        lb_lastTime = new JLabel();
        panel11 = new JPanel();
        panel10 = new JPanel();
        label2 = new JLabel();
        cb_label = new JComboBox();
        btn_labelManager = new JButton();
        panel8 = new JPanel();
        subpanel2 = new JPanel();
        label6 = new JLabel();
        btn_showList = new JButton();
        btn_conn = new JButton();
        panel3 = new JPanel();
        lb_status = new JLabel();
        db_status = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        panel7 = new JPanel();
        subpanel3 = new JPanel();
        label7 = new JLabel();
        btn_addUrl = new JButton();
        btn_update = new JButton();
        panel6 = new JPanel();
        lb_updateState = new JLabel();
        lb_alert = new JLabel();
        subpanel5 = new JPanel();
        lb_info1 = new JLabel();
        lb_lastDays = new JLabel();
        txt_days = new JTextField();
        lb_desc = new JLabel();
        cb_desc = new JComboBox<>();
        panel5 = new JPanel();
        subpanel6 = new JPanel();
        lb_info2 = new JLabel();
        lb_titleFilter = new JLabel();
        txt_titleFilter = new JTextField();
        lb_filterMeta = new JLabel();
        subpanel6_1 = new JPanel();
        ck_area = new JCheckBox();
        txt_areaFilter = new JTextField();
        ck_netName = new JCheckBox();
        txt_netName = new JTextField();
        btn_query = new JButton();
        scrollPane2 = new JScrollPane();
        txt_cotent = new JEditorPane();
        panel4 = new JPanel();
        panel2 = new JPanel();
        ckb_download = new JCheckBox();
        panel_download = new JPanel();
        lb_path = new JLabel();
        txt_path = new JTextField();
        btn_chooseFile = new JButton();
        lb_fileName = new JLabel();
        txt_fileName = new JTextField();
        btn_download = new JButton();

        //======== monitorFrame ========
        {
            monitorFrame.setTitle("netMonitor");
            Container monitorFrameContentPane = monitorFrame.getContentPane();
            monitorFrameContentPane.setLayout(new FormLayout(
                "default",
                "9*(default, $lgap), default"));

            //======== subpanel1 ========
            {
                subpanel1.setLayout(new FlowLayout());

                //======== panel9 ========
                {
                    panel9.setLayout(new GridLayout(1, 5));

                    //---- label4 ----
                    label4.setText("\u6b22\u8fce\u60a8\uff01");
                    label4.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel9.add(label4);

                    //---- lb_userName ----
                    lb_userName.setHorizontalAlignment(SwingConstants.CENTER);
                    panel9.add(lb_userName);

                    //---- label5 ----
                    label5.setText("\u4e0a\u6b21\u767b\u5f55\u65f6\u95f4\uff1a");
                    label5.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel9.add(label5);
                }
                subpanel1.add(panel9);
                subpanel1.add(lb_lastTime);
            }
            monitorFrameContentPane.add(subpanel1, CC.xy(1, 1));

            //======== panel11 ========
            {
                panel11.setLayout(new FlowLayout());

                //======== panel10 ========
                {
                    panel10.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u9009\u62e9\u6807\u7b7e");
                    panel10.add(label2);

                    //---- cb_label ----
                    cb_label.setMaximumRowCount(10);
                    panel10.add(cb_label);

                    //---- btn_labelManager ----
                    btn_labelManager.setText("\u6807\u7b7e\u7ba1\u7406");
                    panel10.add(btn_labelManager);
                }
                panel11.add(panel10);
            }
            monitorFrameContentPane.add(panel11, CC.xy(1, 3));

            //======== panel8 ========
            {
                panel8.setLayout(new FlowLayout());

                //======== subpanel2 ========
                {
                    subpanel2.setLayout(new GridLayout());
                    subpanel2.add(label6);

                    //---- btn_showList ----
                    btn_showList.setText("\u663e\u793a\u76d1\u542c\u5217\u8868");
                    subpanel2.add(btn_showList);

                    //---- btn_conn ----
                    btn_conn.setText("\u5237\u65b0\u8fde\u63a5");
                    subpanel2.add(btn_conn);

                    //======== panel3 ========
                    {
                        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 12));

                        //---- lb_status ----
                        lb_status.setText("\u72b6\u6001:");
                        lb_status.setHorizontalAlignment(SwingConstants.CENTER);
                        panel3.add(lb_status);

                        //---- db_status ----
                        db_status.setText("\u672a\u8fde\u63a5");
                        panel3.add(db_status);
                    }
                    subpanel2.add(panel3);
                }
                panel8.add(subpanel2);
            }
            monitorFrameContentPane.add(panel8, CC.xy(1, 5));

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("=======================================================");
                panel1.add(label1);
            }
            monitorFrameContentPane.add(panel1, CC.xy(1, 7));

            //======== panel7 ========
            {
                panel7.setLayout(new FlowLayout());

                //======== subpanel3 ========
                {
                    subpanel3.setLayout(new GridLayout());
                    subpanel3.add(label7);

                    //---- btn_addUrl ----
                    btn_addUrl.setText("\u6dfb\u52a0");
                    subpanel3.add(btn_addUrl);

                    //---- btn_update ----
                    btn_update.setText("\u66f4\u65b0");
                    subpanel3.add(btn_update);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- lb_updateState ----
                        lb_updateState.setPreferredSize(new Dimension(63, 17));
                        panel6.add(lb_updateState);

                        //---- lb_alert ----
                        lb_alert.setIcon(UIManager.getIcon("Table.descendingSortIcon"));
                        lb_alert.setMaximumSize(new Dimension(16, 16));
                        lb_alert.setMinimumSize(new Dimension(16, 16));
                        panel6.add(lb_alert);
                    }
                    subpanel3.add(panel6);
                }
                panel7.add(subpanel3);
            }
            monitorFrameContentPane.add(panel7, CC.xy(1, 9));

            //======== subpanel5 ========
            {
                subpanel5.setLayout(new FlowLayout());

                //---- lb_info1 ----
                lb_info1.setText("\u5fc5\u9009\u53c2\u6570:");
                subpanel5.add(lb_info1);

                //---- lb_lastDays ----
                lb_lastDays.setText("\u6700\u8fd1\u5929\u6570");
                subpanel5.add(lb_lastDays);

                //---- txt_days ----
                txt_days.setColumns(3);
                txt_days.setText("30");
                subpanel5.add(txt_days);

                //---- lb_desc ----
                lb_desc.setText("\u6392\u5e8f\u65b9\u5f0f");
                subpanel5.add(lb_desc);

                //---- cb_desc ----
                cb_desc.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u65f6\u95f4 \u7f51\u7ad9",
                    "\u7f51\u7ad9 \u65f6\u95f4"
                }));
                subpanel5.add(cb_desc);
            }
            monitorFrameContentPane.add(subpanel5, CC.xy(1, 11));

            //======== panel5 ========
            {
                panel5.setLayout(new FlowLayout());

                //======== subpanel6 ========
                {
                    subpanel6.setLayout(new BoxLayout(subpanel6, BoxLayout.X_AXIS));

                    //---- lb_info2 ----
                    lb_info2.setText("\u53ef\u9009\u53c2\u6570:");
                    lb_info2.setHorizontalAlignment(SwingConstants.RIGHT);
                    lb_info2.setPreferredSize(new Dimension(80, 30));
                    subpanel6.add(lb_info2);

                    //---- lb_titleFilter ----
                    lb_titleFilter.setText("<html><body>\u6807\u9898\u8fc7\u6ee4<br>(\u4ee5\u5206\u53f7\u5206\u9694):</body>");
                    lb_titleFilter.setHorizontalAlignment(SwingConstants.CENTER);
                    subpanel6.add(lb_titleFilter);

                    //---- txt_titleFilter ----
                    txt_titleFilter.setColumns(10);
                    txt_titleFilter.setPreferredSize(new Dimension(150, 30));
                    subpanel6.add(txt_titleFilter);

                    //---- lb_filterMeta ----
                    lb_filterMeta.setText("\u8fc7\u6ee4\u6761\u4ef6:");
                    subpanel6.add(lb_filterMeta);

                    //======== subpanel6_1 ========
                    {
                        subpanel6_1.setLayout(new GridLayout(2, 0));

                        //---- ck_area ----
                        ck_area.setText("\u5730\u533a:");
                        subpanel6_1.add(ck_area);

                        //---- txt_areaFilter ----
                        txt_areaFilter.setColumns(5);
                        subpanel6_1.add(txt_areaFilter);

                        //---- ck_netName ----
                        ck_netName.setText("\u7f51\u7ad9\u540d:");
                        subpanel6_1.add(ck_netName);

                        //---- txt_netName ----
                        txt_netName.setColumns(5);
                        subpanel6_1.add(txt_netName);
                    }
                    subpanel6.add(subpanel6_1);

                    //---- btn_query ----
                    btn_query.setText("\u67e5\u8be2");
                    btn_query.setActionCommand("query");
                    subpanel6.add(btn_query);
                }
                panel5.add(subpanel6);
            }
            monitorFrameContentPane.add(panel5, CC.xy(1, 13));

            //======== scrollPane2 ========
            {
                scrollPane2.setPreferredSize(null);

                //---- txt_cotent ----
                txt_cotent.setPreferredSize(new Dimension(0, 300));
                scrollPane2.setViewportView(txt_cotent);
            }
            monitorFrameContentPane.add(scrollPane2, CC.xy(1, 15));

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout());

                //======== panel2 ========
                {
                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

                    //---- ckb_download ----
                    ckb_download.setText("\u4e0b\u8f7d");
                    panel2.add(ckb_download);

                    //======== panel_download ========
                    {
                        panel_download.setLayout(new BoxLayout(panel_download, BoxLayout.X_AXIS));

                        //---- lb_path ----
                        lb_path.setText("\u4e0b\u8f7d\u8def\u5f84:");
                        panel_download.add(lb_path);

                        //---- txt_path ----
                        txt_path.setColumns(15);
                        panel_download.add(txt_path);

                        //---- btn_chooseFile ----
                        btn_chooseFile.setText("...");
                        btn_chooseFile.setIconTextGap(1);
                        btn_chooseFile.setMaximumSize(new Dimension(15, 30));
                        btn_chooseFile.setMinimumSize(new Dimension(15, 30));
                        btn_chooseFile.setPreferredSize(new Dimension(20, 30));
                        panel_download.add(btn_chooseFile);

                        //---- lb_fileName ----
                        lb_fileName.setText("\u6587\u4ef6\u540d:");
                        panel_download.add(lb_fileName);

                        //---- txt_fileName ----
                        txt_fileName.setColumns(3);
                        txt_fileName.setText("article.txt");
                        panel_download.add(txt_fileName);

                        //---- btn_download ----
                        btn_download.setText("\u4e0b\u8f7d");
                        btn_download.setActionCommand("download");
                        panel_download.add(btn_download);
                    }
                    panel2.add(panel_download);
                }
                panel4.add(panel2);
            }
            monitorFrameContentPane.add(panel4, CC.xy(1, 17));
            monitorFrame.pack();
            monitorFrame.setLocationRelativeTo(monitorFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        lb_userName.setText(authUserInfo.getUserName());
        lb_lastTime.setText(authUserInfo.getLastLoginTime());
        //netLabelVos = authUserInfo.getNetLabelVos();
        refreshLabelCombox();
        cb_label.setVisible(true);
    }

    private void loadLocalProperty() {
        lastDays = ((StringUtils.isEmpty(localPropertyBean.getProperty("lastDays")) ||
                !localPropertyBean.getProperty("lastDays").matches("[0-9]*")) ?
                30 : Integer.parseInt(localPropertyBean.getProperty("lastDays")));
        descOrder = ((StringUtils.isEmpty(localPropertyBean.getProperty("descOrder")) ||
                !localPropertyBean.getProperty("descOrder").matches("[0-9]")) ?
                0 : Integer.parseInt(localPropertyBean.getProperty("descOrder")));
        titleContentFilter = localPropertyBean.getProperty("titleContentFilter");
        ckArea = (new Boolean(localPropertyBean.getProperty("ckArea"))).booleanValue();
        ckNetName = (new Boolean(localPropertyBean.getProperty("ckNetName"))).booleanValue();
        area = localPropertyBean.getProperty("area");
        netName = localPropertyBean.getProperty("netName");
        ifDownload = (new Boolean(localPropertyBean.getProperty("ckDownload"))).booleanValue();
        downloadPath = localPropertyBean.getProperty("downloadPath");
        downloadFileName = localPropertyBean.getProperty("downloadFileName");
        netLabelIndex = ((StringUtils.isEmpty(localPropertyBean.getProperty("netLabelIndex")) ||
                !localPropertyBean.getProperty("netLabelIndex").matches("[0-9]")) ?
                0 : Integer.parseInt(localPropertyBean.getProperty("netLabelIndex")));
        if (netLabelIndex >= cb_label.getItemCount())
            netLabelIndex = 0;

        txt_days.setText(lastDays.toString());
        cb_desc.setSelectedIndex(descOrder);
        txt_titleFilter.setText(titleContentFilter);
        if (ckArea) {
            ck_area.setSelected(true);
        }
        if (ckNetName) {
            ck_netName.setSelected(true);
        }
        txt_areaFilter.setText(area);
        txt_netName.setText(netName);
        //下载相关
        ckb_download.setSelected(ifDownload);
        txt_path.setText(downloadPath);
        txt_fileName.setText(downloadFileName);
        cb_label.setSelectedIndex(netLabelIndex);
    }

    private void saveLocalProperty() {
        Map<String, String> stringMap = new HashMap<>();
        //查询相关
        stringMap.put("lastDays", txt_days.getText());
        stringMap.put("descOrder", String.valueOf(cb_desc.getSelectedIndex()));
        stringMap.put("titleContentFilter", txt_titleFilter.getText());
        stringMap.put("ckArea", Boolean.toString(ck_area.isSelected()));
        stringMap.put("ckNetName", Boolean.toString(ck_netName.isSelected()));
        stringMap.put("area", txt_areaFilter.getText());
        stringMap.put("netName", txt_netName.getText());
        //下载相关
        stringMap.put("ckDownload", Boolean.toString(ckb_download.isSelected()));
        stringMap.put("downloadPath", txt_path.getText());
        stringMap.put("downloadFileName", txt_fileName.getText());

        stringMap.put("netLabelIndex", String.valueOf(cb_label.getSelectedIndex()));

        localPropertyBean.saveProperty(stringMap);
    }

    /**
     * 查询按钮事件的参数封装
     *
     * @return
     */
    private NetInfoQueryParamVo getQueryParamVo() {
        NetInfoQueryParamVo netInfoQueryParamVo = new NetInfoQueryParamVo();
        try {
            lastDays = StringUtils.isBlank(txt_days.getText()) ? null :
                    (txt_days.getText().matches("\\d+") ? Integer.parseInt(txt_days.getText()) : null);
            descOrder = cb_desc.getSelectedIndex();
            titleContentFilter = txt_titleFilter.getText();
            ckArea = ck_area.isSelected();
            ckNetName = ck_netName.isSelected();
            area = txt_areaFilter.getText();
            netName = txt_netName.getText();

            String netList = ((NetLabelVo) cb_label.getSelectedItem()).getNetList();
            netInfoQueryParamVo.setNetList(netList);
            netInfoQueryParamVo.setLatestDays(lastDays);
            netInfoQueryParamVo.setDescType(descOrder);
            if (!StringUtils.isEmpty(area) && ckArea) {
                netInfoQueryParamVo.setArea(area);
            }
            if (!StringUtils.isEmpty(netName) && ckNetName) {
                netInfoQueryParamVo.setNetName(netName);
            }
            if (!StringUtils.isEmpty(titleContentFilter)) {
                List<String> args = Arrays.asList(titleContentFilter.split(";"));
                netInfoQueryParamVo.setArticleTitleRegex(args);
            }
            return netInfoQueryParamVo;
        } catch (Exception e) {
            log.error("参数解析出错！请检查.", e);
            JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "请检查输入参数");
            return null;
        }

    }

    private void fileIfDownload() {
        if (!ckb_download.isSelected()) {
            btn_download.setEnabled(false);
            btn_chooseFile.setEnabled(false);
            txt_path.setEditable(false);
            txt_fileName.setEditable(false);
        } else {
            btn_download.setEnabled(true);
            btn_chooseFile.setEnabled(true);
            txt_path.setEditable(true);
            txt_fileName.setEditable(true);
        }
    }

    /**
     * 保存至本地文件
     *
     * @param localFilePath
     * @param fileName
     */
    private void downloadToLocal(String localFilePath, String fileName, List<ArticleRecord> articleRecords) throws FileNotFoundException {
        LocalFileWriterBean fileWriterBean = new LocalFileWriterBean(localFilePath, fileName);
        fileWriterBean.saveToFile(articleRecords);
    }

    public void alertFailedUrlsForOneQuery(List<UrlMonitorEntity> urlMonitorEntities) {
        if (urlMonitorEntities != null && urlMonitorEntities.size() > 0) {
            lb_alert.setEnabled(true);
            failedUrlsForOneQuery = urlMonitorEntities;
            lb_alert.setIcon(UIManager.getIcon("Table.descendingSortIcon"));
        } else {
            lb_alert.setIcon(null);
            lb_alert.setEnabled(false); //没有失败的网址，则取消标签的点击事件。
        }
    }

    @Async
    public void updateAsyncTask() throws Exception {
        isUpdateReady = false;
        long start = System.currentTimeMillis();
        lb_updateState.setVisible(true);
        lb_updateState.setText("更新中...");
        NetLabelVo labelVo = (NetLabelVo) cb_label.getSelectedItem();
        JsonResult jsonResult = webClientBean.webClientPostMethodAsync(serverEndpointBean.getNetArticlePersistenceServerEndPoint(), JsonResult.class, labelVo.getNetList());
        long end = System.currentTimeMillis();
        String res;
        if (jsonResult.getReturnCode().equals("SUCC")) {
            res = "更新完成！耗时：" + ((end - start) / 1000) + "秒";
            lb_updateState.setText("更新完成");
        } else {
            res = "更新失败";
            lb_updateState.setText("更新失败");
        }
        JFrameUtil.messageFrame(monitorFrame, MessageBoxType.INFO, res);
    }


    /**
     * 打开文件选择器
     */
    private void fileChooseFrame() {
        int fileSelectionMode = JFileChooser.DIRECTORIES_ONLY;
        String downPath = txt_path.getText();
        JFileChooser jFileChooser = new JFileChooser(downPath);
        jFileChooser.setFileSelectionMode(fileSelectionMode); //仅目录可选
        jFileChooser.setMultiSelectionEnabled(false); // 不允许多选
        int i = jFileChooser.showDialog(monitorFrame, "选择");
        if (i == jFileChooser.APPROVE_OPTION) {
            String filePath = jFileChooser.getSelectedFile().getPath();
            txt_path.setText(filePath);
        }
    }

    public void listenEventRegister() {
        /**
         *  刷新连接
         */
        btn_conn.addActionListener(e -> {
            try {
                connectServerGetUrlList();
            } catch (Exception e1) {
                e1.printStackTrace();
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "服务器连接失败，请关闭客户端重试");
            }
        });

        //checkBox状态改变，同时改变下拉列表
        cb_label.addItemListener(e -> {
            fillListeningNetTags(urlMonitorFullEntities, cb_label.getSelectedIndex());
        });

        /**
         * 标签管理
         */
        btn_labelManager.addActionListener(e -> {
            if (netLabelManager != null) {
                netLabelManager.closeFrame();
            }
            netLabelManager = new NetLabelManager(netLabelVos, urlMonitorFullEntities, this);
            netLabelManager.showFrame();
        });
        /**
         * 展示列表
         */
        btn_showList.addActionListener(e -> {
            if (monitorList == null) {
                return;
            }
            monitorList.showFrame();
        });

        /**
         * 添加url按钮事件
         */
        btn_addUrl.addActionListener(e -> {
            if (!connectedFlag) {
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ALERT, "请先连接服务器!");
                return;
            }
            UrlInfoAdd urlInfoAdd = new UrlInfoAdd();
            urlInfoAdd.showFrame(this);
        });


        /*
            手动触发更新
         */
        btn_update.addActionListener(e -> {
            if (!connectedFlag) {
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ALERT, "请先连接服务器!");
                return;
            }
            failedUrlsForOneQuery = null;
            if (isUpdateReady) {
                new Thread(() -> {
                    try {
                        updateAsyncTask();
                        alertFailedUrlsForOneQuery(failedUrlsForOneQuery);
                        //lb_updateState.setText("更新完成");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "更新异常,请检查服务器状态是否正常");
                        lb_updateState.setText("更新失败");
                        isUpdateReady = true;
                        return;
                    }
                    isUpdateReady = true;
                }).start();
            } else {
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ALERT, "请勿重复点击！");
            }

        });
        //初始时不可点击
        lb_alert.setIcon(null);
        lb_alert.setEnabled(false);
        lb_alert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder builder = new StringBuilder();
                for (UrlMonitorEntity entity : failedUrlsForOneQuery) {
                    builder.append(entity.getName()).append("\n");
                }
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ALERT, "以下网址解析异常\n" + builder.toString());
            }
        });
        /*
            查询显示
         */
        btn_query.addActionListener(e -> {
            if (!db_status.getText().equals("已连接")) {
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ALERT, "请先连接服务器!");
                return;
            }
            saveLocalProperty();//保存参数
            NetInfoQueryParamVo infoQueryParamVo = getQueryParamVo();
            if (infoQueryParamVo == null) {
                return;
            }
            JsonResult jsonResult = null;
            try {
                jsonResult = webClientBean.webClientPostMethodAsync(
                        serverEndpointBean.getNetArticleRecordServerEndPoint(), JsonResult.class, infoQueryParamVo);
            } catch (Exception e1) {
                e1.printStackTrace();
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "查询失败,请检查服务器状态是否正常");
                return;
            }
            List<ArticleRecord> articleRecords = JSONObject.parseArray(JSONObject.toJSONString(jsonResult.getData()), ArticleRecord.class);
            articleRecordsForOneQuery = articleRecords; //保存在全局变量中，需要下载时直接用
            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>");
            for (ArticleRecord record : articleRecords) {
                sb.append("netName: ").append(record.getArticleName()).append("<br>");
                sb.append("link: ")
                        .append("<A href='").append(record.getTargetUrl()).append("'>")
                        .append(record.getTargetUrl()).append("</A>").append("<br>");
                sb.append("text: ").append(record.getArticleTitle()).append("<br>");
                sb.append("date: ").append(record.getDateTime()).append("<br>");
                sb.append("<br>");
            }
            sb.append("</body></html>");
            if (StringUtils.isBlank(sb.toString())) {
                txt_cotent.setText("未查询到相关记录...");
            } else {
                txt_cotent.setText(sb.toString());
            }

        });

        /**
         * 是否需要下载
         */
        fileIfDownload(); //界面渲染时的初始状态
        ckb_download.addActionListener(e -> {
            fileIfDownload();// 点击复选框后的状态
        });

        /*
            下载至本地
         */
        btn_download.addActionListener(e -> {
            saveLocalProperty();
            if (!ckb_download.isSelected()) {
                return;
            }
            String downloadPath = txt_path.getText();
            String fileName = txt_fileName.getText();
            if (StringUtils.isEmpty(downloadPath) || StringUtils.isEmpty(fileName)) {
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ALERT, "下载路径或者文件名不能为空！");
                return;
            }
            if (articleRecordsForOneQuery == null || articleRecordsForOneQuery.size() == 0) {
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.INFO, "未查询内容或查询的内容为空");
                return;
            }
            try {
                downloadToLocal(downloadPath, fileName, articleRecordsForOneQuery);
                int response = JFrameUtil.messageFrame(monitorFrame, MessageBoxType.CONFIRM, "下载完成，是否打开文件位置？");
                if (response == 0) {
                    java.awt.Desktop.getDesktop().open(new java.io.File(downloadPath));
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "下载至本地时出错\n错误内容:" + e1.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
                JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "打开路径失败:" + e1.toString());
            }

        });
        /**
         * 打开文件选择器
         */
        btn_chooseFile.addActionListener(e -> {
            fileChooseFrame();
        });

        /**
         * 添加超链接事件监听
         */
        txt_cotent.setEditable(false);
        txt_cotent.setContentType("text/html");
        txt_cotent.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    String command = "rundll32 url.dll,FileProtocolHandler "
                            + e.getURL().toString();
                    Runtime.getRuntime().exec(command);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "打开链接失败");
                }
            }
        });

        /**
         * 标签下拉框选择监听事件
         */
        //
    }

    /**
     * 刷新标签下拉列表
     */
    public void refreshLabelCombox() {
        cb_label.removeAllItems();
        cb_label.addItem(new NetLabelVo("全部", ""));
        JsonResult jsonResult = webClientBean.webClientGetMethodAsync(
                serverEndpointBean.getNetLabelQueryEndpoint(),
                JsonResult.class);
        netLabelVos = JSONObject.parseArray(JSONObject.toJSONString(jsonResult.getData()), NetLabelVo.class);
        netLabelVos.forEach(netLabelVo -> {
            cb_label.addItem(netLabelVo);
        });
        //更新标签管理页面的标签列表
        if (netLabelManager != null){
            netLabelManager.updateListLabel(netLabelVos);
        }
        //服务器添加查询接口，根据用户信息。
        //把主页面引用传递到标签管理页面。
        //服务器增加标签添加或修改接口。
        //服务器增加标签删除接口。

    }

    /**
     * 连接服务器
     */
    private void connectServerGetUrlList() throws Exception {
        saveLocalProperty();
        JsonResult jsonResult;
        try {
            //responseEntity = transferBean.doGetRequestMapping(urlPath.toString());
            jsonResult = webClientBean.webClientGetMethodAsync(serverEndpointBean.getNetUrlEntityServerEndpoint(), JsonResult.class);
            /**
             * 新增标签查询信息。标签显示要和总url列表联合
             */
            webSocketServiceStart();
            connectedFlag = true;
            db_status.setText("已连接");
            List<UrlMonitorEntity> urlMonitorEntities = JSONObject.parseArray(JSONObject.toJSONString(jsonResult.getData()), UrlMonitorEntity.class);
            urlMonitorFullEntities = urlMonitorEntities;

            fillListeningNetTags(urlMonitorFullEntities, cb_label.getSelectedIndex());

        } catch (Exception e1) {
            //e1.printStackTrace();
            connectedFlag = false;
            if (e1 instanceof HttpClientErrorException) {
                if (((HttpClientErrorException) e1).getRawStatusCode() == 403) {
                    JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "用户校验失败");
                }
            }
            throw e1;
        }
    }

    private void fillListeningNetTags(List<UrlMonitorEntity> urlMonitorFullEntities, Integer index) {
        List<UrlMonitorEntity> urlMonitorEntities;
        if (index <= 0)
            urlMonitorEntities = urlMonitorFullEntities;
        else {
            NetLabelVo netLabelVo = (NetLabelVo) cb_label.getItemAt(index);
            if (StringUtils.isBlank(netLabelVo.getNetList()))
                return;
            List<String> netList = Arrays.asList(netLabelVo.getNetList().split(","));

            urlMonitorEntities = urlMonitorFullEntities.stream().
                    filter(e -> netList.contains(e.getUrlId())).collect(Collectors.toList());
        }

        StringBuilder monitorUrl = new StringBuilder();
        for (UrlMonitorEntity entity : urlMonitorEntities) {
            monitorUrl.append("    ").append(entity.getName()).append(":\t").append(entity.getConnectUrl()).append("\n");
        }
        if (monitorList != null)
            monitorList.closeFrame();
        monitorList = new MonitorList();
        monitorList.setUrlList(monitorUrl.toString(), urlMonitorEntities.size());
    }


    private void webSocketServiceStart() {
        log.info("开始连接WebSocket服务...");
        try {
            webSocketConfig.initFrameInstance(this);
            webSocketConfig.getSocketClient(webClientBean.getBaseUrl());
        } catch (Exception e1) {
            log.error("websocket连接异常{}", e1.toString());
            JFrameUtil.messageFrame(monitorFrame, MessageBoxType.ERROR, "websocket连接异常");
        }
    }


    /**
     * 显示业务界面，连接服务器
     */
    public void showFrame() throws Exception {
        initComponents();
        loadLocalProperty();
        connectServerGetUrlList(); //连接服务器
        listenEventRegister();
        monitorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //monitorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        monitorFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                webSocketConfig.disConnectServer();
                super.windowClosing(e);
            }
        });
        monitorFrame.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame monitorFrame;
    private JPanel subpanel1;
    private JPanel panel9;
    private JLabel label4;
    private JLabel lb_userName;
    private JLabel label5;
    private JLabel lb_lastTime;
    private JPanel panel11;
    private JPanel panel10;
    private JLabel label2;
    private JComboBox cb_label;
    private JButton btn_labelManager;
    private JPanel panel8;
    private JPanel subpanel2;
    private JLabel label6;
    private JButton btn_showList;
    private JButton btn_conn;
    private JPanel panel3;
    private JLabel lb_status;
    private JLabel db_status;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel7;
    private JPanel subpanel3;
    private JLabel label7;
    private JButton btn_addUrl;
    private JButton btn_update;
    private JPanel panel6;
    private JLabel lb_updateState;
    private JLabel lb_alert;
    private JPanel subpanel5;
    private JLabel lb_info1;
    private JLabel lb_lastDays;
    private JTextField txt_days;
    private JLabel lb_desc;
    private JComboBox<String> cb_desc;
    private JPanel panel5;
    private JPanel subpanel6;
    private JLabel lb_info2;
    private JLabel lb_titleFilter;
    private JTextField txt_titleFilter;
    private JLabel lb_filterMeta;
    private JPanel subpanel6_1;
    private JCheckBox ck_area;
    private JTextField txt_areaFilter;
    private JCheckBox ck_netName;
    private JTextField txt_netName;
    private JButton btn_query;
    private JScrollPane scrollPane2;
    private JEditorPane txt_cotent;
    private JPanel panel4;
    private JPanel panel2;
    private JCheckBox ckb_download;
    private JPanel panel_download;
    private JLabel lb_path;
    private JTextField txt_path;
    private JButton btn_chooseFile;
    private JLabel lb_fileName;
    private JTextField txt_fileName;
    private JButton btn_download;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
