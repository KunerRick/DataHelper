package com.ngis.util;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class PathUtil {
	public static String getPath(Class<?>  clazz) { 
        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();  
        String filePath = null;  
        try {  
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// ת��Ϊutf-8����  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        if (filePath.endsWith(".jar")) {// ��ִ��jar�����еĽ�������".jar"  
            // ��ȡ·���е�jar����  
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);  
        }
          
        File file = new File(filePath);  
          
        // /If this abstract pathname is already absolute, then the pathname  
        // string is simply returned as if by the getPath method. If this  
        // abstract pathname is the empty abstract pathname then the pathname  
        // string of the current user directory, which is named by the system  
        // property user.dir, is returned.  
        filePath = file.getAbsolutePath();//�õ�windows�µ���ȷ·��  
        return filePath;
//        return "C:\\Users\\Administrator\\Desktop\\ModelWrapper_QTUI\\wrapper\\model";
    }
}
