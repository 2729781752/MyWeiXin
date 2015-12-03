package message.Response;

import java.util.List;

/**
 * Created by asus on 2015/11/15.
 */
public class NewsMessage extends  BaseMessage{
 //图文消息的条数
    private int ArticleCount;
    //图文消息一般有多条，所以用list，并且第一个默认是大图
    private List<Article> Articles;


    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }


    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }
}
