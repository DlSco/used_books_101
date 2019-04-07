package com.usedBooks.util;



import com.usedBooks.exception.GlobalException;
import com.usedBooks.result.CodeMsg;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class UploadServiceUtil {

    private Logger logger = LoggerFactory.getLogger(UploadServiceUtil.class);

    @Value("${FTP.ADDRESS}")
    private String host;
    // 端口
    @Value("${FTP.PORT}")
    private int port;
    // ftp用户名
    @Value("${FTP.USERNAME}")
    private String userName;
    // ftp用户密码
    @Value("${FTP.PASSWORD}")
    private String passWord;
    // 文件在服务器端保存的主目录
    @Value("${FTP.BASEPATH}")
    private String basePath;
    // 访问图片时的基础url
    @Value("${IMAGE.BASE.URL}")
    private String baseUrl;

    /**
     *
     * @param request
     * @param paramName        上传的文件传参的name
     * @param dirName          自定义目录路径，格式：xxxxx/xxxx/xxx
     * @return
     */
    public List<String> Upload(HttpServletRequest request, String paramName,String dirName) {


        // 参数校验
        if (request == null) {
            logger.error("请求参数为null");
            return null;
        }

        // 判断是多块请求的类型
        if (!(request instanceof MultipartHttpServletRequest)) {
            logger.error("请求参数不是上传类型的请求，不能处理。");
            return null;
        }

        // 强制转换成上传类型的请求处理
        // 获取上传的内容
        List<MultipartFile> multipartFileList = ((MultipartHttpServletRequest) request).getFiles(paramName);
        // 真正有内容的表单数据
        List<MultipartFile> realFileList = new ArrayList<>();
        // 处理名字为null的文件
        for (MultipartFile multipartFile : multipartFileList) {
            if (multipartFile.getOriginalFilename().length() != 0 && multipartFile.getOriginalFilename() != null) {
                realFileList.add(multipartFile);
            }
        }
        return doRemoteUpload(realFileList,dirName);

    }


    /**
     *
     * @param realFileList         文件列表
     * @param dirName              自定义的目录路径   格式：xxxx/xxxx/xxx
     * @return
     */
    public List<String> doRemoteUpload(List<MultipartFile> realFileList,String dirName){
        Iterator<MultipartFile> iterator = realFileList.iterator();
        List<String> picUrlList = new LinkedList<>();
        while (iterator.hasNext()) {
            MultipartFile uploadFile = iterator.next();

            // 真正处理上传服务的请求
            try {
                //1、给上传的图片生成新的文件名
                //1.1获取原始文件名
                String oldName = uploadFile.getOriginalFilename();
                logger.info("oldName:"+oldName);
                //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
                String newName = IDUtil.genImageName();
                newName = newName + oldName.substring(oldName.lastIndexOf("."));
                logger.info("newName:"+newName);
                //1.3生成文件在服务器端存储的子目录
                String filePath = null;
                if(dirName==null){
                    filePath = new DateTime().toString("/yyyy/MM/dd");
                }else{
                    filePath = "/"+dirName+new DateTime().toString("/yyyy/MM/dd");
                }

                logger.info("filePath:"+filePath);
                //3、把图片上传到图片服务器
                //3.1获取上传的io流
                InputStream input = uploadFile.getInputStream();

                //3.2调用FtpUtil工具类进行上传
                boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, filePath, newName, input);
                logger.info("result"+result);
                if (result) {
                    picUrlList.add(baseUrl+filePath+"/"+newName);
                } else {
                    picUrlList.removeAll(picUrlList);
                    FtpUtil.deleteFile(host, port, userName,
                            passWord, basePath, filePath, newName);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new GlobalException(new CodeMsg(500800,"上传失败"));
            }
        }
        return picUrlList;
    }
}
