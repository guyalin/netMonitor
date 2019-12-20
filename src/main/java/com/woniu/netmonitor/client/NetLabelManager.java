/*
 * Created by JFormDesigner on Sun Dec 15 23:09:38 CST 2019
 */

package com.woniu.netmonitor.client;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.woniu.netmonitor.client.customComponent.SelectedList;
import com.woniu.netmonitor.dictionary.MessageBoxType;
import com.woniu.netmonitor.entity.UrlMonitorEntity;
import com.woniu.netmonitor.util.*;
import com.woniu.netmonitor.vo.JsonResult;
import com.woniu.netmonitor.vo.NetLabelVo;
import org.apache.commons.lang.StringUtils;

/**
 * @author sa
 */
public class NetLabelManager {

    private ServerEndpointBean serverEndpointBean = (ServerEndpointBean) SpringUtil.getBean("serverEndpointBean");
    private WebClientUtil webClientBean = (WebClientUtil) SpringUtil.getBean("webClientBean");

    private List<NetLabelVo> netLabelVos;
    private List<UrlMonitorEntity> urlMonitorFullEntities;

    private DefaultListModel jListModel;
    private DefaultListModel jListModelUrl;

    private Integer lastListIndex = 0;

    private NetMonitorClinet netMonitorClinet;

    public NetLabelManager() {
        initComponents();
    }

    public void showFrame() {
        NetLabelMng.setVisible(true);
    }

    public void closeFrame(){
        NetLabelMng.dispose();
    }

    public NetLabelManager(List<NetLabelVo> netLabelVos,
                           List<UrlMonitorEntity> urlMonitorFullEntities,
                           NetMonitorClinet netMonitorClinet) {
        initComponents();
        this.netLabelVos = netLabelVos;
        this.urlMonitorFullEntities = urlMonitorFullEntities;
        this.netMonitorClinet = netMonitorClinet;
        initCustomComp();
    }

