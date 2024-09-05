package views;

import models.User;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int currentUserID;

    public AppFrame() {
        setTitle("User Authentication App");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize and add different panels to the main panel
        LoginPanel loginPanel = new LoginPanel(this);
        CreateProfilePanel createProfilePanel = new CreateProfilePanel(this);
//        ShopPanel shopPanel = new ShopPanel(this);
//        UserProfilePanel userProfilePanel = new UserProfilePanel(this);
//        CartPanel cartPanel = new CartPanel(this);

        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(createProfilePanel, "CreateProfilePanel");
//        mainPanel.add(shopPanel, "ShopPanel");
//        mainPanel.add(userProfilePanel, "UserProfilePanel");
//        mainPanel.add(cartPanel, "CartPanel");

        add(mainPanel);
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
        // Adjust frame size if needed when switching panels
        if (panelName.equals("ShopPanel")) {
            setSize(400, 500);
        } else if (panelName.equals("UserProfilePanel")) {
            setSize(350, 300);
        } else {
            setSize(350, 250);
        }
    }

    public void showCart() {
        CartPanel cartPanel = new CartPanel(this, currentUserID);
        mainPanel.add(cartPanel, "CartPanel");
        switchTo("CartPanel");
    }

    public void showShop() {
        ShopPanel shopPanel = new ShopPanel(this, currentUserID);
        mainPanel.add(shopPanel, "ShopPanel");
        switchTo("ShopPanel");
    }

    public void showUserProfile() {
        UserProfilePanel userProfilePanel = new UserProfilePanel(this, currentUserID);
        mainPanel.add(userProfilePanel, "UserProfilePanel");
        switchTo("UserProfilePanel");
    }

    public void setUser(int userID) {
        this.currentUserID = userID;
    }

    public void logoutUser() {
        this.currentUserID = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setVisible(true);
        });
    }
}