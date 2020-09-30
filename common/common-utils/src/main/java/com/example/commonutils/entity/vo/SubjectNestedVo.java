package com.example.commonutils.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * create by Freedom on 2020/9/3
 */
@Data
public class SubjectNestedVo {

    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
