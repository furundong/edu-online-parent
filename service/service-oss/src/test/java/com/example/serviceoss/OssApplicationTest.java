package com.example.serviceoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * create by Freedom on 2020/8/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OssApplicationTest {


    @Test
    public void me() throws FileNotFoundException {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4G5gkWAUPZq591rJmpzg";
        String accessKeySecret = "527BL0bxHy37B4KO34T37QvBi9Fac4";
        String bucketName ="freedom98";

// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "a.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        String content = "D:\\Users\\Pictures\\aaa.jpg";
        ossClient.putObject(bucketName, objectName, new FileInputStream(content));

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
