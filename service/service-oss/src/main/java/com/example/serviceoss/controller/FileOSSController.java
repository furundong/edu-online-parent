package com.example.serviceoss.controller;

import com.example.commonutils.response.R;
import com.example.serviceapi.oss.FileUploadControllerApi;
import com.example.serviceoss.serivce.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by Freedom on 2020/8/19
 */
@RestController
@RequestMapping("/admin/oss/file")
public class FileOSSController implements FileUploadControllerApi {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file MultipartFile
     */
    @PostMapping("upload")
    public R upload(@RequestParam("file") MultipartFile file) {

        return fileService.uploadFileAvatar(file);
        //返回r对象
    }

    @DeleteMapping
    public R deleteByPath(String path) {
        return fileService.deleteFileByPath(path);
    }

    @DeleteMapping("batch")
    public R deleteFileByBatchPath(List<String> path) {
        return fileService.deleteFileByBatchPath(path);
    }

}
