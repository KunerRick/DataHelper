package com.ngis.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Date;

public class FileUtil {
    /**
     * 单个文件拷贝
     * @param oldFile
     * @param newFile
     * @return
     * @throws Exception
     */
	public static long copyFile(String oldFileName,String newFilName) throws Exception{
		
        long time=new Date().getTime();
        int length=2097152;
        File oldFile=new File(oldFileName);
        if(!oldFile.exists()) {
        	System.out.println(oldFileName+"  does not exist.");
        	return 0;
        }
        File newFile=new File(newFilName);
        if(!newFile.exists()){
        	if(!newFile.getParentFile().exists()){
        		newFile.getParentFile().mkdirs();
        	}
        	newFile.createNewFile();
        }
        FileInputStream in=new FileInputStream(oldFile);
        FileOutputStream out=new FileOutputStream(newFile);
        FileChannel inC=in.getChannel();
        FileChannel outC=out.getChannel();
        int i=0;
        while(true){
            if(inC.position()==inC.size()){
                inC.close();
                outC.close();
                return new Date().getTime()-time;
            }
            if((inC.size()-inC.position())<20971520)
                length=(int)(inC.size()-inC.position());
            else
                length=20971520;
            inC.transferTo(inC.position(),length,outC);
            inC.position(inC.position()+length);
            i++;
        }
    }
	
	/**
	 * 文件夹拷贝
	 * @param oldPath
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	public static boolean copyFiles(String oldPath,String newPath)throws Exception{
	      
		  File file1=new File(oldPath); 
		  if(!file1.exists()) return false;
	        File[] fs=file1.listFiles();  
	        File file2=new File(newPath);  
	        if(!file2.exists()){  
	            file2.mkdirs();  
	        }  
	        for (File f : fs) {  
	            if(f.isFile()){  
	            	copyFile(f.getPath(),newPath+"\\"+f.getName()); //调用文件拷贝的方法  
	            }else if(f.isDirectory()){  
	            	if(!copyFiles(f.getPath(),newPath+"\\"+f.getName())) return false;  
	            }  
	        }
	        return true;
	}
	/**
	 * 删除文件夹，删除本身
	 * @param filePathForDel
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteFiles(String filePathForDel)throws Exception{
	      
		  File file1=new File(filePathForDel); 
		  if(!file1.exists()) return true;
	        File[] fs=file1.listFiles();  
	        for (File f : fs) {  
	             
	            	if(!f.delete()) {
	            		System.out.println(" delete "+f.getName()+" failed.");
	            		return false; 
	            	}       
	           }
	        file1.delete();
	        return true;
	}
	/**
	 * 判断是否删除根目录
	 * @param filePathForDel
	 * @param keepItself //true:keep,false:delete.
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteFiles(String filePathForDel,boolean keepItself)throws Exception{
	      
		  File file1=new File(filePathForDel); 
		  if(!file1.exists()) return true;
	        File[] fs=file1.listFiles();  
	        for (File f : fs) {  
	             
	            	if(!f.delete()) {
	            		System.out.println(" delete "+f.getName()+" failed.");
	            		return false; 
	            	}       
	           }
	        if(!keepItself){
	        	file1.delete();        
	        }
	        return true;
	}
	


}
