package views;

import models.User;

import javax.swing.*;
import java.awt.*;

public class UserProfilePanel extends JPanel {
    private AppFrame appFrame;

    public UserProfilePanel(AppFrame appFrame, int userID) {
        this.appFrame = appFrame;

        setLayout(new BorderLayout());

        // Create the app bar with "Back to Shop" and "Logout" buttons
        JPanel appBar = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backToShopButton = new JButton("Back to Shop");
        JButton logoutButton = new JButton("Logout");

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

        // User info on the right
        infoPanel.add(new JLabel("Username: " + "Ruganje"));
        infoPanel.add(new JLabel("Email: " + "Smijanje"));
        infoPanel.add(new JLabel("Address: " + "Plakanje"));

        profileContent.add(infoPanel, BorderLayout.CENTER);

        add(appBar, BorderLayout.NORTH);
        add(profileContent, BorderLayout.CENTER);

        // Button actions
        backToShopButton.addActionListener(e -> {
            appFrame.showShop();
        });
        logoutButton.addActionListener(e -> {
            appFrame.logoutUser();
            appFrame.switchTo("LoginPanel");
        });
    }
}
