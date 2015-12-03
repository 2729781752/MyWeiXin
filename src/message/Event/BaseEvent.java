package message.Event;

/**
 * Created by asus on 2015/11/15.
 *
 * 事件的基础类
 */
public class BaseEvent {
    //开发者微信号（公众平台账号的原始的微信号）
    private String ToUserName;
    //发送方的微信号
    private String FromUserName;
    //消息的创建时间
    private String CreateTime;
    //消息类型
    private String MsgType;
    //事件类型
    private String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getMsgType() {

        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getCreateTime() {

        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getFromUserName() {

        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {

        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
}
