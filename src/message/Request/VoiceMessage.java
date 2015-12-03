package message.Request;

/**
 * Created by asus on 2015/11/15.
 */
public class VoiceMessage extends BaseMessage{
    //媒体ID
    private String MediaId;

    //语音文件的格式
    private String Format;

    //语音的识别结果
    private String Recognition;

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public String getFormat() {

        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMediaId() {

        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
