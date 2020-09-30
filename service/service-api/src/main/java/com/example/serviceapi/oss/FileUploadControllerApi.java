package com.example.serviceapi.oss;

import com.example.commonutils.response.R;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * create by Freedom on 2020/8/19
 */
public interface FileUploadControllerApi {


    /**
     * 文件上传
     *
     * @param file
     */
    R upload(@RequestParam("file") MultipartFile file) ;

}
