 package Util;


 import message.Response.*;
 import message.Response.Article;
 import com.thoughtworks.xstream.XStream;
 import com.thoughtworks.xstream.core.util.QuickWriter;
 import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
 import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
 import com.thoughtworks.xstream.io.xml.XppDriver;
 import org.dom4j.Document;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;

 import javax.servlet.http.HttpServletRequest;
 import java.io.InputStream;
 import java.io.Writer;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 /**
 * Created by bgm on 2015/11/15.
 */
public class MessageUtil {
    //各种请求的类型
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    //事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_SCAN = "scan";
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    public static final String EVENT_TYPE_CLICK = "CLICK";


    //各种响应的消息类型
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 解析微信发来的请求
     * */

    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{//解析微信服务器端发过来的xml包
        //System.out.println("0000000000000000");
        Map<String,String> map = new HashMap<String,String>();
        //System.out.println("1111111111111111");

        InputStream inputStream = request.getInputStream();
        //System.out.println("2222222222222222222222");
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        //System.out.println("3333333333333333");
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();


        System.out.println("用户发过来的xml数据包！");
        for(Element e : elementList) {
            map.put(e.getName(), e.getText());
            System.out.println(e.getName());
        }
        inputStream.close();
        inputStream = null;


        //System.out.println("MessageUtil2222222222");
        return map;
    }


    /**
     * 扩展xstream使其支持CDATA
     * */
    private static XStream xstream = new XStream(new XppDriver(){

        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){

                boolean cdata = true;
                public void startNode(String name , Class clazz){
                    super.startNode(name,clazz);
                }
                protected void writeText(QuickWriter writer , String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else{
                        writer.write(text);
                    }
                }
            };
        }
    });


    public static String messageToXml(TextMessage textMessage){
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    public static String messageToXml(ImageMessage imageMessage){
        xstream.alias("xml",imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }
    public static String messageToXml(VoiceMessage voiceMessage){
        xstream.alias("xml",voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }
    public static String messageToXml(VideoMessage videoMessage){
        xstream.alias("xml",videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }
    public static String messageToXml(MusicMessage musicMessage){
        xstream.alias("xml",musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    public static String messageToXml(NewsMessage newsMessage){//图文消息
        xstream.alias("xml",newsMessage.getClass());
        xstream.alias("item",new Article().getClass());
        return xstream.toXML(newsMessage);
    }
}

