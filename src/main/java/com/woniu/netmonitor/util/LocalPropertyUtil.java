package com.woniu.netmonitor.util;

import org.springframework.boot.system.ApplicationHome;

import java.io.*;
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
