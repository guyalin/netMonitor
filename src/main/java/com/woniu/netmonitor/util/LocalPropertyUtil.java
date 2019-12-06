package com.woniu.netmonitor.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.system.ApplicationHome;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LocalPropertyUtil {

    private String basePath = new ApplicationHome(this.getClass()).getSource().getParentFile().getPath()+ "/files/";

    private Properties prop;
    private String fileName;

    public LocalPropertyUtil(Properties prop, String fileName){
        this.prop = prop;
        this.fileName = fileName;
    }

    public String getProperty(String propertyName) {
        InputStream in = null;
        String filePath = basePath + fileName;
        String value = "";
        try {
            CreateFileIfNecessary(filePath);
            in = new BufferedInputStream(new FileInputStream(filePath));
            prop.load(in);
            value = prop.getProperty(propertyName);
            in.close();
            return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void saveProperty(Map<String, String> propertyMap) {
        FileOutputStream fos = null;
        String filePath = basePath + fileName;
        try {
            CreateFileIfNecessary(filePath);
            fos = new FileOutputStream(filePath);
            for (String key : propertyMap.keySet()) {
                prop.setProperty(key,propertyMap.get(key));
            }
            prop.store(fos, "localProperties");
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 组件值注册保存最近一次修改值。用于下次登录加载
     */
    public void saveProperties(JFrame parent, List<JComponent> jComponents){
        Map<String, String> stringMap = new HashMap<>();
        for (JComponent component : jComponents) {
            String key = parent.getName().concat("-").concat(component.getName());
            if (component instanceof JTextField){
                stringMap.put(key, ((JTextField) component).getText());
            }
            if (component instanceof JCheckBox){
                stringMap.put(key, Boolean.toString(((JCheckBox) component).isSelected()));
            }
            if (component instanceof JComboBox){
                stringMap.put(key, String.valueOf(((JComboBox) component).getSelectedIndex()));
            }
        }
        saveProperty(stringMap);
    }

    /**
     * 从配置文件加载值
     * @param parent
     * @param jComponents
     */
    public void loadProperties(JFrame parent, List<JComponent> jComponents){

        for (JComponent component : jComponents) {
            String key = parent.getName().concat("-").concat(component.getName());
            if (component instanceof JTextField){
                ((JTextField) component).setText(getProperty(key));
            }
            if (component instanceof JCheckBox){
                Boolean ifChecked = (new Boolean(getProperty(key))).booleanValue();
                ((JCheckBox) component).setSelected(ifChecked);
            }
            if (component instanceof JComboBox){
                Integer idx = ((StringUtils.isEmpty(getProperty(key)) ||
                        !getProperty(key).matches("[0-9]")) ?
                        0 : Integer.parseInt(getProperty(key)));
                ((JComboBox) component).setSelectedIndex(idx);
            }

        }

    }


    private void CreateFileIfNecessary(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

}
