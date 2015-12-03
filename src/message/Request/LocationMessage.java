package message.Request;

/**
 * Created by asus on 2015/11/15.
 */
public class LocationMessage extends BaseMessage{
    //地理位置纬度
    private String Location_x;

    //地理位置经度
    private  String Location_Y;

    //地图的缩放大小
    private  String Scale;

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getScale() {

        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLocation_Y() {

        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getLocation_x() {

        return Location_x;
    }

    public void setLocation_x(String location_x) {
        Location_x = location_x;
    }

    //地理位置信息
    private String Label;


}
