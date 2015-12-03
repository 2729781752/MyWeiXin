package message.Response;

/**
 * Created by asus on 2015/11/15.
 */
public class Video {
    //媒体ID
    private String MediaId;
    //缩略图的媒体id
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
