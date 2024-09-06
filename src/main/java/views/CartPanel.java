package views;

import controllers.CartController;
import models.Article;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartPanel extends JPanel {
    private Map<Article, Integer> articles;
    private AppFrame appFrame;
    private CartController cc = new CartController();

    public CartPanel(AppFrame appFrame, int userID) {
        this.appFrame = appFrame;
        this.articles = cc.getUserArticles(userID);
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

        for (Map.Entry<Article, Integer> e: articles.entrySet()) {
            Article article = e.getKey();
            int quantity = e.getValue();
            // Item Image and Name
            JLabel imageLabel = new JLabel(new ImageIcon(article.getPathToPic()));
            JLabel nameLabel = new JLabel(article.getArticleName());
            cartContent.add(imageLabel);
            cartContent.add(nameLabel);

            // Item Quantity
            JLabel quantityLabel = new JLabel("Qty: " + quantity);
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

    private double calculateTotalPrice() {
        return articles.entrySet().stream().mapToDouble(value -> value.getValue() * value.getKey().getPrice()).sum();
    }
}
