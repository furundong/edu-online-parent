package com.example.serivceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.commonutils.entity.EduComment;
import com.example.commonutils.entity.UcenterMember;
import com.example.commonutils.response.PageResult;
import com.example.commonutils.response.R;
import com.example.commonutils.utils.JwtUtils;
import com.example.serivceedu.client.UcenterClient;
import com.example.serivceedu.service.EduCommentService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 评论(EduComment)表控制层
 *
 * @author makejava
 * @since 2020-11-10 10:54:07
 */
@RestController
@RequestMapping("edu/comment")
public class EduCommentController {
    /**
     * 服务对象
     */
    @Resource
    private EduCommentService eduCommentService;

    @Resource
    private UcenterClient ucenterClient;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param courseId  课程ID
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EduComment> page, String courseId) {
        final QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        Page<EduComment> entity = this.eduCommentService.page(page,wrapper);
        return R.ok().data(new PageResult<>(entity.getTotal(), entity.getRecords()));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        EduComment entityById = this.eduCommentService.getById(id);
        return R.ok().data(entityById);
    }

    /**
     * 新增数据
     *
     * @param comment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        comment.setMemberId(memberId);

        UcenterMember ucenterInfo = ucenterClient.getInfoUc(memberId);
        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());

        eduCommentService.save(comment);
        return R.ok();
    }

    /**
     * 修改数据
     *
     * @param eduComment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EduComment eduComment) {
        this.eduCommentService.updateById(eduComment);
        return R.ok();
    }

    /**
     * 删除数据单条数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable("id") String id) {
        this.eduCommentService.removeById(id);
        return R.ok();
    }

    /**
     * 批量删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("batchDelete")
    public R delete(@RequestParam("idList") List<String> idList) {
        this.eduCommentService.removeByIds(idList);
        return R.ok();
    }
}
