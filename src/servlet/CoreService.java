package servlet;

import Util.MessageUtil;
import message.Response.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 处理微信发来的消息
 * Created by asus on 2015/11/15.
 */
public class CoreService {//以下创建的所有的消息对象，最终都会转换成xml


    public static String processRequest(HttpServletRequest request){

         ServletContext sc = request.getServletContext();

        //XML格式的消息数据
         String respXml = null;
        //默认返回的文本消息
         String respContent = "未知的消息类型";


       try{
           //调用parseXml方法解析request 中xml文档
           //System.out.println("ddddddddddddddddddddddddddddd");

            Map<String,String> requestMap = MessageUtil.parseXml(request);

           //System.out.println("Adduuuuuuuuuuuuuuuuuuuuuuuuuuu");

           //发送方账号
           String fromUserName = requestMap.get("FromUserName");
           //System.out.println(fromUserName);

           //开发者的微信号
           String toUserName = requestMap.get("ToUserName");
           //消息类型
           System.out.println("接收者"+toUserName);

           String msgType = requestMap.get("MsgType");

           System.out.println("消息类型"+msgType);

 //11111111111111111111111111111111111111  消息推送
           //文本消息
           if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){

               System.out.println("进入文本消息体");
               String recContent = requestMap.get("Content");
               TextMessage textMessage  = new TextMessage();
               textMessage.setFromUserName(toUserName);
               textMessage.setToUserName(fromUserName);
               textMessage.setMsgType(msgType);
               textMessage.setCreateTime(new Date().getTime());

               respContent = "您发送的文本消息内容是："+recContent;
               //设置文本消息的内容
               textMessage.setContent(respContent);
               //将文本消息对象转换成xml
               respXml = MessageUtil.messageToXml(textMessage);
           }

           //图片消息
          else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){

               String MediaId = requestMap.get("MediaId");//2222222222222
               String PicUrl = requestMap.get("PicUrl");

               System.out.println("进入图片消息体");
               sc.setAttribute("url",PicUrl);
               sc.setAttribute("id",MediaId);

               ImageMessage imageMessage = new ImageMessage();
               imageMessage.setFromUserName(toUserName);
               imageMessage.setToUserName(fromUserName);
               imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
               imageMessage.setCreateTime(new Date().getTime());

               Image image = new Image();

               image.setMediaId(MediaId);
               imageMessage.setImage(image);

               respXml = MessageUtil.messageToXml(imageMessage);
               System.out.println(respXml);
           }

           //语音消息
          else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){

               System.out.println("进入语音消息体");
               String recognition = requestMap.get("Recognition");
               System.out.println(recognition);
               System.out.println(recognition + "11111");

               String MediaId = requestMap.get("MediaId");
               Voice voice = new Voice();
               voice.setMediaId(MediaId);

               VoiceMessage voiceMessage = new VoiceMessage();
               voiceMessage.setFromUserName(toUserName);
               voiceMessage.setToUserName(fromUserName);
               voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
               voiceMessage.setCreateTime(new Date().getTime());
               voiceMessage.setVoice(voice);
               respXml = MessageUtil.messageToXml(voiceMessage);//一定要记得将语音对象转换成xml
               System.out.println(respXml);
           }

           //视屏消息
          else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
               respContent = "您发送的是视屏消息";
           }

           //地理位置消息
          else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){

           }

           //链接消息
          else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
               respContent = "您发送的是链接消息";
           }


 //2222222222222222222222222222222222222事件推送
           ///事件推送
          else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){

               System.out.println("进入事件处理体");
               //事件类型
               String eventType = requestMap.get("Event");

               //关注
               if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){

                   System.out.println("进入关注事件体");
                   TextMessage textMessage  = new TextMessage();
                   textMessage.setFromUserName(toUserName);
                   textMessage.setToUserName(fromUserName);
                   textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                   textMessage.setCreateTime(new Date().getTime());
                   respContent = "您好,欢迎关注 zhiyuanxiangyang!";
                   textMessage.setContent(respContent);
                   respXml = MessageUtil.messageToXml(textMessage);
                   System.out.print(respXml);
               }

               //取消关注
              else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
                //nothing
               }

               //扫描带参数的二维码
              else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
                    //TODO
               }

               //上报地理位置
              else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){

               }



  ///////////////////////////////////////////自定义的菜单的点击事件
               //自定义菜单(这里只需要对点击类型的按钮进行处理)
              else  if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
