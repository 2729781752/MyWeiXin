package message.Event;

/**
 * Created by asus on 2015/11/15.
 * 扫描带参数的二维码
 */
public class QRCodeEvent extends BaseEvent{
    //事件的key值（）
    private String EventKey;

    //用于换取二维码图片的票据
    private String Ticket;

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getEventKey() {

        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
