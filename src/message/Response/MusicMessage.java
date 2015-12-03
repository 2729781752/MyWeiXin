package message.Response;

/**
 * Created by asus on 2015/11/15.
 */
public class MusicMessage extends BaseMessage {
    //音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
