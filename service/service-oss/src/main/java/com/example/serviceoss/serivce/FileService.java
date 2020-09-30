package com.example.serviceoss.serivce;

import com.example.commonutils.response.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * create by Freedom on 2020/8/19
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    R uploadFileAvatar(MultipartFile file);

    R deleteFileByPath(String path);

    R deleteFileByBatchPath(List<String> path);
}
