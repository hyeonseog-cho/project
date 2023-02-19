package com.APP.Exercise;

import com.APP.NavBar.bottomMenuPane;

import javax.swing.*;
import java.awt.*;

public class AppExercise extends JFrame{
    public AppExercise() {
        initialize();
    }
    private void initialize() {
        setTitle("Exercise page"); // 메인페이지
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // container set
        Container container = getContentPane();

        // Size and layout

        setSize(560,840);
        setLayout(new BorderLayout());

        JPanel TopSearchingPane = new JPanel();
        JPanel MainInfoPane = new JPanel();
        JPanel MainBtnPane = new JPanel();
        JPanel bottomMenuPane = new bottomMenuPane(560, 105, this);

        JPanel MainjPanel = new JPanel();

        container.add(TopSearchingPane, BorderLayout.NORTH);
        container.add(MainjPanel, BorderLayout.CENTER);
        container.add(bottomMenuPane, BorderLayout.SOUTH);

        MainjPanel.add(MainInfoPane, BorderLayout.NORTH);
        MainjPanel.add(MainBtnPane, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppExercise appExercise = new AppExercise();
                    appExercise.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
