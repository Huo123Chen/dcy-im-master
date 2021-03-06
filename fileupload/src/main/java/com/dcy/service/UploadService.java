package com.dcy.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	/**
	 * 视频文件上传
	 * @param request
	 * @return
	 */
	public Map<String, Object> uploadVideo(MultipartFile file,HttpServletRequest request) throws Exception;
	
	/**
	 * 图片文件上传
	 * @param request
	 * @return
	 */
	public Map<String, Object> uploadImage(MultipartFile file,HttpServletRequest request) throws Exception;
	
	/**
	 * 项目目录下的图片文件上传
	 * @param request
	 * @return
	 */
	public Map<String, Object> getImageUrl(HttpServletRequest request) throws Exception;
}
