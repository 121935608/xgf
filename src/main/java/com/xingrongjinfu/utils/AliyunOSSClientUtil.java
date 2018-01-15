package com.xingrongjinfu.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

public class AliyunOSSClientUtil{
	//阿里云API的内或外网域名
	public static String ENDPOINT="oss-cn-shanghai.aliyuncs.com";
   //阿里云API的密钥Access Key ID  
   public static String ACCESS_KEY_ID="LTAITGQYVDwzkpAJ";
   //阿里云API的密钥Access Key Secret  
   public static String ACCESS_KEY_SECRET="BriB4BByZhskAlQkuwo0IgGkuxoxlj";
   //阿里云API的bucket名称  
   public static String BACKET_NAME="xrjf";
   //阿里云API的文件夹名称 
   public static String FOLDER="xgf/platform/";
   //阿里云API的文件夹名称 商品图片
   public static String FOLDER2="xgf/imgFile/";

   
   /**
    * 获取阿里云OSS客户端对象
    * @return
    */
   public static OSSClient getOSSClient(){
	   return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
	   }
   
   /**
    * 创建存储空间
    * @param ossClient  OSS连接
    * @param bucketName  存储空间
    * @return
    */
   public static String createBucketName(OSSClient ossClient,String bucketName){
	   final String bucketNames=bucketName;
	   if(!ossClient.doesBucketExist(bucketName)){
		   //创建存储空间
		   Bucket bucket=ossClient.createBucket(bucketName);
		   return bucket.getName();
	   }
	  return bucketNames;
   }
   
   /**
    * 
    * @param ossClient
    * @param bucketName
    */
   public static void deleteBucket(OSSClient ossClient,String bucketName){
	   ossClient.deleteBucket(bucketName);
   }
   
   /**
    * 创建模拟文件夹
    * @param ossClient
    * @param bucketName
    * @param folder
    * @return
    */
   public static String createFolder(OSSClient ossClient,String bucketName,String folder){
	 //文件夹名   
	  final String keySuffixWithSlash=folder;
	  
	//判断文件夹是否存在，不存在则创建  
	  if(!ossClient.doesObjectExist(bucketName,keySuffixWithSlash)){
		  //创建文件夹  
            ossClient.putObject(bucketName,keySuffixWithSlash,new ByteArrayInputStream(new byte[0]));
             //得到文件夹名  
           OSSObject object = ossClient.getObject(bucketName,keySuffixWithSlash);
            String fileDir=object.getKey();
              return fileDir;
          }
         return keySuffixWithSlash;
   }
   
   /**
    * 根据key删除OSS服务器上的文件
    * @param ossClient
    * @param bucketName
    * @param folder
    * @param key
    */
   public static void deleteFile(OSSClient ossClient,String bucketName,String folder, String key){
	   ossClient.deleteObject(bucketName,folder+key);
   }
   
   /**
    * 上传图片至OSS
    * @param ossClient
    * @param file
    * @param bucketName
    * @param folder
    * @return
    */
   public static String uploadObject2OSS(OSSClient ossClient,MultipartFile file,String bucketName,String folder){
	   String resultStr=null;
	   try {
		//InputStream is = new FileInputStream(file);
		String fileName=file.getOriginalFilename();
		Long fileSize = file.getSize();
		ObjectMetadata metadata= new ObjectMetadata();
		metadata.setContentLength(fileSize);
		metadata.setCacheControl("no-cache");
		metadata.setHeader("Pragma", "no-cache");
		metadata.setContentEncoding("utf-8");
		metadata.setContentType(getContentType(fileName));
		metadata.setContentDisposition("filename/filesize="+fileName+"/"+fileSize+"Byte.");
	    //上传文件
		PutObjectResult putResult= ossClient.putObject(bucketName, folder+fileName, file.getInputStream(),metadata);
		resultStr=putResult.getETag();
	   } catch (IOException e) {
		e.printStackTrace();
	}
	   return resultStr;
	   
   }
   
   public static String getContentType(String fileName){
	   String fileExtension=fileName.substring(fileName.lastIndexOf("."));
	   if(".bmp".equalsIgnoreCase(fileExtension)){
		   return "image/bmp";
	   }
	   if(".gif".equalsIgnoreCase(fileExtension)){
		   return "image/gif";
	   }
	   if(".jpeg".equalsIgnoreCase(fileExtension)||".jpg".equalsIgnoreCase(fileExtension)||".png".equalsIgnoreCase(fileExtension)){
		   return "image/jpeg";
	   }
	   if (".txt".equalsIgnoreCase(fileExtension)){
		   return "txt";
	   }
	return fileName;
   }
   
   
   public static String  uploadImg(MultipartFile headPic) {
	   OSSClient ossClient=getOSSClient();
	   String md5key=uploadObject2OSS(ossClient, headPic, BACKET_NAME, FOLDER);
	   //ossClient.shutdown();
	return md5key;
   }

	public static String  uploadImgs(MultipartFile headPic) {
		OSSClient ossClient=getOSSClient();
		String md5key=uploadObject2OSS(ossClient, headPic, BACKET_NAME, FOLDER2);
		//ossClient.shutdown();
		return md5key;
	}
}
