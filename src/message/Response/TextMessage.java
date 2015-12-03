package message.Response;

/**
 * Created by asus on 2015/11/15.
 * 响应的文本消息
 */
public class TextMessage extends BaseMessage{
    //恢复的消息的内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
