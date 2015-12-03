package message.Event;

/**
 * Created by asus on 2015/11/15.
 *
 * 上报地理位置时间
 */
public class LocationEvent extends BaseEvent{
    //地理位置纬度
    private String Latitude;
    //地理位置经度
    private String Longitude;

    //地理位置精度
    private String Precision;

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }

    public String getLongitude() {

        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {

        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }
}
