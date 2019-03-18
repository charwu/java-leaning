package com.wxb.blog.controller.learn;

import java.util.Scanner;

/**
 * 数据类型转换
 */
public class NumberChange {
    public static void main(String[] args){
        rule();
    }

    /**
     *  byte,short,char                 +++++++++   int    ======>  int
     *  byte,short,char,int             +++++++++   long   ======>  loog
     *  byte,short,char,int,long        +++++++++   float  ======>  float
     *  byte,short,char,int,long,float  +++++++++   double ======>  double
     */
    private static void rule(){
        System.out.println("double 转换 int  ===> int val = (int)[doubleValue]");
        System.out.println("float 转换 long  ===> long val = (long)[floatValue]");
        int b = (int)'d';
        System.out.println(b);
    }
}