// 事件KEY值，与创建菜单时的key值对应
                   System.out.println("进入callus按钮点击事件体");
                   String eventKey = requestMap.get("EventKey");

                   // 根据key值判断用户点击的按钮
                   if (eventKey.equals("callus")) {//联系我们的图文消息（可嵌入image,但官方不支持识别）

                       String PicUrl = "http://mmbiz.qpic.cn/mmbiz/bicU3ibHUJJwUAoOjSiaEicCSAz9O7ypP7z7zCrQ7RgViapfalia" +
                               "FuVXGmEeqBrsDczS9OicKbCj48Oiaa7secB4lGiaGjw/0";

                      // String PicUrl = (String)sc.getAttribute("url");
                       Article article = new Article();
                       article.setTitle("联系我们");

                       article.setDescription("襄阳志愿者协会客服在线");

                       article.setPicUrl(PicUrl);//图片的原图的链接
                       System.out.println("图片的picurl");

                       article.setUrl("http://111.177.117.94/one/html/call.html");//第三方的客服管理系统的URL

                       List<Article> articleList = new ArrayList<Article>();//存储文章的列表
                       articleList.add(article);

                       // 创建图文消息（Article是在List中然后被一起包含在NewsMessage中）

                       NewsMessage newsMessage = new NewsMessage();
                       newsMessage.setToUserName(fromUserName);
                       newsMessage.setFromUserName(toUserName);
                       newsMessage.setCreateTime(new Date().getTime());
                       newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                       newsMessage.setArticleCount(articleList.size());

                       newsMessage.setArticles(articleList);
                       respXml = MessageUtil.messageToXml(newsMessage);


                   } else if (eventKey.equals("inform")) {//通知公告的图文消息

                       String PicUrl = "http://mmbiz.qpic.cn/mmbiz/bicU3ibHUJJwUAoOjSiaEicCSAz9O7ypP7z7si" +
                               "cktuxOiaVlunDcxmib8rmSr4XNBm7xdo9kJ0qwLwA3TicFX3NMpymA5g/0";

                       System.out.println("进入inform按钮点击事件体");
                       //String PicUrl = (String)sc.getAttribute("url");

                       Article article = new Article();
                       article.setTitle("通知公告");
                       article.setDescription("笑言义工志愿者招募公告");

                       article.setPicUrl(PicUrl);//图片的原图的链接

                       article.setUrl("http://111.177.117.94/one/html/yg.html");//第三方的客服管理系统的URL
                       List<Article> articleList = new ArrayList<Article>();
                       articleList.add(article);

                       // 创建图文消息（Article是在List中然后被一起包含在NewsMessage中）

                       NewsMessage newsMessage = new NewsMessage();
                       newsMessage.setToUserName(fromUserName);
                       newsMessage.setFromUserName(toUserName);
                       newsMessage.setCreateTime(new Date().getTime());
                       newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                       newsMessage.setArticleCount(articleList.size());
                       newsMessage.setArticles(articleList);
                       respXml = MessageUtil.messageToXml(newsMessage);


                   }else if (eventKey.equals("volunteer")) {//志愿风采图文消息//ooooooooooooooooooo000000000000000000000

                      String PicUrl = "http://mmbiz.qpic.cn/mmbiz/bicU3ibHUJJwUAoOjSiaEicCSAz9O7ypP7z7sUGkoG7FtqoVib" +
                              "6lwjKj3tc3zYqgMBpmuaX2MDFqWMrT5S6qkjQ1rGA/0";

                       System.out.println("进入volunteer按钮点击事件体");

                       //这里需要调用获取永久图片素材的方法/////////////////////////////////000000000000000000000000000000000

                       //String PicUrl = (String)sc.getAttribute("url");//这个是将用户发给我的图片的链接保存在上下文中

                       Article article = new Article();
                       article.setTitle("志愿风采");
                       article.setDescription("襄阳环保协会");

                       article.setPicUrl(PicUrl);//图片的原图的链接

                       article.setUrl("http://111.177.117.94/one/html/hb.html");//第三方的客服管理系统的URL
                       List<Article> articleList = new ArrayList<Article>();
                       articleList.add(article);

                       // 创建图文消息（Article是在List中然后被一起包含在NewsMessage中）

                       NewsMessage newsMessage = new NewsMessage();
                       newsMessage.setToUserName(fromUserName);
                       newsMessage.setFromUserName(toUserName);
                       newsMessage.setCreateTime(new Date().getTime());
                       newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                       newsMessage.setArticleCount(articleList.size());
                       newsMessage.setArticles(articleList);
                       respXml = MessageUtil.messageToXml(newsMessage);

                   } else if (eventKey.equals("activity")) {//活动招募的图文消息
                       String PicUrl = "http://mmbiz.qpic.cn/mmbiz/bicU3ibHUJJwUAoOjSiaEicCSAz9O7ypP7z7Rl" +
                               "jialSy9MVuQRbJj9uX4BcQCwegCTtmAN8fZbapW4k9YdFFvBtf08A/0";

                       //String PicUrl = (String)sc.getAttribute("url");

                       System.out.println("进入activity按钮点击事件体");
                       Article article = new Article();

                       article.setDescription("晨光小学助力毕业班家长正确面对孩子的毕业考");

                       article.setPicUrl(PicUrl);//图片的原图的链接

                       article.setUrl("http://111.177.117.94/one/html/zm.html");//第三方的客服管理系统的URL
                       List<Article> articleList = new ArrayList<Article>();
                       articleList.add(article);

                       // 创建图文消息（Article是在List中然后被一起包含在NewsMessage中）
                       NewsMessage newsMessage = new NewsMessage();
                       newsMessage.setToUserName(fromUserName);
                       newsMessage.setFromUserName(toUserName);
                       newsMessage.setCreateTime(new Date().getTime());

                       newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                       newsMessage.setArticleCount(articleList.size());
                       newsMessage.setArticles(articleList);
                       respXml = MessageUtil.messageToXml(newsMessage);
                   }
               }
           }



       }catch(Exception e){
           e.printStackTrace();
       }
        return respXml;
    }
}
