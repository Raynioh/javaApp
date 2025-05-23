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
        AdminPanel adminPanel = new AdminPanel(this);

        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(createProfilePanel, "CreateProfilePanel");
        mainPanel.add(adminPanel, "AdminPanel");

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
            setSize(350, 350);
        }
    }

    public void showLogin() {
        LoginPanel loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "LoginPanel");
        switchTo("LoginPanel");
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

    public void removePanel(Component comp) {
        mainPanel.remove(comp);
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