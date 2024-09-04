package views;

import models.Article;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShopPanel extends JPanel {
    private List<Article> article;
    private AppFrame appFrame;

    public ShopPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        this.article = createArticles();
        setLayout(new GridLayout(article.size(), 1, 10, 10));

        for (Article article : article) {
            add(createArticlePanel(article));
        }
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