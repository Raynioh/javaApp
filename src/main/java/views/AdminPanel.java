package views;

import controllers.AdminController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPanel extends JPanel {

    private AppFrame appFrame;
    private JTextArea adminTextArea;
    private AdminController ac = new AdminController();

    public AdminPanel(AppFrame appFrame) {
        this.appFrame = appFrame;

        setLayout(new BorderLayout());

        // Create the app bar with "Back to User Profile" and "Logout" buttons
        JPanel appBar = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backToProfileButton = new JButton("Back to Profile");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(backToProfileButton);
        buttonPanel.add(logoutButton);
        appBar.add(buttonPanel, BorderLayout.NORTH);

        // Text panel (scrollable)
        adminTextArea = new JTextArea();
        adminTextArea.setEditable(false); // Set to read-only by default
        this.appendText(ac.getUsers());

        // ScrollPane for the text area
        JScrollPane scrollPane = new JScrollPane(adminTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add app bar and scrollable text panel to the layout
        add(appBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button actions
        backToProfileButton.addActionListener(e -> {
            appFrame.showUserProfile();
        });
        logoutButton.addActionListener(e -> {
            appFrame.logoutUser();
            appFrame.switchTo("LoginPanel");
        });    }

    // Method to append information to the admin text panel
    public void appendText(List<User> users) {
        for(User u: users) {
            adminTextArea.append(u.toString() + "\n");
        }
    }
}
