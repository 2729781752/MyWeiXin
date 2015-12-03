package message.Request;

/**
 * Created by asus on 2015/11/15.
 */
public class LinkMessage extends BaseMessage{
    //消息的标题
    private String Title;
    //消息描述
    private String Description;
    //消息链接
    private String URl;

    public String getURl() {
        return URl;
    }

    public void setURl(String URl) {
        this.URl = URl;
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
