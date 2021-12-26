package com.company;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class menuClass extends JList implements ItemListener {
    private static String[] menu={"Start","Exit"};
    private JComboBox comboBox;
    private JList royxat;
    gamePanel panel;
    gameFreme freme;
    public menuClass(){
        royxat=new JList<>(menu);
        royxat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel=new gamePanel();
        freme=new gameFreme();
    }
    public void itemStateChanged(ItemEvent hodisa) {
    if(hodisa.getSource().equals(menu[0])){
        panel.running=true;
        panel.timer.start();
        }else
            panel.running=false;
    panel.timer.stop();
    freme.getDefaultCloseOperation();
    }
}
