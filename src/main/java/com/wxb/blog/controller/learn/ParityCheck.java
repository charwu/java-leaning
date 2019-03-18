package com.wxb.blog.controller.learn;

import java.util.Scanner;

/**
 * 判断数字的奇偶性
 */
public class ParityCheck {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        Long number = scan.nextLong();
        String check = (number % 2 == 0) ? "这个数字是：偶数":"这个数字是：奇数";
        System.out.println(check);
    }
}
