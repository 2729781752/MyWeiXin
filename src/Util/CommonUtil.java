package Util;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
import pojo.Token;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;


/**
 * Created by Administrator on 2015/11/21.
 */
public class CommonUtil {
   // private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    //凭证获取(get)
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr){
        JSONObject jsonObject = null;
        try {
            //创建SSLContext对象，使用指定的喜人管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm, new java.security.SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置请求方式(GET/POST)
            conn.setRequestMethod(requestMethod);

            //当output不为null时，向输出流写数据
            if (null != outputStr){
                OutputStream outputStream = conn.getOutputStream();
                //编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            //从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){
                buffer.append(str);
            }
            //释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        }catch (ConnectException ce){
           // log.error("连接超时:{}",ce);
            System.out.print("连接超时");
        }catch (Exception e){
           // log.error("https请求异常:{}",e);
            System.out.print("https请求异常:{}");
        }
        return jsonObject;
    }

    public static Token getToken(String appid, String appsecret){
        Token token = null;
        String requestUrl = token_url.replace("APPID",appid).replace("APPSECRET",appsecret);
        //发起GET请求
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        System.out.print(jsonObject);
        if (null != jsonObject){
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
                System.out.print("ACCESS_TOKEN:"+token);
            }catch (JSONException E){
                token = null;
                //获取token失败
               // log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));

                System.out.println("获取token失败 errcode:{} errmsg:{}" + jsonObject.getString("errcode") + jsonObject.getString("errmsg"));
            }
        }
        return token;
    }
}
