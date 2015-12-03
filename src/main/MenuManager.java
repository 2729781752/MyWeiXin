package main;

import Util.CommonUtil;
import Util.MenuUtil;
import menu.*;
//import org.slf4j.LoggerFactory;
import pojo.Token;
//import org.slf4j.Logger;


/**
 * Created by Administrator on 2015/11/21.
 */
public class MenuManager {
   // private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    /**
     * 定义菜单结构
     *
     * @return
     */
    private static Menu getMenu() {

        ViewButton btn11 = new ViewButton();
        btn11.setName("最新项目");
        btn11.setType("view");
        btn11.setUrl("http://111.177.117.94/five/items");

        ViewButton btn12 = new ViewButton();
        btn12.setName("项目发布");
        btn12.setType("view");
        btn12.setUrl("http://www.iteye.com");

        ViewButton btn13 = new ViewButton();
        btn13.setName("服务计时");
        btn13.setType("view");
        btn13.setUrl("http://www.iteye.com");

        ClickButton btn14 = new ClickButton();//111111111111111click
        btn14.setName("联系我们");
        btn14.setType("click");
        btn14.setKey("callus");//联系我们

        ViewButton btn15 = new ViewButton();
        btn15.setName("个人注册");
        btn15.setType("view");
        btn15.setUrl("http://111.177.117.94/four/html/index.html");



        ViewButton btn21 = new ViewButton();
        btn21.setName("最新动态");
        btn21.setType("view");
        btn21.setUrl("http://m.taobao.com");

        ClickButton btn22 = new ClickButton();//11111111111111111click
        btn22.setName("通知公告");
        btn22.setType("click");
        btn22.setKey("inform");//通知公告

        ClickButton btn23 = new ClickButton();
        btn23.setName("志愿风采");
        btn23.setType("click");
        btn23.setKey("volunteer");



        ClickButton btn31 = new ClickButton();//11111111111111111click
        btn31.setName("活动招募");
        btn31.setType("click");
        btn31.setKey("activity");

        ViewButton btn32 = new ViewButton();
        btn32.setName("我的项目");
        btn32.setType("view");
        btn32.setUrl("http://www.yi588.com");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("掌上服务");
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13,btn14,btn15 });


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("志愿资讯");
        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("志愿活动");
        mainBtn3.setSub_button(new Button[] { btn31, btn32 });


        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
        return menu;
    }

    public static void main(String[] args) {
        // 第三方用户唯一凭证

       String appId = "wx332d45de169c738f";
        // 第三方用户唯一凭证密钥
       String appSecret = "d4624c36b6795d1d99dcf0547af5443d";


       // String appId= "wx530299b7dd584037";//zss的密码
       // String appSecret="d4624c36b6795d1d99dcf0547af5443d";


        // 调用接口获取凭证
        Token token = CommonUtil.getToken(appId, appSecret);
        System.out.println(token);
        if (null != token) {
            // 创建菜单
          boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
            //boolean result = MenuUtil.deleteMenu(token.getAccessToken());

            // 判断菜单创建结果
            if (result)
               // log.info("菜单创建成功！");
              System.out.println("创建菜单成功！");
            else
               // log.info("菜单创建失败！");
                System.out.println("创建菜单失败");
        }
    }
}
