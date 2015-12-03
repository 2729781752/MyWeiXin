package message.Event;

/**
 * Created by asus on 2015/11/15.
 * 自定义菜单事件
 */
public class MenuEvent extends BaseEvent {
    //事件的key值，与自定义菜单接口中的key值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
