package com.wxb.blog.controller.ai;

import com.wxb.blog.bean.Test;
import com.wxb.blog.common.utils.BruteForceUtil;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * AI 核心代码，价值1亿；
 */
@Controller
public class AiController {

    public static void main(String args[]) throws Exception{
        BigDecimal money = new BigDecimal("0");
        for (int i= 1;i<31; i++){
            BigDecimal everyDay = new BigDecimal("0");
            if(i == 1){
                everyDay = new BigDecimal("0.01");
            }else{
                everyDay = new BigDecimal("0.02");
                for(int d =2; d<i;d++){
                    everyDay = everyDay.multiply(new BigDecimal("2"));
                }
            }
            money = money.add(everyDay);
            System.out.println("第"+ i +"天：当前应该给出 ==》"+ everyDay + "   累计金额==》" + money);

        }
        System.out.println("30天总金额："+ money);
    }
}
