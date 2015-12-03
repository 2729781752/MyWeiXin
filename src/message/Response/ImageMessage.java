package message.Response;

/**
 * Created by asus on 2015/11/15.
 */
public class ImageMessage extends BaseMessage {
    //图片
    private Image Image;//图片对象

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
