package com.example.view.flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightRegistrationForm extends JFrame {
    private JLabel flightName;
    private JTextField flightNameField;
    private JLabel flightNumber;
    private JTextField flightNumberField;
    private JButton flightRegisterButton;

    public FlightRegistrationForm(){
        super("Flight Reistration Form");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        flightName=new JLabel("Enter flight name");
        flightNameField=new JTextField(20);
        flightNumber=new JLabel("Enter flight number");
        flightNumberField=new JTextField(20);
        flightRegisterButton=new JButton("Register Flight");

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.WEST;
        add(flightName,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.WEST;
        add(flightNameField,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.WEST;
        add(flightNumber,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.WEST;
        add(flightNumberField,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.anchor=GridBagConstraints.CENTER;
        add(flightRegisterButton,gbc);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightName = flightNameField.getText();
                String flightNumber = flightNumberField.getText();

                System.out.println("\nFlight name : "+flightName);
                System.out.println("Flight number : "+flightNumber);
            }
        };

        flightRegisterButton.addActionListener(actionListener);
    }
}
