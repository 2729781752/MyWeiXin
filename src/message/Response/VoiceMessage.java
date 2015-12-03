package message.Response;

/**
 * Created by asus on 2015/11/15.
 */
public class VoiceMessage extends BaseMessage {
    //语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
