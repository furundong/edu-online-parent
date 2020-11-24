package com.example.serivceedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.entity.EduComment;
import com.example.serivceedu.dao.EduCommentDao;
import com.example.serivceedu.service.EduCommentService;
import org.springframework.stereotype.Service;

/**
 * 评论(EduComment)表服务实现类
 *
 * @author makejava
 * @since 2020-11-10 10:54:07
 */
@Service("eduCommentService")
public class EduCommentServiceImpl extends ServiceImpl<EduCommentDao, EduComment> implements EduCommentService {

}
