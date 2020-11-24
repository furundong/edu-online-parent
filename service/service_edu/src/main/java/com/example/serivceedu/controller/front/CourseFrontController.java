package com.example.serivceedu.controller.front;

import com.example.commonutils.entity.dto.CourseInfoForm;
import com.example.commonutils.entity.vo.ChapterVo;
import com.example.commonutils.entity.vo.CourseWebVo;
import com.example.commonutils.response.R;
import com.example.commonutils.utils.JwtUtils;
import com.example.serivceedu.client.OrderClient;
import com.example.serivceedu.service.EduChapterService;
import com.example.serivceedu.service.EduCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * create by Freedom on 2020/11/12
 */
@RestController
@RequestMapping("/edu/course/front")
public class CourseFrontController {

    @Resource
    private EduCourseService courseService;

    @Resource
    private EduChapterService chapterService;

    @Resource
    private OrderClient orderClient;

    //2 课程详情的方法
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.nestedList(courseId);


        //远程调用，判断课程是否被购买
        boolean buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), courseId);

//        //根据课程id和用户id查询当前课程是否已经支付过了
//        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        final HashMap<String, Object> result = new HashMap<>();
        result.put("courseWebVo",courseWebVo);
        result.put("chapterVideoList",chapterVideoList);
        result.put("isBuy",buyCourse);
        return R.ok().data(result);
    }

    //根据课程id查询课程信息
    @GetMapping("/getDto/{courseId}")
    public CourseInfoForm getCourseInfoDto(@PathVariable String courseId) {
        return courseService.getCourseInfoFormById(courseId);
    }
}
