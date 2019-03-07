package com.usedBooks.frontStage.test.controller;


import com.usedBooks.result.Result;
import com.usedBooks.util.UploadServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @Autowired
    UploadServiceUtil uploadServiceUtil;

    /**
     * 测试上传文件
     * @param request
     * @param dirName       自定义目录路径，格式：xxxxx/xxxx/xxx
     * @param fileKey       文件类型的名字
     * @return              返回的文件url
     */
    @PostMapping("/testUpload")
    public Result testUpload(HttpServletRequest request, String dirName,String fileKey){
        return Result.success(uploadServiceUtil.Upload(request,fileKey,dirName));
    }
}
