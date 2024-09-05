package views;

import models.Article;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShopPanel extends JPanel {
    private List<Article> articles;
    private AppFrame appFrame;

    public ShopPanel(AppFrame appFrame, User user) {
        this.appFrame = appFrame;
        this.articles = createArticles();
        setLayout(new GridLayout(articles.size() + 2, 1, 10, 10));

        JPanel appBar = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton profileButton = new JButton("My profile");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);
        appBar.add(buttonPanel, BorderLayout.NORTH);

        add(appBar);

        for (Article article : articles) {
            add(createArticlePanel(article));
        }

        JPanel buyPanel = new JPanel();
        JButton buyButton = new JButton("Buy");
        buyPanel.add(buyButton, BorderLayout.EAST);
        add(buyPanel);

        profileButton.addActionListener(e -> {
            appFrame.showUserProfile();
        });
        logoutButton.addActionListener(e -> {
            appFrame.logoutUser();
            appFrame.switchTo("LoginPanel");
        });
        buyButton.addActionListener(e -> {
            appFrame.showCart();
        });
    }

    private JPanel createArticlePanel(Article article) {
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
        rightPanel.add(priceLabel);
        rightPanel.add(quantitySpinner);

        // Add both sides to the panel
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);

        return panel;
    }

    private List<Article> createArticles() {
        // Normally article would be fetched from a database or another source
        List<Article> article = new ArrayList<>();
        article.add(new Article(1, "Eggs", 2.49f, "src/main/resources/eggs.jpg"));
        article.add(new Article(2, "Milk", 1.49f, "src/main/resources/milk.jpg"));
        article.add(new Article(3, "Bread", 0.5f, "src/main/resources/bread.png"));
        return article;
    }
}