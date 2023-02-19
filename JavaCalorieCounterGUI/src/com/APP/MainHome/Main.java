package com.APP.MainHome;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame { //implements 글로벌컨스턴스
    public Main() throws SQLException, IOException {
        initialize();
    }

    private void initialize() throws SQLException, IOException {
        Container container = getContentPane();
        setVisible(true);
        container.setLayout(new BorderLayout());
        // setResizable(false);
        setSize(560, 840);
        setTitle("HOME");

        JPanel mainUserSelectPane = new mainUserSelectPane();
        container.add(mainUserSelectPane, BorderLayout.CENTER);
    }
    public static void main(String[] args) throws SQLException, IOException {
        new Main();
    }
}
class mainUserSelectPane extends JPanel {
    JComboBox<String> UserSex;
    JPanel[] TitleBorder;
    JTextField[] UserInputField;
    JButton btnRecord, btnSubmit;   //Calculated Calories, submit

    String[] Sex = {"Male", "Female", "None"};
    String[] UserTitle = {"Gender", "Age", "Height", "Weight", "another"};

    String BtnStr = "<html><u>Calculated Calories >></u></html>";
    JFrame jFrame;
    public mainUserSelectPane( ) throws SQLException, IOException {
        setPreferredSize(new Dimension(500,200));
//        setBackground(PRIMARY_COLOR);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        TitleBorder = new JPanel[UserTitle.length];
        UserInputField = new JTextField[UserTitle.length];
        for (int i=0; i<TitleBorder.length; i++) {

            TitleBorder[i] = new JPanel(new GridBagLayout());
            TitleBorder[i].setOpaque(false); // transparent setting for background
            TitleBorder[i].setBorder(BorderFactory.createTitledBorder(UserTitle[i]));
            gridBagConstraints.ipadx = 400; // Size setting of JTextField using x-width
            gridBagConstraints.ipady = 20;
            gridBagConstraints.insets = new Insets(10,0,10,0);
            gridBagConstraints.gridx = 0; // Grid x position
            gridBagConstraints.gridy = i; // Grid y position
            if (i == 0) {
                UserSex = new JComboBox<String>(Sex);
                UserSex.setPreferredSize(new Dimension(300, 20));
                gridBagConstraints.ipadx = 310; // Size setting of JTextField using x-width
                TitleBorder[i].add(UserSex);
            } else {
                UserInputField[i] = new JTextField();
//                UserInputField[i].setText(UserInputTxt.getUserInputTxt().get(i));

                UserInputField[i].setColumns(35);
                TitleBorder[i].add(UserInputField[i]);
            }
            add(TitleBorder[i], gridBagConstraints);
        }


        btnSubmit = new JButton("Submit");
        btnSubmit.setBorderPainted(false); // remove border
        btnSubmit.setContentAreaFilled(false); // remove fill
        btnSubmit.setFocusPainted(true); // remove mouse focus
        btnSubmit.setOpaque(false); // transparent
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 0; // Grid x position
        gridBagConstraints.gridy = 7; // Grid y position
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(btnSubmit, gridBagConstraints);
    }
}