    private void initCustomComp() {
        NetLabelMng.setDefaultCloseOperation(2);
        btn_labelSave.setEnabled(false);
        if (JList_label.isSelectionEmpty()) {
            btn_labelUpd.setEnabled(false);
        }
        jListModel = new DefaultListModel();
        jListModelUrl = new DefaultListModel();
        JList_label_url.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JList_label_url.setCellRenderer(new SelectedList());
        JList_label_url.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (super.isSelectedIndex(index0))
                    super.removeSelectionInterval(index0, index1);
                else
                    super.addSelectionInterval(index0, index1);
            }
        });
        JList_label_url.setEnabled(false);

        JList_label.setModel(jListModel);
        JList_label_url.setModel(jListModelUrl);
        jPopupMenu = new JPopupMenu();
        //jMenu = new JMenu("标签");
        jMenuItemRemove = new JMenuItem("删除");
        jMenuItemAdd = new JMenuItem("添加");
        jPopupMenu.add(jMenuItemAdd);
        jPopupMenu.add(jMenuItemRemove);
        jPopupMenu.addSeparator();

        JList_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && JList_label.getSelectedIndex() != -1) {
                    //获取选择项的值
                    //Object selected = JList_label.getModel().getElementAt(JList_label.getSelectedIndex());
                    //System.out.println(selected);
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        updateListLabel();
        listenEventRegister();
    }

    public void updateListLabel() {
        jListModel.clear();
        jListModelUrl.clear();
        netLabelVos.forEach(t -> jListModel.addElement(t));
        urlMonitorFullEntities.forEach(t -> jListModelUrl.addElement(t));
    }

    public void updateListLabel(List<NetLabelVo> netLabelVos){
        this.netLabelVos = netLabelVos;
        updateListLabel();
    }

    private void updateListUrlSelection(String urlsStr) {
        JList_label_url.clearSelection();
        if (StringUtils.isBlank(urlsStr)){
            return;
        }
        List<String> urlIdList = Arrays.asList(urlsStr.split(","));
        for (int i = 0; i < urlMonitorFullEntities.size(); i++) {
            UrlMonitorEntity value = (UrlMonitorEntity) JList_label_url.getModel().getElementAt(i);
            if (urlIdList.contains(value.getUrlId())) {
                JList_label_url.setSelectedIndex(i);
            }
        }
        JList_label_url.setEnabled(false);
    }

    private void listenEventRegister() {

        /**
         * 修改按钮
         */
        btn_labelUpd.addActionListener(e -> {
            btn_labelSave.setEnabled(true);
            JList_label_url.setEnabled(true);
        });

        btn_labelSave.addActionListener(e -> {
            btn_labelUpd.setEnabled(false);
            NetLabelVo netLabelVo = (NetLabelVo) JList_label.getSelectedValue();
            String newLabelNetList = "";
            List<UrlMonitorEntity> entities = JList_label_url.getSelectedValuesList();
            if (entities.size() != 0){
                newLabelNetList = entities.stream().map(UrlMonitorEntity::getUrlId).map(String::valueOf).collect(Collectors.joining(","));
            }
            netLabelVo.setNetList(newLabelNetList);

            JsonResult jsonResult = webClientBean.webClientPostMethodAsync(serverEndpointBean.getNetLabelAddOrUpdEndpoint(), JsonResult.class, netLabelVo);
            if (jsonResult.getReturnCode().equals(JsonResult.SUCC)){
                JFrameUtil.messageFrame(NetLabelMng, MessageBoxType.INFO, "标签：" + netLabelVo.getLabelName() + "修改成功");
                netMonitorClinet.refreshLabelCombox();
            }

            JList_label_url.setEnabled(false);
            btn_labelSave.setEnabled(false);
        });
        /**
         * 标签选中事件
         */
        JList_label.addListSelectionListener(e -> {
            Integer idx = JList_label.getSelectedIndex();
            //每次变化后在刷新右边列表
            if (lastListIndex != idx){
                NetLabelVo netLabelVo = (NetLabelVo) JList_label.getSelectedValue();
                String netList = "";
                if (netLabelVo != null){
                    netList = netLabelVo.getNetList();
                }
                updateListUrlSelection(netList);
                lastListIndex = idx;
            }
            btn_labelSave.setEnabled(false);
            if (JList_label.isSelectionEmpty()) {
                btn_labelUpd.setEnabled(false);
            } else {
                btn_labelUpd.setEnabled(true);
            }
        });
        /**
         * 右击弹出菜单监听事件
         */
        jMenuItemAdd.addActionListener(e -> {
            //webClientBean
            String newLabelName = JOptionPane.showInputDialog(NetLabelMng, "new label name:\n", "new",
                    JOptionPane.PLAIN_MESSAGE);
            if (StringUtils.isBlank(newLabelName)){
                return;
            }
            NetLabelVo newLabelVo = new NetLabelVo();
            newLabelVo.setCreateTime(DateUtil.DateToString(new Date()));
            newLabelVo.setLabelName(newLabelName);
            JsonResult jsonResult = webClientBean.webClientPostMethodAsync(serverEndpointBean.getNetLabelAddOrUpdEndpoint(), JsonResult.class, newLabelVo);
            if (jsonResult.getReturnCode().equals(JsonResult.SUCC)){
                JFrameUtil.messageFrame(NetLabelMng, MessageBoxType.INFO, "标签：" + newLabelName + "新增成功");
                netMonitorClinet.refreshLabelCombox();
            }
        });

        jMenuItemRemove.addActionListener(e -> {
            NetLabelVo selected = (NetLabelVo) JList_label.getModel().getElementAt(JList_label.getSelectedIndex());
            if (selected == null) {
                return;
            }
            int result = JOptionPane.showConfirmDialog(NetLabelMng, "确认删除标签 "+ selected.getLabelName()+"?",
                    "提示",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION){
                JsonResult jsonResult = webClientBean.webClientPostMethodAsync(serverEndpointBean.getNetLabelDelEndpoint(), JsonResult.class, selected.getLabelId());
                if (jsonResult.getReturnCode().equals(JsonResult.SUCC)) {
                    JFrameUtil.messageFrame(NetLabelMng, MessageBoxType.INFO, "标签：" + selected.getLabelName() + "删除成功");
                    netMonitorClinet.refreshLabelCombox();
                }else {
                    JFrameUtil.messageFrame(NetLabelMng, MessageBoxType.INFO, "标签：" + selected.getLabelName() + "删除失败");
                }
            }

        });


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        NetLabelMng = new JFrame();
        panel2 = new JPanel();
        label1 = new JLabel();
        panel5 = new JPanel();
        splitPane1 = new JSplitPane();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        JList_label = new JList();
        scrollPane2 = new JScrollPane();
        JList_label_url = new JList();
        panel3 = new JPanel();
        btn_labelUpd = new JButton();
        btn_labelSave = new JButton();

        //======== NetLabelMng ========
        {
            NetLabelMng.setTitle("labelManager");
            NetLabelMng.setIconImage(((ImageIcon)UIManager.getIcon("FileView.hardDriveIcon")).getImage());
            NetLabelMng.setMinimumSize(new Dimension(400, 400));
            Container NetLabelMngContentPane = NetLabelMng.getContentPane();
            NetLabelMngContentPane.setLayout(new FormLayout(
                "95dlu:grow",
                "fill:default, $lgap, default, $lgap, 26dlu"));

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("\u6807\u7b7e\u7ba1\u7406");
                panel2.add(label1);
            }
            NetLabelMngContentPane.add(panel2, CC.xy(1, 1));

            //======== panel5 ========
            {
                panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

                //======== splitPane1 ========
                {
                    splitPane1.setDividerLocation(150);
                    splitPane1.setMinimumSize(new Dimension(157, 144));

                    //======== panel1 ========
                    {
                        panel1.setLayout(new FormLayout(
                            "16dlu:grow",
                            "64dlu:grow"));

                        //======== scrollPane1 ========
                        {
                            scrollPane1.setViewportView(JList_label);
                        }
                        panel1.add(scrollPane1, CC.xy(1, 1));
                    }
                    splitPane1.setLeftComponent(panel1);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(JList_label_url);
                    }
                    splitPane1.setRightComponent(scrollPane2);
                }
                panel5.add(splitPane1);
            }
            NetLabelMngContentPane.add(panel5, CC.xy(1, 3));

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout());

                //---- btn_labelUpd ----
                btn_labelUpd.setText("\u4fee\u6539");
                panel3.add(btn_labelUpd);

                //---- btn_labelSave ----
                btn_labelSave.setText("\u4fdd\u5b58");
                panel3.add(btn_labelSave);
            }
            NetLabelMngContentPane.add(panel3, CC.xy(1, 5));
            NetLabelMng.pack();
            NetLabelMng.setLocationRelativeTo(NetLabelMng.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame NetLabelMng;
    private JPanel panel2;
    private JLabel label1;
    private JPanel panel5;
    private JSplitPane splitPane1;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JList JList_label;
    private JScrollPane scrollPane2;
    private JList JList_label_url;
    private JPanel panel3;
    private JButton btn_labelUpd;
    private JButton btn_labelSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private JPopupMenu jPopupMenu;
    private JMenuItem jMenuItemAdd, jMenuItemRemove;
}
