package com.tlglearning.gui.panelinterface;

import com.tlglearning.gui.ColorPane;
import com.tlglearning.gui.MainWindow;
import com.tlglearning.gui.button.CommandButton;

import javax.swing.*;
import java.awt.*;

public class PromptPanel extends PanelAbstractMethods implements PanelImageInterface{

    private static  JPanel PROMPT_CONTAINER;

    private static  JTextField commandTextField;
    private static  ColorPane prompt_SubPanelAscii;
    private static  JPanel BUTTON_ACTION_CONTAINER;
    private static  JPanel BUTTON_GO_CONTAINER;

    private static CommandButton BUTTON_GO_NORTH;
    private static  CommandButton BUTTON_GO_SOUTH;
    private static  CommandButton BUTTON_GO_EAST;
    private static  CommandButton BUTTON_GO_WEST;
    private static  CommandButton BUTTON_EXPLORE;
    private static  CommandButton BUTTON_GET;



    static{

        MainWindow.setPROMPT_CONTAINER(PROMPT_CONTAINER);
        MainWindow.setcommandTextField(commandTextField);
        MainWindow.set_prompt_SubPanelAscii(prompt_SubPanelAscii);


        MainWindow.set_BUTTON_ACTION_CONTAINER(BUTTON_ACTION_CONTAINER);
        MainWindow.set_BUTTON_GO_CONTAINER(BUTTON_GO_CONTAINER);
        MainWindow.set_BUTTON_GO_NORTH(BUTTON_GO_NORTH);
        MainWindow.set_BUTTON_GO_EAST(BUTTON_GO_EAST);
        MainWindow.set_BUTTON_GO_SOUTH(BUTTON_GO_SOUTH);
        MainWindow.set_BUTTON_GO_WEST(BUTTON_GO_WEST);
        MainWindow.set_BUTTON_EXPLORE(BUTTON_EXPLORE);
        MainWindow.set_BUTTON_GET(BUTTON_GET);
    }
    public void setPrompt(String str) {
//        prompt_SubPanelAscii.setEditable(true);
//        MainWindow.sleep();
//        if (!MainWindow.gameStarted) {
//            MainWindow.setPromptText(str);
//            prompt_SubPanelAscii.appendANSI("\n" + text);
//        }
//        if (str.contains("New game started.")) {
//
//            prompt_SubPanelAscii.setPreferredSize(new Dimension(600, 150));
//            setGameStarted();
//            setPromptText(str);
//            prompt_SubPanelAscii.setText(text);
//        } else if (prompt_SubPanelAscii.getText().charAt(prompt_SubPanelAscii.getText().length() - 3) == '>') {
//            setPromptText(str);
//            prompt_SubPanelAscii.setText("\n" + text);
//        } else if (gameStarted) {
//            setPromptText(str);
//            prompt_SubPanelAscii.appendANSI("\n" + text);
//        }
//        PROMPT_CONTAINER.revalidate();
//        PROMPT_CONTAINER.repaint();
//        sleep();
//        prompt_SubPanelAscii.setEditable(false);
    }









//          BUTTON_GO_CONTAINER.add((new CommandButton(this, "N","Go North")).getButton(),BorderLayout.NORTH);
//        BUTTON_GO_CONTAINER.add((new CommandButton(this, "S","Go South")).getButton(),BorderLayout.SOUTH);
//        BUTTON_GO_CONTAINER.add(commandTextField, BorderLayout.CENTER);
//        BUTTON_GO_CONTAINER.add((new CommandButton(this, "E","Go East")).getButton(),BorderLayout.EAST);
//        BUTTON_GO_CONTAINER.add((new CommandButton(this, "W","Go West")).getButton(),BorderLayout.WEST);
//
//                PROMPT_CONTAINER.add(BUTTON_GO_CONTAINER,BorderLayout.NORTH);
//        PROMPT_CONTAINER.add(BUTTON_ACTION_CONTAINER,BorderLayout.SOUTH);
//
//                BUTTON_GO_CONTAINER.add((new CommandButton(this, "N","Go North")).getButton(),BorderLayout.NORTH);
//        BUTTON_GO_CONTAINER.add((new CommandButton(this, "S","Go South")).getButton(),BorderLayout.SOUTH);
//        BUTTON_GO_CONTAINER.add(commandTextField, BorderLayout.CENTER);
//        BUTTON_GO_CONTAINER.add((new CommandButton(this, "E","Go East")).getButton(),BorderLayout.EAST);
//        BUTTON_GO_CONTAINER.add((new CommandButton(this, "W","Go West")).getButton(),BorderLayout.WEST);
//
//
//    public void setPrompt(String str) {
//        prompt_SubPanelAscii.setEditable(true);
//        sleep();
//        if (!gameStarted) {
//            setPromptText(str);
//            prompt_SubPanelAscii.appendANSI("\n" + text);
//        }
//        if (str.contains("New game started.")) {
//
//            prompt_SubPanelAscii.setPreferredSize(new Dimension(600, 150));
//            setGameStarted();
//            setPromptText(str);
//            prompt_SubPanelAscii.setText(text);
//        } else if (prompt_SubPanelAscii.getText().charAt(prompt_SubPanelAscii.getText().length() - 3) == '>') {
//            setPromptText(str);
//            prompt_SubPanelAscii.setText("\n" + text);
//        } else if (gameStarted) {
//            setPromptText(str);
//            prompt_SubPanelAscii.appendANSI("\n" + text);
//        }
//        PROMPT_CONTAINER.revalidate();
//        PROMPT_CONTAINER.repaint();
//        sleep();
//        prompt_SubPanelAscii.setEditable(false);
//    }
}
