package message.Request;

/**
 * Created by asus on 2015/11/15.
 */
public class VideoMessage extends BaseMessage {
    //视屏媒体的ID
    private String MediaId;

    //视屏消息缩略图的媒体ID
    private String ThumbMediaId;

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getMediaId() {

        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
