package com.wxb.blog.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SerTest implements Serializable {
    private Integer user_id;
    private String nickname;
    private String filename;
    private String ofilename;
    private Integer createtime;
    private Integer filesize;
    private String filext;
}
