package views;

import controllers.ShopController;
import models.Article;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShopPanel extends JPanel {
    private List<Article> articles;
    private AppFrame appFrame;
    private ShopController sc = new ShopController();

    public ShopPanel(AppFrame appFrame, int userID) {
        this.appFrame = appFrame;
        this.articles = sc.getArticles();
        setLayout(new GridLayout(articles.size() + 1, 1, 10, 10));

        JPanel appBar = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton profileButton = new JButton("My profile");
        JButton cartButton = new JButton("My cart");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(profileButton);
        buttonPanel.add(cartButton);
        buttonPanel.add(logoutButton);
        appBar.add(buttonPanel, BorderLayout.NORTH);

        add(appBar);

        for (Article article : articles) {
            add(createArticlePanel(article, userID));
        }

        profileButton.addActionListener(e -> {
            appFrame.showUserProfile();
        });
        logoutButton.addActionListener(e -> {
            appFrame.logoutUser();
            appFrame.showLogin();
        });
        cartButton.addActionListener(e -> {
            appFrame.showCart();
        });
    }

    private JPanel createArticlePanel(Article article, int userID) {
        JPanel panel = new JPanel(new BorderLayout());

        // Left side (Image and Name)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        JLabel imageLabel = new JLabel(new ImageIcon(article.getPathToPic()));
        JLabel nameLabel = new JLabel(article.getArticleName());
        leftPanel.add(imageLabel);
        leftPanel.add(nameLabel);

        // Right side (Price and Spinner)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JLabel priceLabel = new JLabel("Price: €" + article.getPrice());
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Default to 0, max 100
        JButton addButton = new JButton("Add to Cart");

        rightPanel.add(priceLabel);
        rightPanel.add(quantitySpinner);
        rightPanel.add(addButton);

        // Add both sides to the panel
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);

        addButton.addActionListener(e -> {
            int quantity = (int) quantitySpinner.getValue();
            if (quantity > 0) {
                sc.addArticles(userID, article, quantity);
                JOptionPane.showMessageDialog(this, "Item added to cart!");
            }
        });

        return panel;
    }
}