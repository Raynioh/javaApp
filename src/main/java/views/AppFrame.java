package views;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

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
        ShopPanel shopPanel = new ShopPanel(this);

        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(createProfilePanel, "CreateProfilePanel");
        mainPanel.add(shopPanel, "ShopPanel");

        add(mainPanel);
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
        // Adjust frame size if needed when switching panels
        if (panelName.equals("ShopPanel")) {
            setSize(400, 500);
        } else {
            setSize(350, 250);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setVisible(true);
        });
    }
}