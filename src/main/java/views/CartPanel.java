package views;

import models.Article;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CartPanel extends JPanel {
    private List<Article> articles;
    private AppFrame appFrame;

    public CartPanel(AppFrame appFrame, int userID) {
        this.appFrame = appFrame;
        this.articles = createArticles();
        setLayout(new BorderLayout());

        // Create the app bar with "Back to Shop" and "Logout" buttons
        JPanel appBar = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backToShopButton = new JButton("Back to Shop");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(backToShopButton);
        buttonPanel.add(logoutButton);
        appBar.add(buttonPanel, BorderLayout.NORTH);

        // Cart content
        JPanel cartContent = new JPanel();
        cartContent.setLayout(new GridLayout(articles.size() + 1, 4, 10, 10));

        for (Article article : articles) {
            // Item Image and Name
            JLabel imageLabel = new JLabel(new ImageIcon(article.getPathToPic()));
            JLabel nameLabel = new JLabel(article.getArticleName());
            cartContent.add(imageLabel);
            cartContent.add(nameLabel);

            // Item Quantity
            JLabel quantityLabel = new JLabel("Qty: " + "1");
            cartContent.add(quantityLabel);

            // Item Price
            JLabel priceLabel = new JLabel(String.format("$%.2f", article.getPrice()));
            cartContent.add(priceLabel);
        }

        // Total price row
        cartContent.add(new JLabel("")); // Empty cells
        cartContent.add(new JLabel(""));
        cartContent.add(new JLabel(""));
        JLabel totalPriceLabel = new JLabel(String.format("Total: $%.2f", calculateTotalPrice()));
        totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cartContent.add(totalPriceLabel);

        add(appBar, BorderLayout.NORTH);
        add(cartContent, BorderLayout.CENTER);

        backToShopButton.addActionListener(e -> {
            appFrame.showShop();
        });
        logoutButton.addActionListener(e -> {
            appFrame.logoutUser();
            appFrame.switchTo("LoginPanel");
        });
    }

    private List<Article> createArticles() {
        // Normally article would be fetched from a database or another source
        List<Article> article = new ArrayList<>();
        article.add(new Article(1, "Eggs", 2.49f, "src/main/resources/eggs.jpg"));
        article.add(new Article(2, "Milk", 1.49f, "src/main/resources/milk.jpg"));
        article.add(new Article(3, "Bread", 0.5f, "src/main/resources/bread.png"));
        return article;
    }

    private double calculateTotalPrice() {
        return articles.stream().mapToDouble(value -> value.getPrice()).sum();
    }
}
