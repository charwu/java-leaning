package com.wxb.blog.controller.learn;

import java.util.Scanner;

/**
 * 不用其他变量实现两个变量的转换
 */
public class VariableExchange {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入变量A的值");
        Long A = scan.nextLong();
        System.out.println("请输入变量B的值");
        Long B = scan.nextLong();
        System.out.println("A=" + A + "\tB=" + B);
        System.out.println("执行变量互换");
        A = A ^ B;
        B = B ^ A;
        A = A ^ B;
        System.out.println("A=" + A + "\tB=" + B);
    }
}
