package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginPanel(AppFrame appFrame) {
        setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2));
        JPanel buttonPanel = new JPanel();

        fieldsPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        fieldsPanel.add(usernameField);

        fieldsPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        fieldsPanel.add(passwordField);

        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        JButton loginButton = new JButton("Login");
        JButton switchToCreateProfileButton = new JButton("Create Profile");

        buttonPanel.add(loginButton);
        buttonPanel.add(switchToCreateProfileButton);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        switchToCreateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appFrame.switchTo("CreateProfilePanel");
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (true) {
            statusLabel.setText("Login successful!");
            statusLabel.setForeground(Color.GREEN);
        } else {
            statusLabel.setText("Invalid username or password.");
            statusLabel.setForeground(Color.RED);
        }
    }
}