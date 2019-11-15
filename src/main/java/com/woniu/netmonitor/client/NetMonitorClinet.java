/*
 * Created by JFormDesigner on Mon Oct 14 16:27:40 CST 2019
 */

package com.woniu.netmonitor.client;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.woniu.netmonitor.configuration.WebSocketConfig;
import com.woniu.netmonitor.dictionary.MessageBoxType;
import com.woniu.netmonitor.entity.ArticleRecord;
import com.woniu.netmonitor.util.*;
import com.woniu.netmonitor.entity.UrlMonitorEntity;
import com.woniu.netmonitor.vo.JsonResult;
import com.woniu.netmonitor.vo.NetInfoQueryParamVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.java_websocket.client.WebSocketClient;
import org.springframework.scheduling.annotation.Async;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @author gyl
 */
@Slf4j
public class NetMonitorClinet {

    /**
     * 参数记录
     */
    private static String serverIp;
    private static String serverPort;
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

    private static String baseUrl;
    private static String baseWsUrl;

    private static volatile Boolean isUpdateReady = true;

    private static List<ArticleRecord> articleRecordsForOneQuery;
    private static List<UrlMonitorEntity> failedUrlsForOneQuery;

    private HttpTransferBean transferBean = (HttpTransferBean) SpringUtil.getBean("httpTransferBean");
    private LocalPropertyUtil localPropertyBean = (LocalPropertyUtil) SpringUtil.getBean("localPropertyBean");
    private WebClientUtil webClientBean = (WebClientUtil) SpringUtil.getBean("webClientBean");
    private WebSocketConfig webSocketConfig = (WebSocketConfig) SpringUtil.getBean("webSocketConfig");

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        monitorFrame = new JFrame();
        subpanel1 = new JPanel();
        hSpacer1 = new JPanel(null);
        server_ip = new JLabel();
        txt_serverIp = new JTextField();
        server_port = new JLabel();
        txt_serverPort = new JTextField();
        hSpacer2 = new JPanel(null);
        subpanel2 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        btn_conn = new JButton();
        panel3 = new JPanel();
        lb_status = new JLabel();
        db_status = new JLabel();
        label4 = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        subpanel3 = new JPanel();
        panel5 = new JPanel();
        monitorList = new JLabel();
        lb_urlNum = new JLabel();
        btn_addUrl = new JButton();
        btn_update = new JButton();
        panel6 = new JPanel();
        lb_updateState = new JLabel();
        lb_alert = new JLabel();
        panel4 = new JPanel();
        scrollPane1 = new JScrollPane();
        txt_monitorList = new JTextArea();
        subpanel5 = new JPanel();
        lb_info1 = new JLabel();
        lb_lastDays = new JLabel();
        txt_days = new JTextField();
        lb_desc = new JLabel();
        cb_desc = new JComboBox<>();
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
                "8*(default, $lgap), default"));

            //======== subpanel1 ========
            {
                subpanel1.setLayout(new FlowLayout());

                //---- hSpacer1 ----
                hSpacer1.setBorder(LineBorder.createBlackLineBorder());
                subpanel1.add(hSpacer1);

                //---- server_ip ----
                server_ip.setText("\u670d\u52a1\u5668ip:");
                subpanel1.add(server_ip);

                //---- txt_serverIp ----
                txt_serverIp.setColumns(10);
                subpanel1.add(txt_serverIp);

                //---- server_port ----
                server_port.setText("\u670d\u52a1\u5668port");
                subpanel1.add(server_port);

                //---- txt_serverPort ----
                txt_serverPort.setColumns(5);
                subpanel1.add(txt_serverPort);

                //---- hSpacer2 ----
                hSpacer2.setBorder(LineBorder.createBlackLineBorder());
                subpanel1.add(hSpacer2);
            }
            monitorFrameContentPane.add(subpanel1, CC.xy(1, 1));

            //======== subpanel2 ========
            {
                subpanel2.setLayout(new GridLayout());
                subpanel2.add(label2);
                subpanel2.add(label3);

                //---- btn_conn ----
                btn_conn.setText("\u8fde\u63a5");
                subpanel2.add(btn_conn);

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- lb_status ----
                    lb_status.setText("\u72b6\u6001:");
                    lb_status.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(lb_status);

                    //---- db_status ----
                    db_status.setText("\u672a\u8fde\u63a5");
                    panel3.add(db_status);
                }
                subpanel2.add(panel3);
                subpanel2.add(label4);
            }
            monitorFrameContentPane.add(subpanel2, CC.xy(1, 3));

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("=======================================================");
                panel1.add(label1);
            }
            monitorFrameContentPane.add(panel1, CC.xy(1, 5));

            //======== subpanel3 ========
            {
                subpanel3.setLayout(new GridLayout());

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout(FlowLayout.RIGHT));

                    //---- monitorList ----
                    monitorList.setText("\u5df2\u76d1\u63a7\u7f51\u7ad9:");
                    monitorList.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel5.add(monitorList);

                    //---- lb_urlNum ----
                    lb_urlNum.setText("0\u4e2a");
                    panel5.add(lb_urlNum);
                }
                subpanel3.add(panel5);

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
            monitorFrameContentPane.add(subpanel3, CC.xy(1, 7));

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(1, 1));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(txt_monitorList);
                }
                panel4.add(scrollPane1);
            }
            monitorFrameContentPane.add(panel4, CC.xy(1, 9));

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
            monitorFrameContentPane.add(subpanel6, CC.xy(1, 13));

            //======== scrollPane2 ========
            {

                //---- txt_cotent ----
                txt_cotent.setPreferredSize(new Dimension(489, 300));
                scrollPane2.setViewportView(txt_cotent);
            }
            monitorFrameContentPane.add(scrollPane2, CC.xy(1, 15));

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
            monitorFrameContentPane.add(panel2, CC.xy(1, 17));
            monitorFrame.pack();
            monitorFrame.setLocationRelativeTo(monitorFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void loadLocalProperty() {
        serverIp = localPropertyBean.getProperty("serverIp");
        serverPort = localPropertyBean.getProperty("serverPort");
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

        txt_serverIp.setText(serverIp);
        txt_serverPort.setText(serverPort);
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

    }

    private void saveLocalProperty() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("serverIp", txt_serverIp.getText());
        stringMap.put("serverPort", txt_serverPort.getText());
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
            messageFrame(MessageBoxType.ERROR, "请检查输入参数");
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

    public void alertFailedUrlsForOneQuery(List<UrlMonitorEntity> urlMonitorEntities){
        if (urlMonitorEntities!=null && urlMonitorEntities.size() > 0){
            lb_alert.setEnabled(true);
            failedUrlsForOneQuery = urlMonitorEntities;
            lb_alert.setIcon(UIManager.getIcon("Table.descendingSortIcon"));
        } else{
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
        StringBuilder urlPath = new StringBuilder(baseUrl);
        urlPath = urlPath.append(transferBean.getRootUrlPath()).
                append(transferBean.getNetArticlePersistenceServerEndPoint());
        JsonResult jsonResult = webClientBean.webClientGetMethodAsync(urlPath.toString(), JsonResult.class);
        long end = System.currentTimeMillis();
        String res;
        if (jsonResult.getReturnCode().equals("SUCC")) {
            res = "更新完成！耗时：" + ((end - start) / 1000) + "秒";
            lb_updateState.setText("更新完成");
        } else {
            res = "更新失败";
            lb_updateState.setText("更新失败");
        }
        messageFrame(MessageBoxType.INFO, res);
    }

    private int messageFrame(MessageBoxType boxType, String message) {
        switch (boxType) {
            case ALERT:
                JOptionPane.showMessageDialog(monitorFrame, message, "警告", JOptionPane.WARNING_MESSAGE);
                break;
            case INFO:
                JOptionPane.showMessageDialog(monitorFrame, message, "提示", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ERROR:
                JOptionPane.showMessageDialog(monitorFrame, message, "错误", JOptionPane.ERROR_MESSAGE);
                break;
            case CONFIRM:
                return JOptionPane.showConfirmDialog(monitorFrame, message, "错误", JOptionPane.YES_NO_OPTION);
            default:
                JOptionPane.showMessageDialog(monitorFrame, message, "提示", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        return 0;
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
        /*
            连接服务器
         */
        btn_conn.addActionListener(e -> {
            serverIp = txt_serverIp.getText();
            serverPort = txt_serverPort.getText();
            saveLocalProperty();
            baseUrl = new StringBuilder("http://").append(serverIp).append(":").append(serverPort).toString();
            baseWsUrl = new StringBuilder("ws://").append(serverIp).append(":").append(serverPort).toString();
            webSocketServiceStart();
            StringBuilder urlPath = new StringBuilder(baseUrl);
            urlPath = urlPath.append(transferBean.getRootUrlPath()).
                    append(transferBean.getNetUrlEntityServerEndpoint());
            JSONObject responseEntity;
            try {
                responseEntity = transferBean.doGetRequestMapping(urlPath.toString());
                JSONArray jsonArray = responseEntity.getJSONArray("data");
                List<UrlMonitorEntity> urlMonitorEntities = JSONArray.toList(jsonArray, new UrlMonitorEntity(), new JsonConfig());
                StringBuilder monitorUrl = new StringBuilder();
                for (UrlMonitorEntity entity : urlMonitorEntities) {
                    monitorUrl.append("    ").append(entity.getName()).append(":\t").append(entity.getConnectUrl()).append("\n");
                }
                txt_monitorList.setText(monitorUrl.toString());
                db_status.setText("已连接");
                lb_urlNum.setText(urlMonitorEntities.size() + "个");
            } catch (Exception e1) {
                e1.printStackTrace();
                messageFrame(MessageBoxType.ERROR, "连接失败,请检查服务器ip或端口是否正确");
                return;
            }
        });

        /**
         * 添加url按钮事件
         */
        btn_addUrl.addActionListener(e -> {
            UrlInfoAdd urlInfoAdd = new UrlInfoAdd(baseUrl);
            urlInfoAdd.showFrame();
        });


        /*
            手动触发更新
         */
        btn_update.addActionListener(e -> {
            if (!db_status.getText().equals("已连接")) {
                messageFrame(MessageBoxType.ALERT, "请先连接服务器!");
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
                        messageFrame(MessageBoxType.ERROR, "更新异常,请检查服务器状态是否正常");
                        lb_updateState.setText("更新失败");
                        isUpdateReady = true;
                        return;
                    }
                    isUpdateReady = true;
                }).start();
            } else {
                messageFrame(MessageBoxType.ALERT, "请勿重复点击！");
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
                messageFrame(MessageBoxType.ALERT, "以下网址解析异常\n"+ builder.toString());
            }
        });
        /*
            查询显示
         */
        btn_query.addActionListener(e -> {
            if (!db_status.getText().equals("已连接")) {
                messageFrame(MessageBoxType.ALERT, "请先连接服务器!");
                return;
            }
            saveLocalProperty();//保存参数
            NetInfoQueryParamVo infoQueryParamVo = getQueryParamVo();
            if (infoQueryParamVo == null) {
                return;
            }
            StringBuilder urlPath = new StringBuilder(baseUrl);
            urlPath = urlPath.append(transferBean.getRootUrlPath()).
                    append(transferBean.getNetArticleRecordServerEndPoint());
            JSONObject responseEntity = null;
            try {
                responseEntity = transferBean.doPostRequestMapping(urlPath.toString(), infoQueryParamVo);
            } catch (Exception e1) {
                e1.printStackTrace();
                messageFrame(MessageBoxType.ERROR, "查询失败,请检查服务器状态是否正常");
                return;
            }
            JSONArray jsonArray = responseEntity.getJSONArray("data");
            List<ArticleRecord> articleRecords = JSONArray.toList(jsonArray, new ArticleRecord(), new JsonConfig());
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
                messageFrame(MessageBoxType.ALERT, "下载路径或者文件名不能为空！");
                return;
            }
            if (articleRecordsForOneQuery == null || articleRecordsForOneQuery.size() == 0) {
                messageFrame(MessageBoxType.INFO, "未查询内容或查询的内容为空");
                return;
            }
            try {
                downloadToLocal(downloadPath, fileName, articleRecordsForOneQuery);
                int response = messageFrame(MessageBoxType.CONFIRM, "下载完成，是否打开文件位置？");
                if (response == 0) {
                    java.awt.Desktop.getDesktop().open(new java.io.File(downloadPath));
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                messageFrame(MessageBoxType.ERROR, "下载至本地时出错\n错误内容:" + e1.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
                messageFrame(MessageBoxType.ERROR, "打开路径失败:" + e1.toString());
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
                    messageFrame(MessageBoxType.ERROR, "打开链接失败");
                }
            }
        });

    }

    private void addUrlComponent(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel lb_urlName = new JLabel();
        JTextField textField = new JTextField();
        JButton btn_active = new JButton("启动");

    }

    private void activeUrlComponent(){

    }


    private void webSocketServiceStart() {
        log.info("开始连接WebSocket服务...");
        try {
            webSocketConfig.initFrameInstance(this);
            webSocketConfig.getSocketClient(baseUrl);
        } catch (Exception e1) {
            log.error("websocket连接异常{}",e1.toString());
            messageFrame(MessageBoxType.ERROR, "websocket连接异常");
        }
    }



    public void showFrame() {
        initComponents();
        loadLocalProperty();

        listenEventRegister();

        monitorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        monitorFrame.setVisible(true);
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame monitorFrame;
    private JPanel subpanel1;
    private JPanel hSpacer1;
    private JLabel server_ip;
    private JTextField txt_serverIp;
    private JLabel server_port;
    private JTextField txt_serverPort;
    private JPanel hSpacer2;
    private JPanel subpanel2;
    private JLabel label2;
    private JLabel label3;
    private JButton btn_conn;
    private JPanel panel3;
    private JLabel lb_status;
    private JLabel db_status;
    private JLabel label4;
    private JPanel panel1;
    private JLabel label1;
    private JPanel subpanel3;
    private JPanel panel5;
    private JLabel monitorList;
    private JLabel lb_urlNum;
    private JButton btn_addUrl;
    private JButton btn_update;
    private JPanel panel6;
    private JLabel lb_updateState;
    private JLabel lb_alert;
    private JPanel panel4;
    private JScrollPane scrollPane1;
    private JTextArea txt_monitorList;
    private JPanel subpanel5;
    private JLabel lb_info1;
    private JLabel lb_lastDays;
    private JTextField txt_days;
    private JLabel lb_desc;
    private JComboBox<String> cb_desc;
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
