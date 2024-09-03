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

        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(createProfilePanel, "CreateProfilePanel");

        add(mainPanel);
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setVisible(true);
        });
    }
}