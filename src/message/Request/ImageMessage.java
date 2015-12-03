package message.Request;

/**
 * Created by asus on 2015/11/15.
 */
public class ImageMessage extends BaseMessage{
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
