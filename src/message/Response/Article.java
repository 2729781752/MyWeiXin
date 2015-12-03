package message.Response;

/**
 * Created by asus on 2015/11/15.
 * 图文model
 */
public class Article {
    //图文消息的名称
    private String Title;

    //图文消息的描述
    private String Description;

    //图片链接（这张大图片存在那个位置）
    private String PicUrl;

    //点击图文消息跳转链接
    private String Url;

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getPicUrl() {

        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getDescription() {

        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
