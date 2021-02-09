package com.xqx.frame.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.config.Config;
import com.xqx.frame.dao.TGradeDao;




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
		//File upDir = null;
		//	baseDir += uploadDir ;
		if (!multipartFile.isEmpty()) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
			String subfolder = sdf1.format(new Date());
		
			File upDir = new File(baseDir);
			if (!upDir.exists()) {
				upDir.mkdirs();
			}
			File fileDir = new File(baseDir + "/" + subfolder);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

					String fileName = multipartFile.getOriginalFilename();
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
					String uuid = sdf2.format(new Date());
					String Type = fileName.substring(fileName.lastIndexOf("."));
					String picName = "";
					File file = null;
					BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
					if (bi != null) {
						picName = uuid + 0  + Type;
						file = new File(fileDir.getPath(), picName);
					} else {
						picName = fileName;
						file = new File(fileDir.getPath(), picName);
					}
					
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),	file);	FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),	file);
					return file.getPath();
			
			}else {
				return "0";
			}
	
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
