package com.usedBooks.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;

/**
 * @Description: 上传服务额工具
 * @ClassName：RemoteUploadServiceUtil
 * @Author：GuoGuanzhi
 * @CreateTime：2018年10月11日上午10:27:30
 * @mail Hunter909683502@126.com
 */
@Service
public class RemoteUploadServiceUtil {

	private Logger logger = LoggerFactory.getLogger(RemoteUploadServiceUtil.class);

	/**
	 * @Description: 返回json格式的处理方法
	 * @MethodName: remoteUploadGetJson
	 * @param request
	 * @param paramName
	 * @param remoteUrl
	 * @return
	 * @return String
	 * @throws @CreateTime：2018年10月11日上午10:31:29
	 * @Author：GuoGuanzhi
	 */
	public String remoteUploadGetJson(HttpServletRequest request, String paramName, String remoteUrl, String dirName) {

		return this.remoteUpload(request, paramName, remoteUrl,dirName);
	}

	/**
	 * @Description: 返回一个储存上传图片路径的list
	 * @MethodName: remoteUploadGetList
	 * @param request
	 * @param paramName
	 * @param remoteUrl
	 * @return List<String> 失败的话，返回值null
	 * @throws @CreateTime：2018年10月11日上午10:35:28
	 * @Author：GuoGuanzhi
	 */
	public List<String> remoteUploadGetList(HttpServletRequest request, String paramName, String remoteUrl, String dirName) {
		String res = this.remoteUpload(request, paramName, remoteUrl,dirName);

		logger.info("上传图片响应结果：{}",res);

		// 解析字符串
		List<String> rd = JSON.parseArray(res, String.class);

		logger.info("转换的结果是，{}", rd);
		return rd;
	}

	private String remoteUpload(HttpServletRequest request, String paramName, String remoteUrl, String dirName) {
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

		// 真正处理上传服务的请求

		return doRemoteUpload(realFileList, remoteUrl,dirName);

	}

	/**
	 * @Description: 真正处理上传的代码
	 * @MethodName: doRemoteUpload
	 * @param realFileList 真正上传了多少文件
	 * @param remoteUrl
	 * @return String json格式的字符串
	 * @throws @CreateTime：2018年10月11日上午10:07:32
	 * @Author：GuoGuanzhi
	 */
	private String doRemoteUpload(List<MultipartFile> realFileList, String remoteUrl, String dirName) {

		// 构造一个MultipartFileBuilder
		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建一个HttpClient对象

		HttpPost httpPost = new HttpPost(remoteUrl); // 设置要进行访问的请求地址

		// 创建一个多实体构造器
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setCharset(java.nio.charset.Charset.forName("utf-8"));// 设置请求的编码格式
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置浏览器兼容模式

		// 开启解析上传文件，并将上传文件绑定到post请求体中
		try {
			resolverPart(realFileList, multipartEntityBuilder,dirName);
			HttpEntity entity = multipartEntityBuilder.build();
			httpPost.setEntity(entity); // 将请求的实体信息进行发送
			HttpResponse response = httpClient.execute(httpPost); // 执行请求的发送

			return EntityUtils.toString(response.getEntity(), java.nio.charset.Charset.forName("UTF-8"));
		} catch (IOException e) {
			logger.error("绑定出错 {}", e);
			return null;
		}
	}

	/**
	 * @Description: 解析文件块，并添加到post请求体中
	 * @MethodName: resolverPart
	 * @param realFileList
	 * @param multipartEntityBuilder
	 * @throws IOException
	 * @return void
	 * @throws @CreateTime：2018年10月11日上午10:23:17
	 * @Author：GuoGuanzhi
	 */
	private void resolverPart(List<MultipartFile> realFileList, MultipartEntityBuilder multipartEntityBuilder,String dirName)
			throws IOException {

		if (realFileList.size() == 0 || null == realFileList || null == multipartEntityBuilder) {
			logger.error("解析错误，请求参数非法， realFileList={}, multipartEntityBuilder={}", realFileList,
					multipartEntityBuilder);
			throw new RuntimeException();
		}

		Iterator<MultipartFile> iterator = realFileList.iterator();
		while (iterator.hasNext()) {
			MultipartFile multipartFile = iterator.next();
			// File file = new File("E:/upload/"+multipartFile.getOriginalFilename());
			// multipartFile.transferTo(file);
			byte[] buf = multipartFile.getBytes();
			logger.info("这里是没有问题的。。,,,,{}", buf.length);
			multipartEntityBuilder.addBinaryBody(multipartFile.getOriginalFilename(), buf, ContentType.DEFAULT_BINARY,
					dirName);
		}

		logger.info("解析完成！");
	}

}
