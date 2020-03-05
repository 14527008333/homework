package com.zhk.zhkopencartstore.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

public class FileUploadUtil {
	
	// 用于图片上传				session				上传的文件				服务器上传路径
	public static String  load(HttpSession session,MultipartFile file,String toPath) {
		// 获取原始文件名称  主要为了找到后缀名.png  .jpg
		String originalFilename = file.getOriginalFilename();
		//  找到 最后一个.  的位置  下标
		int indexOf = originalFilename.lastIndexOf(".");
		// 截取字符串
		String houzhui = originalFilename.substring(indexOf);
		//UUID 随机性
		String string = UUID.randomUUID().toString();
		// 服务器上传路径
		/*String realPath = session.getServletContext().getRealPath("/"+toPath);*/
		
		String path=toPath+File.separator+string+houzhui;
		
		// 上传文件
		File file2 = new File(path);
		try {
			file.transferTo(file2);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string+houzhui;
	}

	
	
	public static  void down(String fileName,String toPath,HttpServletResponse response,HttpServletRequest request) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data");
		// 设置下在的头文件  格式
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		// 获取文件的 绝对路径
		String realPath = request.getServletContext().getRealPath("/"+toPath+"/");
		realPath=realPath+File.separator+fileName;
		try {
			FileInputStream fis=new FileInputStream(realPath);
			OutputStream outputStream = response.getOutputStream();
			int read=0;
			while((read=fis.read())!=-1) {
				outputStream.write(read);
			}
			
			close(fis,outputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public  static void  close(InputStream is,OutputStream os) {
		try {
			if(is!=null) {
				is.close();
			}
			if(os!=null) {
				os.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
