package com.example.serviceorder.client;

import com.example.commonutils.entity.dto.CourseInfoForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * create by Freedom on 2020/11/20
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/edu/course/front/getDto/{courseId}")
    CourseInfoForm getCourseInfoDto(@PathVariable("courseId") String courseId);
}
