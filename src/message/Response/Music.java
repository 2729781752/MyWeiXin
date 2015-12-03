package message.Response;

/**
 * Created by asus on 2015/11/15.
 * 音乐类和语音类不同
 */
public class Music {
    //音乐标题
    private String Title;
    //音乐描述
    private String Description;
    //音乐链接
    private  String MusicUrl;
    //高品质的音乐的链接
    private String HQMusicUrl;
    //缩略图媒体Id
    private String ThumbMedia;

    public String getThumbMedia() {
        return ThumbMedia;
    }

    public void setThumbMedia(String thumbMedia) {
        ThumbMedia = thumbMedia;
    }

    public String getHQMusicUrl() {

        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getMusicUrl() {

        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
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
