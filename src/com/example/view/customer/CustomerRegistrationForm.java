package com.example.view.customer;

import com.example.service.CustomerService;
import com.example.model.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRegistrationForm extends JFrame {
    private static CustomerService customerService = new CustomerService();
    // Declare Swing components
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JButton registerButton;

    public CustomerRegistrationForm() {
        // Set the title of the JFrame
        super("Customer Registration Form");

        // Set the default close operation (exit the application when closed)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(400, 350);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Use GridBagLayout for better control over component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill their display area

        // Initialize components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20); // 20 columns wide
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(20);
        registerButton = new JButton("Register");

        // Add components to the frame using GridBagConstraints

        // Name Label
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        add(nameLabel, gbc);

        // Name Field
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);

        // Email Label
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        gbc.anchor = GridBagConstraints.WEST;
        add(emailLabel, gbc);

        // Email Field
        gbc.gridx = 1; // Column 1
        gbc.gridy = 1; // Row 1
        gbc.anchor = GridBagConstraints.WEST;
        add(emailField, gbc);

        // Phone Label
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.WEST;
        add(phoneLabel, gbc);

        // Phone Field
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        gbc.anchor = GridBagConstraints.WEST;
        add(phoneField, gbc);

        // Register Button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 3; // Row 3
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        add(registerButton, gbc);

         ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the input fields
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                Customer newCustomer = new Customer(name, phone, email);
		        customerService.create(newCustomer);
		        System.out.println("New Customer Created\n");
		        System.out.println(newCustomer);
            }
        };

        // Add ActionListener to the register button
        registerButton.addActionListener(actionListener);
    }
}
