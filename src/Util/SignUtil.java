package Util;

/**
 * Created by asus on 2015/11/15.
 */

//该类的主要功能是将请求校验的流程封装起来，以便于servlet调用

        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.Arrays;

/**
 * Created by asus on 2015/11/11.
 */
public class SignUtil {
    //这里的token应该与开发者模式中定义的token保持一致，不然验证一定会出错
    public static String token = "weiXin";//这是一个token令牌，有了这个token，微信服务器才能将请求发送给你


    //param signature 微信加密签名（注意这里的signature是一个16进制的字符串）
    //param timestamp 时间戳
    //param nonce 随机数
    //验证的原理是将 token timestamp nonce 三个字符串拼接起来之后（加密同时转换成16进制的字符串），与signature进行比对，看是不是相等，相等则验证成功


    //定义一个封装check signature的方法

    public static boolean CheckSignature(String signature, String timestamp, String nonce) {
        //对token，timestamp，nonce按字典进行排序（可以使用Arrays类的sort方法，所以要先将这几个字符串，保存到字符串数组中）
        String[] paramArr = new String[]{token, timestamp, nonce};
        Arrays.sort(paramArr);//将数组中的元素按照字典进行排序
        boolean flag = false;//用于返回判定结果的(默认是false)

        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);//将排序后的字符串，拼接成一个完整的字符串

        String ciphertext = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //使用MessageDigest类对content进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());//使用sha1对字符串进行加密后返回的是一个字节数组，二我们最终的任务是获得一个经过加密后的16进制的字符串
            ciphertext = byteToStr(digest);//使用一个自己创建的方法byteToStr,将传递的digest数组，变成16进制的字符

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        //校验
        if (ciphertext != null) {

            if (ciphertext.equals(signature.toUpperCase())) {
                flag = true;
            } else
                flag = false;
        }
        return flag;
    }


    static String byteToStr(byte[] byteArrays){//该方法是将字节 数组 变成十六进制的字符串(就是将每个字符数组中的元素，先转换成16进制的字符串)，然后拼接起来
        String strDigest = "";
        for(int i=0;i<byteArrays.length;i++){
            strDigest += byteToHexStr(byteArrays[i]);
        }

        return strDigest;
    }

    static String byteToHexStr(byte Mybyte){//将每个字节变成16进制的字符串
        char[] digest = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        char[]  tempArr = new char[2];
        tempArr[0] = digest[(Mybyte >>> 4) & 0X0f];//这个是取字节的高四位
        tempArr[1] = digest[Mybyte & 0x0f];//这个是取出字节的低四位
        String s = new String(tempArr);//用该字符数组构造一个字符串

        return s;
    }
}

