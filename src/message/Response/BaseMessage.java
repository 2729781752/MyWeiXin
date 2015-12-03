package message.Response;

/**
 * Created by asus on 2015/11/15.
 */
public class BaseMessage {
    //接收方的账号（用户OpenId）这里的OpenId,是用户对于每个公众平台的唯一的标识符
    private String ToUserName;

    //发送方账号
    private String FromUserName;

    //消息的创建时间

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getCreateTime() {

        return CreateTime;
    }

    public void setCreateTime(long createTime) {
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

    private long CreateTime;

    //消息的类型
    private String MsgType;

}
