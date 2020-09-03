package com.example.serviceoss.controller;

import com.example.commonutils.R;
import com.example.serviceoss.serivce.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * create by Freedom on 2020/8/19
 */
@RestController
@RequestMapping("/admin/oss/file")
public class FileUploadController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @PostMapping("upload")
    public R upload(@RequestParam("file") MultipartFile file) {

        return fileService.uploadFileAvatar(file);
        //返回r对象
    }

}
