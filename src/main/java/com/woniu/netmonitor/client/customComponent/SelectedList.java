package com.woniu.netmonitor.client.customComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author: guyalin
 * @date: 2019/12/16
 */
public class SelectedList extends JCheckBox implements ListCellRenderer {

    public SelectedList(){
        super();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setText(value.toString());
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        this.setSelected(isSelected);
        return this;
    }
}
