package com.example.commonutils.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * create by Freedom on 2020/9/8
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
}
