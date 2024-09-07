package views;

import controllers.UserProfileController;
import models.User;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class UserProfilePanel extends JPanel {
    private AppFrame appFrame;
    private UserProfileController upc = new UserProfileController();

    // Labels for display mode
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel addressLabel;

    // Text fields for editing mode
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField addressField;

    // Buttons
    private JButton editButton;
    private JButton saveButton;

    public UserProfilePanel(AppFrame appFrame, int userID) {
        this.appFrame = appFrame;

        setLayout(new BorderLayout());

        // Create the app bar with "Back to Shop" and "Logout" buttons
        JPanel appBar = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton adminButton = new JButton("All users");
        JButton backToShopButton = new JButton("Back to Shop");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(adminButton);
        buttonPanel.add(backToShopButton);
        buttonPanel.add(logoutButton);
        appBar.add(buttonPanel, BorderLayout.NORTH);

        // Profile content
        JPanel profileContent = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Profile picture on the left
        JLabel profilePicture = new JLabel(new ImageIcon("src/main/resources/profile_pic.png"));
        profileContent.add(profilePicture, BorderLayout.WEST);

        // User info on the right (default in labels)
        User user = upc.getUser(userID);
        adminButton.setVisible(user.getAdminStatus());
        usernameLabel = new JLabel("Username: " + user.getUsername());
        emailLabel = new JLabel("Email: " + user.getEmail());
        addressLabel = new JLabel("Address: " + user.getAddress());

        // Editable text fields
        usernameField = new JTextField(user.getUsername());
        emailField = new JTextField(user.getEmail());
        addressField = new JTextField(user.getAddress());

        infoPanel.add(usernameLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(addressLabel);

        profileContent.add(infoPanel, BorderLayout.CENTER);

        // Edit and Save buttons
        JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        editButton = new JButton("Edit");
        saveButton = new JButton("Save");
        saveButton.setVisible(false); // Save button is hidden by default

        editPanel.add(editButton);
        editPanel.add(saveButton);
        profileContent.add(editPanel, BorderLayout.SOUTH);

        add(appBar, BorderLayout.NORTH);
        add(profileContent, BorderLayout.CENTER);

        // Button actions
        adminButton.addActionListener(e -> {
            appFrame.switchTo("AdminPanel");
        });
        backToShopButton.addActionListener(e -> {
            appFrame.showShop();
        });
        logoutButton.addActionListener(e -> {
            appFrame.logoutUser();
            appFrame.switchTo("LoginPanel");
        });
        editButton.addActionListener(e -> switchToEditMode());
        saveButton.addActionListener(e -> saveUserData(userID));
    }

    // Switch to edit mode
    private void switchToEditMode() {
        // Replace labels with text fields
        usernameLabel.setVisible(false);
        emailLabel.setVisible(false);
        addressLabel.setVisible(false);

        JPanel infoPanel = (JPanel) usernameLabel.getParent();
        infoPanel.removeAll();

        infoPanel.add(new JLabel("Username"));
        infoPanel.add(usernameField);
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(emailField);
        infoPanel.add(new JLabel("Address"));
        infoPanel.add(addressField);

        // Toggle buttons
        editButton.setVisible(false);
        saveButton.setVisible(true);

        revalidate();
        repaint();
    }

    // Save user data and switch back to view mode
    private void saveUserData(int userID) {
        // Get the updated data from the text fields
        String updatedUsername = usernameField.getText();
        String updatedEmail = emailField.getText();
        String updatedAddress = addressField.getText();

        // Update the user object
        boolean changed = upc.changeUserInfo(userID, updatedUsername, updatedEmail, updatedAddress);

        if(changed) {
            // Replace text fields with labels
            usernameLabel.setText("Username: " + updatedUsername);
            emailLabel.setText("Email: " + updatedEmail);
            addressLabel.setText("Address: " + updatedAddress);

            JPanel infoPanel = (JPanel) usernameField.getParent();
            infoPanel.removeAll();

            infoPanel.add(usernameLabel);
            infoPanel.add(emailLabel);
            infoPanel.add(addressLabel);

            // Toggle buttons
            editButton.setVisible(true);
            saveButton.setVisible(false);

            usernameLabel.setVisible(true);
            emailLabel.setVisible(true);
            addressLabel.setVisible(true);

            revalidate();
            repaint();
        }
    }
}


