package com.xqx.frame.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqx.frame.config.Config;

import eu.medsea.mimeutil.MimeUtil;

public class PubUtil {
	/**
	 * 缩略图获取<默认生成gif格式，配置常量DEFAULTIMGTYPE 为jpg 生成jpg格式缩略图> 功能在服务器不实用，有待改善
	 * 
	 * @param Path
	 * @param inputF
	 * @param outputF
	 * @return
	 */
	public static boolean convertFLV2Img(String ffmpegPath, String Path,
			String inputF, String outputF) {
		boolean covered = false;
		try {
			String a = " " + ffmpegPath + "\\myConvertxjpg.bat";
			String b = " " + Path + "\\" + inputF;
			String c = " " + Path + "\\" + outputF + ".jpg";
			String x = " " + ffmpegPath + "\\ffmpeg.exe";
			String cmd = "cmd /c start " + a + x + b + c;
			Runtime.getRuntime().exec(cmd);
			covered = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return covered;
	}
    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }
	/**
	 * 获得视频缩略图，获取成功返回true，获取失败返回false
	 * 
	 * @param ffmpegPath
	 *            是ffmpeg.exe存放的路径
	 * @param path
	 *            是视频文件的存放路径
	 * @param outImagePath
	 *            输出缩略图的保存路径
	 * @return
	 */
	public static boolean getVideoImage(String ffmpegPath, String path,
			String inputF, String outImagePath) {
		File file = new File(path + "\\" + inputF);
		if (!file.exists()) {// 判断视频文件是否存在
			System.err.println("路径[" + path + "]对应的视频文件不存在!");
			return false;
		}
		// 设置参数
		List<String> commands = new java.util.ArrayList<String>();
		// commands.add("start");
		commands.add(ffmpegPath + "\\ffmpeg.exe");// 这里设置ffmpeg.exe存放的路径
		commands.add("-i");
		commands.add(path + "\\" + inputF);// 这里是设置要截取缩略图的视频的路径
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss");
		commands.add("2");// 这里设置的是要截取视频开始播放多少秒后的图，可以自己设置时间
		commands.add("-t");
		commands.add("0.001");
		commands.add("-s");
		commands.add("320x240");// 这里设置输出图片的大小
		commands.add(path + "\\" + outImagePath + ".jpg");// 这里设置输出的截图的保存路径
		try {
			// 截取缩略图并保存
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.redirectErrorStream(true);
			System.out.println("视频截图开始...");
			Process process = builder.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[1024];
			System.out.print("正在进行截图，请稍候");
			while (in.read(re) != -1) {
				System.out.print(".");
			}
			System.out.println("");
			in.close();
			System.out.println("视频截图完成...");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 校验文件类型是否是被允许的
	 * 
	 * @param multipartFiles
	 * @return
	 */
	public static Map<String, Object> uploadFileType(
			List<MultipartFile> multipartFiles) {
		// 文件类型
		String uploadFileType = Config.getString("uploadFileType");
		String[] fileTypes = uploadFileType.split("_");
		List<String> ALLOW_TYPES = Arrays.asList(fileTypes);
		Map<String, Object> map = new HashMap<String, Object>();
		String isSuccess = "ok";
		String message = "ok";
		for (MultipartFile multiPartFile : multipartFiles) {
			if (!multiPartFile.isEmpty()) {
				String fileName = multiPartFile.getOriginalFilename();
				String postfix = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!ALLOW_TYPES.contains(postfix)) {
					isSuccess = "err";
					message = "文件类型不合法,只能上传jpg png pdf doc文件";
					break;
				}
			}
		}
		map.put("isSuccess", isSuccess);
		map.put("message", message);
		return map;
	}

	/**
	 * 校验文件类型是否是被允许的
	 * 
	 * @param multiPartFile
	 * @return
	 */
	public static Map<String, Object> uploadFileType(MultipartFile multiPartFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		String uploadFileType = Config.getString("uploadFileType");
		String[] fileTypes = uploadFileType.split("_");
		List<String> ALLOW_TYPES = Arrays.asList(fileTypes);
		String isSuccess = "ok";
		String message = "ok";
		if (!multiPartFile.isEmpty()) {
			String fileName = multiPartFile.getOriginalFilename();
			String postfix = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!ALLOW_TYPES.contains(postfix)) {
				isSuccess = "err";
				message = "文件类型不合法,只能上传jpg png pdf doc文件";
			}
		}
		map.put("isSuccess", isSuccess);
		map.put("message", message);
		return map;
	}

	/**
	 * 判断文件类型 mime-util
	 * 
	 * @param multipartFiles
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> fileTypeByMimeUtil(
			List<MultipartFile> multipartFiles) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String isSuccess = "ok";
		String message = "ok";
		for (MultipartFile multiPartFile : multipartFiles) {
			if (!multiPartFile.isEmpty()) {
				String fileName = multiPartFile.getOriginalFilename();
				String type = fileName.substring(fileName.lastIndexOf(".") + 1)
						.toLowerCase();
				CommonsMultipartFile cf = (CommonsMultipartFile) multiPartFile;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				File file = fi.getStoreLocation();
				// long getFileSize = GetFileSize(file);
				MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
				Collection<?> mimeTypes = MimeUtil.getMimeTypes(file);
				String mediaType = MimeUtil.getMediaType(mimeTypes.toString());
				// String postfix = MimeUtil.getSubType(mimeTypes.toString());
				String uploadFileType = Config.getString("uploadFileType");
				String[] uploadFileTypes = uploadFileType.split("_");
				List<String> fileTypes = Arrays.asList(uploadFileTypes);
				if ("image".equals(mediaType)) {
					// String imageSize = Config.getString("imageSize");
					// if(getFileSize>Long.valueOf(imageSize)){
					// isSuccess = "err";
					// message = "图片太大，只能上传"+imageSize+"MB的文件";
					// break;
					// }
					String uploadImageType = Config
							.getString("uploadImageType");
					String[] uploadImageTypes = uploadImageType.split("_");
					List<String> imageTypes = Arrays.asList(uploadImageTypes);
					if (!imageTypes.contains(type)) {
						isSuccess = "err";
						message = "文件类型不合法,只能上传" + fileTypes.toString() + "文件";
						break;
					}
				} else if ("application".equals(mediaType)) {
					String uploadApplicationType = Config
							.getString("uploadApplicationType");
					String[] uploadApplicationTypes = uploadApplicationType
							.split("_");
					List<String> applicationTypes = Arrays
							.asList(uploadApplicationTypes);
					if (!applicationTypes.contains(type)) {
						isSuccess = "err";
						message = "文件类型不合法,只能上传" + fileTypes.toString() + "文件";
						break;
					}
				} else {
					if (!fileTypes.contains(type)) {
						isSuccess = "err";
						message = "文件类型不合法,只能上传" + fileTypes.toString() + "文件";
						break;
					}
				}
			}
		}
		map.put("isSuccess", isSuccess);
		map.put("message", message);
		return map;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param source
	 * @param rex
	 * @return
	 */
	public static String dateToString(Date source, String rex) {
		if (source == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(rex);
		return format.format(source);
	}
	
	public static boolean isNumerics(String str) { 
		String bigStr; 
		try {
			bigStr = new BigDecimal(str).toString(); 
			} catch (Exception e) {
				return false;//异常 说明包含非数字。 } return true; }
		}
		 return true;
	}
	
	
}
