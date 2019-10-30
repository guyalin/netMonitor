package com.woniu.netmonitor.util;

import com.woniu.netmonitor.entity.ArticleRecord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@Data
public class LocalFileWriterBean {
    private String localFilePath;
    private String fileName;
    private PrintWriter pw;

    public LocalFileWriterBean(String localFilePath, String fileName) {
        this.localFilePath = localFilePath;
        this.fileName = fileName;
    }

    private void init() throws FileNotFoundException {
        if (pw == null) {
            log.info("LocalFileWriterBean: printWriter init!");
            pw = new PrintWriter(new File(localFilePath + "\\" + fileName));
        }
    }

    public void saveToFile(List<ArticleRecord> records) throws FileNotFoundException {
        init();
        for (ArticleRecord record : records) {
            pw.println("department: " + record.getArticleName());
            pw.println("link : " + record.getTargetUrl());
            pw.println("text : " + record.getArticleTitle());
            pw.println("date : " + record.getDateTime());
            pw.println("\n");
        }
        pw.flush();
        destroy();
    }

    public void destroy() {
        if (pw != null) {
            log.info("LocalFileWriterBean: printWriter destroyed!");
            pw.close();
            pw = null;
        }
    }
}
