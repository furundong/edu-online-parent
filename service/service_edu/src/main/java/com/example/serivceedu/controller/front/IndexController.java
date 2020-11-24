package com.example.serivceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.entity.EduCourse;
import com.example.commonutils.entity.EduTeacher;
import com.example.commonutils.response.R;
import com.example.serivceedu.service.EduCourseService;
import com.example.serivceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * create by Freedom on 2020/9/24
 */
@RestController
@RequestMapping("/edu/service/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping
    public R index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("sort");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        HashMap<String, Object> map = new HashMap<>();
        map.put("eduList",eduList);
        map.put("teacherList",teacherList);
        return R.ok().data(map);
    }
}
