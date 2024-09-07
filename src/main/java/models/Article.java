package models;

public class Article {

    int articleId;
    String articleName;
    float price;
    String pathToPic;


    public Article(int articleId, String articleName, float price, String pathToPic) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.price = price;
        this.pathToPic = pathToPic;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public float getPrice() {
        return price;
    }

    public String getPathToPic() {
        return pathToPic;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", price=" + price +
                ", pathToPic='" + pathToPic + '\'' +
                '}';
    }
}
