package com.woniu.netmonitor.util;

import com.woniu.netmonitor.dictionary.MessageBoxType;

import javax.swing.*;

public class JFrameUtil {

    public static int messageFrame(JFrame frame, MessageBoxType boxType, String message) {
        switch (boxType) {
            case ALERT:
                JOptionPane.showMessageDialog(frame, message, "警告", JOptionPane.WARNING_MESSAGE);
                break;
            case INFO:
                JOptionPane.showMessageDialog(frame, message, "提示", JOptionPane.INFORMATION_MESSAGE);
                break;
            case ERROR:
                JOptionPane.showMessageDialog(frame, message, "错误", JOptionPane.ERROR_MESSAGE);
                break;
            case CONFIRM:
                return JOptionPane.showConfirmDialog(frame, message, "请确认", JOptionPane.YES_NO_OPTION);
            default:
                JOptionPane.showMessageDialog(frame, message, "提示", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        return 0;
    }


    public static void LocalePropertyRegister(JComponent jComponent){

    }

}
