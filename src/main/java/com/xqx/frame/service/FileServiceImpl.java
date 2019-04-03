package com.xqx.frame.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.config.Config;
import com.xqx.frame.dao.TGradeDao;
import com.xqx.frame.model.TGrade;



/** 
 * @Description 文件扫描上传 serviceImpl
 *
 */

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	TGradeDao gradeDao;

	/**
	 * 路径  本地路径\登录名\订单号\订单上传文件类型\文件名
	 * @throws IOException 
	 * */
	@Override
	@Transactional
	public boolean webUpload(List<MultipartFile> multipartFiles) throws IOException{
		boolean result = false;
		String uploadDir = Config.getString("uploadFileDir");
			
		for (int i = 0; i < multipartFiles.size(); i++) {
			MultipartFile multipartFile = multipartFiles.get(i);
			if (!multipartFile.isEmpty()) {
				this.saveFile(multipartFile, uploadDir);
				result = true;
			}
		}
		return result;
	}

	
	/**
	 * 保存文件
	 * 
	 * @param multipartFile
	 * @param sj
	 * @param yw
	 * @throws IOException
	 */
	public String saveFile(MultipartFile multipartFile, String uploadDir) throws IOException{
		String baseDir = Config.getString("uploadFileDir");
		File upDir = null;
	//	baseDir += uploadDir ;
		upDir = new File(uploadDir);
		if (!upDir.exists()) {
			upDir.mkdirs();
		}
		File file = new File(upDir.getPath(), multipartFile.getOriginalFilename());
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),
					file);
		return file.getPath();
	}


	@Override
	@Transactional
	public String deleteFile(String file) {
		String baseDir = Config.getString("uploadFileDir");
		
	    if(file != null){
	    	new File(baseDir + file).delete();
	    }

	 
		return "success";
	}


	@Override
	@Transactional
	public String upphote(MultipartFile PhotoDir) throws IOException{
		String result = "";
		String uploadDir = "\\" + Config.getString("uploadFileDir") +"\\" + "0"+"\\" ;
		
		
		if (!PhotoDir.isEmpty()) {
			//TGrade grade= new TGrade();
			try {
				//grade.setPhotoDir(PhotoDir.getBytes());
				//gradeDao.save(grade);
				result =this.saveFile(PhotoDir, uploadDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
		return result;
	}
}
