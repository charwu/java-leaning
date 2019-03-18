package com.wxb.blog.biz.math;

import com.wxb.blog.model.form.math.MathForm;
import com.wxb.blog.model.vo.math.MathVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Component
public class MathBiz {
    private static final Logger logger = LoggerFactory.getLogger(MathBiz.class);

    public List<MathVO> run(MathForm form){
        List<MathVO> list = new ArrayList<>();
        for (int i = 0; i< form.getSize();i++){
            MathVO mathVO = getMathString(form.getStart(),form.getEnd());
            list.add(mathVO);
        }
        return list;
    }


    private Integer getMathType(){
        int max=2;
        int min=1;
        Random random = new Random();
        Integer s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

    private Integer getMathRand(Integer start,Integer end){
        Random random = new Random();
        Integer total = random.nextInt(end)%(end-start+1) + start;
        return total;
    }

    private MathVO getMathString(Integer start,Integer end){
        Integer type = getMathType();
        String formula = "";
        Integer key = 0,pass,total;
        while (true){
            pass = getMathRand(start,end);
            total = getMathRand(start,end);
            if(type == 1){
                if(pass >= total){
                    formula = pass + " - ( ) = " + total;
                    key = pass - total;
                }else {
                    formula = pass + " + ( ) = " + total;
                    key = total - pass;
                }
            }else if(type == 2){
                if(total >= pass){
                    formula = "( ) + " + pass + " = " + total;
                    key = total - pass;
                }else {
                    formula = "( ) - " + pass + " = " + total;
                    key = total + pass;
                    if(key >= end){
                        continue;
                    }
                }
            }
            break;
        }
        MathVO mathVO = new MathVO();
        mathVO.setFormula(formula);
        mathVO.setKey(key);
        return  mathVO;
    }

}
