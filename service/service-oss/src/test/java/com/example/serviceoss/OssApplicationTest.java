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

    String endpoint = "oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAI4G5gkWAUPZq591rJmpzg";
    String accessKeySecret = "527BL0bxHy37B4KO34T37QvBi9Fac4";
    String bucketName ="freedom98";

    @Test
    public void upload() throws FileNotFoundException {

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

    @Test
    public void delete(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        String s1 = "2020/09/02/4eabd6d1129f4981be57120702dca397file.png";
        ossClient.deleteObject(bucketName, s1);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void strSub(){
        String s = "https://freedom98.oss-cn-beijing.aliyuncs.com/2020/09/09/311cf2f7ef6645d5a1e3dbb0bb001d81default.jpg";
        String substring = s.substring(s.indexOf(endpoint));
        System.out.println("substring = " + substring);
    }
}
