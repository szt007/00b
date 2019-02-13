package com.yellowcong.baidu.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;


public class FileUtils {
	//文件夹信息
	private static List<String> dirNames= null;
	//文件信息
	private static List<String> fileNames = null;
	
	//文件大小单位 ，我们可以通过添加SizeStr 来添加单位
	private static String [] sizeStr = {"B","KB","MB","GB","PB"}; 
	private static int count =0;
	private static long fileSize = 0;

	private FileUtils(){}
	
	/**
	 * 获取类路径下文件的路径
	 * @param fileName
	 * @return
	 */
	public static String getClassPathFilePath(String fileName){
		String path = FileUtils.class.getClassLoader().getResource(fileName).getPath().toString();
		if(path.contains("%20")){
			path = path.replace("%20", " ");
		}
		return path;
	}
	
	/**
	 * 获取文件对象
	 * @param fileName
	 * @return
	 */
	public static File getClassPathFile(String fileName){
		return new File(FileUtils.getClassPathFilePath(fileName));
	}
	
	/*在类路径创建我们的 文件夹*/
	/**
	 * 创建类路径下面的文件夹
	 * @param directory
	 */
	public static String mkClassPathDir(String directory){
		String path = FileUtils.class.getClassLoader().getResource("").getPath();
		path += directory;
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}
		return path;
	}
	
	/**
	 * 获取类文件的输入流
	 * @param fileName
	 * @return
	 */
	public static InputStream getClassPathFileInputStream(String fileName){
		return FileUtils.class.getResourceAsStream(fileName);
	}
	
	
	/**
	 * 将File 文件转化为 字符串
	 * @param filePath
	 * @return
	 */
	public static String copyFileToString(String filePath){
		return FileUtils.getFileContent(filePath);
	}
	
	/**
	 * 获取一个文本文件里面的内容
	 * @param filePath
	 * @return
	 */
	public static String getFileContent(String filePath){
		FileReader in  = null;
		BufferedReader reader = null;
		try {
			in  = new FileReader(new File(filePath));
			reader = new BufferedReader(in);
			String line = null;
			StringBuffer str = new StringBuffer();
			while((line = reader.readLine())!= null){
				str.append(line);
				str.append("\r\n");
			}
			return str.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
				if(reader != null ){
					reader.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 通过 路径来创建文件
	 * @param path
	 */
	public static void createDirectory(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	/**
	 * 通过文件夹  获取文件夹 当前目录 和下面目录所有的文件 
	 * @param path
	 * @param isNowPath 目录级别  当前目录 数据 
	 * @return
	 */
	//获取所有文件
	public static List<String> getChildFiles(String path,boolean isNowPath){
		if(!new File(path).exists()){
			throw new RuntimeException(path+"，文件夹不存在");
		}
		if(!new File(path).isDirectory()){
			throw new RuntimeException(path+"，不是文件夹");
		}
		fileNames = new ArrayList<String>();
		listAllFile(path,isNowPath);
		return fileNames;
	}
	
	/**
	 * 通过文件夹  获取文件夹 当前目录 和下面目录所有的文件 
	 * @param path
	 * @return
	 */
	public static List<String> getChildFiles(String path){
		return getChildFiles(path, false);
	}
	
	
	
	/**
	 * 通过文件夹 获取
	 * 所有的文件夹
	 * @param path 目录名称
	 * @param isNowPath 是否是当前目录
	 * @return
	 */
	public static List<String> getChildDirs(String path,boolean isNowPath){
		if(!new File(path).exists()){
			throw new RuntimeException(path+"，文件夹不存在");
		}
		if(!new File(path).isDirectory()){
			throw new RuntimeException(path+"，不是文件夹");
		}
		dirNames = new ArrayList<String>();
		listAllDir(path,isNowPath);
		return dirNames;
	}
	/**
	 * 通过文件夹 获取 我们的子 
	 * 所有的文件夹  
	 * @param path 目录名称
	 * @return
	 */
	public static List<String> getChildDirs(String path){
		return getChildDirs(path, false);
	}
	/**
	 * 获取某个文件夹下面的文件
	 * 其中文件夹包含了第一个文件夹就是自己 重复调用
	 * 
	 * @param path 文件夹路径名称
	 */
	private static void listAllFile(String path,boolean isNowPath){
		File file = new File(path);
		if(file.isDirectory()){
				for(File f:file.listFiles()){
					if(isNowPath){
						if(f.isFile()){
							fileNames.add(f.getPath());
						}
					}else{
						listAllFile(f.getPath(),isNowPath);

					}
				}
		}else{
			fileNames.add(path);
		}
	}
	/**
	 * 获取某个文件夹下面的文件
	 * 其中文件夹包含了第一个文件夹就是自己 重复调用
	 * 
	 * @param path 文件夹路径名称
	 * @param isNowPath 是否是当前目录 ，包含子目录
	 */
	private static void listAllDir(String path,boolean isNowPath){
		File file = new File(path);
		if(file.isDirectory()){
			dirNames.add(path);
			for(File f:file.listFiles()){
				if(isNowPath){
					dirNames.add(f.getPath());
				}else{
					listAllDir(f.getPath(),isNowPath);
				}
				
			}
		}
	}
	
	/**
	 * 获取所有的文件，包括 文件夹的信息
	 * @param path
	 * @return
	 */
	public static List<String> listAllFiles(String path){
		 List<String> strs = FileUtils.getChildDirs(path);
		 strs.addAll(FileUtils.getChildFiles(path));
		 FileUtils.getChildFiles(path);
		 return strs;
	}
	
	/**
	 * 将InputStream  转化为输出数据
	 * @param in 输入流
	 * @param file 输出的文件对象
	 */
	public static void copyInputStreamToFile(InputStream in ,File file){
		OutputStream out= null;
		try {
			out = new FileOutputStream(file);
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff)) > 0) {
				out.write(buff, 0, len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}finally{
				try {
					if(out != null){
						out.close();
					}
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
		
	}
	

	/**
	 * 将字符数据转化为我们的文件
	 * @param data 字节数组
	 * @param file 输出文件
	 */
	public static void copyByte2File(byte [] data,File file){
		if ((data == null) || (data.length <= 0))
			return;
		
		OutputStream out = null;
		InputStream in = null;
		try {
			out = new FileOutputStream(file);
			in = new ByteInputStream(data,data.length );
			byte [] buff = new byte[1024];
			int len =  0;
			while((len = in.read(buff))>0){
				out.write(buff, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}finally{
				try {
					if(out != null){
						out.close();
					}
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
	}
	
	/**
	 * 将InputStream 转化为字节码文件
	 * @return 
	 */
	public static byte[] copyFileToByteArray(File file){
		InputStream  in = null;
		try {
			in = new FileInputStream(file);
			//获取字节码的数量，将字节码装
			int len = in.available();
			byte [] result = new byte[len];
			in.read(result);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
		
	}
	/**
	 * 将InputStream 转化为字节码文件
	 * @return 
	 */
	public static byte[] copyInputStreamToByteArray(InputStream in){
		try {
			//获取字节码的数量，将字节码装
			int len = in.available();
			byte [] result = new byte[len];
			in.read(result);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	/**
	 * xx "B","KB","MB","GB","PB"
	 * 
	 * 计算文件大小
	 * @param size
	 */
	private static void getFileSize(long size){
		long result  = size/1024;
		if(result >=1 && count <(sizeStr.length-2)){
			fileSize = size/1024;
			count = count +1;
			getFileSize(fileSize);
		}else{
			fileSize = size;
		}
	}
	
	/**
	 * 
	 * xx "B","KB","MB","GB","PB"
	 * 
	 * 计算文件大小
	 * 我们用的时候，对于 静态的数据，需要清空 或者重新初始化，解决多次使用，导致数据堆积问题
	 * @param fileSize
	 * @return
	 */
	public static String getFileSizeStr(long filesize){
		if(filesize <0){
			throw new RuntimeException("文件大小有问题");
		}
		//多次使用的时候，这个 就会用问题了，一定要清空
		count = 0;
		fileSize= 0;
		getFileSize(filesize);
		return fileSize+sizeStr[count];
	}
	
	/**
	 * 将InputStream转化为Str
	 * @param in 输入流
	 * @return  数据
	 */
	public static String copyInput2Str(InputStream in){
		String str = null;
		try {
			
			//写数据
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			    
			StringBuffer sb = new StringBuffer();
			while((line = reader.readLine())!= null){
				 sb.append(line);
			}
			str = sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return str;
	}
}
