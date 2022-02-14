package com.dcy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dcy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
	@Autowired
	private UploadService uploadService;

	/**
	 * 视频文件上传
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value ="/uploadVideo",method=RequestMethod.POST)
	public Map<String, Object> uploadVideo(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception{
		return uploadService.uploadVideo(file, request);
	}

	/**
	 * 图片头像上传
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/uploadHeadImage",method=RequestMethod.POST)
	public Map<String, Object> uploadHeadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
		if (null == file){
			return null;
		}
		return uploadService.uploadImage(file, request);
	}
	
	/**
	 * 图片文件上传
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value ="/uploadImage",method=RequestMethod.POST)
	public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
		if (null == file){
			return null;
		}
		return uploadService.uploadImage(file, request);
	}
        	
	/**
	 * 项目目录下的图片文件上传
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value ="/getImageUrl",method=RequestMethod.GET)
	public Map<String, Object> getImageUrl(HttpServletRequest request) throws Exception{
		return uploadService.getImageUrl(request);
	}
}
