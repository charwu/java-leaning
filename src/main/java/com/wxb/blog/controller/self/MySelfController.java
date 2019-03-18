package com.wxb.blog.controller.self;

import org.springframework.stereotype.Controller;

@Controller
public class MySelfController {
    private static String name = "吴小兵"; // 姓名
    private static String nick = null; // 昵称
    private static Integer age = 27; // 年龄
    private static Integer hight = 178; // 身高
    private static Double wight = 74.5; // 体重
    private static String status = "也许是碌碌无为吧！"; // 人生状态
    private static String half = null; // 另一半
    private static String thinkHalf = null; // 个人想象的另一半

    /**
     * 技能：表白
     */
    public static void selfExpression(){
        System.out.println(thinkHalf+",我爱你，你呢？");
    }

    public static void main(String[] arga) throws Exception{
        int sleepTime = 2500;
        Thread.sleep(sleepTime);
        System.out.println();
        System.out.println("我~");
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("姓名:"+name+";昵称:"+nick+";年龄:"+age+";身高:"+hight+";体重:"+wight+";另一半(感情):"+half);
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("我的前半生~");
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println(status);
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("这样的我，找不到自己的价值，更别提拥有奢华的情感。");
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("不善于表达的我，就算遇到心仪的菇凉，也不可能遇到就说:");
        thinkHalf = "某某某";
        System.out.println();
        Thread.sleep(sleepTime);
        selfExpression();
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("这样等来的，可能是无尽的拒绝和伤悲。");
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("渐渐地，渐渐地，我变得不为所动，身边的狗粮一堆一堆丢给我，我也无动于衷。");
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("于是乎，动漫和游戏成为了我的精神粮食。");
        System.out.println();
        Thread.sleep(sleepTime);
        System.out.println("直到(快，主人要浪漫了，撒花)");
        System.out.println();
        Thread.sleep(sleepTime);
    }
}
