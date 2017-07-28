package com.bo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @author 王博
 * @version 2017年7月28日　下午3:57:24
 */
public class FileUpload {
	@RequestMapping
	public void execute(@RequestParam(required = false) MultipartFile file, @RequestParam(value = "file_info_id", required = false) Integer fileInfoId, ModelMap model, HttpServletRequest request) throws Exception {

		if (file == null || file.isEmpty()) {
			return;
		}
		
		byte[] bytes = file.getBytes();
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request) {
		Map<Object, Object> modelMap = new HashMap<Object, Object>();
		if (ex instanceof MaxUploadSizeExceededException) {
			modelMap.put("errors", "文件大小不应超过 " + getFileKB(((MaxUploadSizeExceededException) ex).getMaxUploadSize()));
		} else {
			modelMap.put("errors", "上传发生未知错误: " + ex.getMessage());
		}
		return new ModelAndView("/common/file/upload", (Map) modelMap);

	}

	private String getFileKB(long byteFile) {
		if (byteFile == 0)
			return "0KB";
		long kb = 1024;
		return "" + byteFile / kb + "KB";
	}

	private String getFileMB(long byteFile) {
		if (byteFile == 0)
			return "0MB";
		long mb = 1024 * 1024;
		return "" + byteFile / mb + "MB";
	}
}
