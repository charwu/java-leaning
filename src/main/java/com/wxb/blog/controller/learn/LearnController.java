package com.wxb.blog.controller.learn;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
public class LearnController {
    public static void main(String[] args) throws Exception{
        int[] a = new int[]{3,2,1,2,1,7};
        int c = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < a.length; i++){
            while (Arrays.asList(list).contains(a[i])){
                a[i] ++;
                c++;
            }
            list.add(a[i]);
        }
        System.out.println(a);
    }

}
