package com.ngis.util;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class PathUtil {
	public static String getPath(Class<?>  clazz) { 
        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();  
        String filePath = null;  
        try {  
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"  
            // 截取路径中的jar包名  
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);  
        }
          
        File file = new File(filePath);  
          
        // /If this abstract pathname is already absolute, then the pathname  
        // string is simply returned as if by the getPath method. If this  
        // abstract pathname is the empty abstract pathname then the pathname  
        // string of the current user directory, which is named by the system  
        // property user.dir, is returned.  
        filePath = file.getAbsolutePath();//得到windows下的正确路径  
        return filePath;
//        return "C:\\Users\\Administrator\\Desktop\\ModelWrapper_QTUI\\wrapper\\model";
    }
}
