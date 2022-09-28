package com.tlglearning.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Timer;

public class Countdown {
    private static Timer timer;
    private static JPanel panel;
    private static Font font1 = new Font("Arial", Font.PLAIN, 70);
    private static int second;
    private static int minute;
    private static DecimalFormat dFormat = new DecimalFormat();
    private static String ddSecond;
    private static String ddMinute;
    private static JLabel counterLabel;

    public Countdown() {
        counterLabel = new JLabel("");
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(font1);
        counterLabel.setForeground(new Color(100,100,100,90));
        panel = new JPanel();
        panel.setBounds(1020, 25, 180, 80);
        panel.setOpaque(false);
        panel.add(counterLabel);
        counterLabel.setText("15:00");
        second = 0;
        minute = 15;
        startCountDown();
        timer.start();
    }

    private void startCountDown() {
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                counterLabel.setText(ddMinute + ":" + ddSecond);

                if (second == -1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    counterLabel.setText(ddMinute + ":" + ddSecond);
                }

                if (second <= 9 && second >= 0){
                    counterLabel.setText(ddMinute + ":0" + ddSecond);
                }

                if (minute == 0 && second == 0) {
                    timer.stop();
                    //TODO: YOU LOSE QUIT THE GAME
                }
            }
        });
    }

    public static JPanel getPanel() {
        return panel;
    }

}
