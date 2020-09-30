package com.example.commonutils.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * create by Freedom on 2020/9/9
 */
@Data
public class CoursePublishVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
