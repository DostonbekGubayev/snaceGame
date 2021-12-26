package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameFreme extends JFrame {
    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
    }
    private JButton tugma;


    gameFreme(){
        gamePanel panel=new gamePanel();
        this.add(new gamePanel());
        this.setTitle("Ilon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
