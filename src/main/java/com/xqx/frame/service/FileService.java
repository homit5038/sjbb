package com.xqx.frame.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;



/** 
 *  @Description 文件扫描上传service
 */
public interface FileService {

	/**
	 * 文件上传
	 * 
	 * @param orderNumber
	 * @param fileType
	 * @param multipartFiles
	 * @return
	 * @throws IOException
	 */
	public boolean webUpload(List<MultipartFile> multipartFiles) throws IOException;
	
	
	
	public String upphote( MultipartFile PhotoDir) throws IOException;
	
	
	/**
	 * 删除文件
	 * 
	 * @param id
	 * @return
	 */
	public String deleteFile(String String);
}
