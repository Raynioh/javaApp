package views;

import controllers.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProfilePanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField addressField;
    private JLabel statusLabel;
    private JPanel radioPanel;
    private ButtonGroup radioButtons;
    private JRadioButton adminButton;
    private JRadioButton userButton;
    private LoginController lc = new LoginController();


    public CreateProfilePanel(AppFrame appFrame) {
        setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel(new GridLayout(6, 2));
        JPanel buttonPanel = new JPanel();

        fieldsPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        fieldsPanel.add(usernameField);

        fieldsPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        fieldsPanel.add(passwordField);

        fieldsPanel.add(new JLabel("Email:"));
        emailField = new JTextField(15);
        fieldsPanel.add(emailField);

        fieldsPanel.add(new JLabel("Address:"));
        addressField = new JTextField(15);
        fieldsPanel.add(addressField);

        fieldsPanel.add(new JLabel("Admin:"));
        radioButtons = new ButtonGroup();
        userButton = new JRadioButton("No");
        adminButton = new JRadioButton("Yes");
        radioPanel = new JPanel();

        userButton.setSelected(true);
        userButton.setActionCommand("user");
        adminButton.setActionCommand("admin");
        radioButtons.add(userButton);
        radioButtons.add(adminButton);
        radioPanel.add(userButton);
        radioPanel.add(adminButton);
        fieldsPanel.add(radioPanel);

        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        JButton createButton = new JButton("Create Profile");
        JButton backToLoginButton = new JButton("Back to Login");

        buttonPanel.add(createButton);
        buttonPanel.add(backToLoginButton);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProfileCreation();
            }
        });

        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appFrame.switchTo("LoginPanel");
            }
        });
    }

    private void handleProfileCreation() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        String address = addressField.getText();
        boolean admin = radioButtons.getSelection().getActionCommand().equals("admin");

        if (lc.createProfile(username, password, email, address, admin)) {
            statusLabel.setText("Profile created successfully!");
            statusLabel.setForeground(Color.GREEN);
        } else {
            statusLabel.setText("Username already exists.");
            statusLabel.setForeground(Color.RED);
        }
    }
}