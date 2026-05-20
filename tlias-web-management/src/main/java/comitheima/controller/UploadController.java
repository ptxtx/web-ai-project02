package comitheima.controller;

import comitheima.Utils.AliyunOSSOperator;
import comitheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {//与前端的参数名要一致哦！！
//        log.info("接收参数: {},{},{}",name,age,file);
//        String newFileName= UUID.randomUUID().toString();
//
//        String extension= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//获取源文件后缀名
//        //x现在要保存文件-本地存储
//        file.transferTo(new File("/Users/ptx/Desktop/"+newFileName+ extension));
//        return Result.success();
//    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}",file.getOriginalFilename());
        //将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传完成,返回访问路径: {}",url);
        return Result.success(url);
    }
}
