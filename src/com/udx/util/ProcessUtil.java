package com.udx.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProcessUtil {
	public static boolean callCmd(String locationCmd) throws IOException {
		// 执行批处理文件

		String strcmd = "cmd /c start  " + locationCmd;
		Runtime rt = Runtime.getRuntime();
		Process ps = null;
		try {
			ps = rt.exec(strcmd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ps.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = ps.exitValue();
		if (i == 0) {
			System.out.println("Model starts runing.");

		} else {

			System.out.println("Model starts error.");
			return false;
		}
		ps.destroy();
		ps = null;

		// 批处理执行完后，根据cmd.exe进程名称
		// kill掉cmd窗口(这个方法是好不容易才找到了，网上很多介绍的都无效，csdn废我3分才找到这个方法)
		new ProcessUtil().killProcess();
		return true;
	}

	public void killProcess() {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean ProcessHasEnd(String ProcessName) throws IOException {
		int tasklist1 = -1;
		try {
			String cmd1 = "cmd.exe /c  tasklist";
			Process p = Runtime.getRuntime().exec(cmd1);
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[1024];
			for (int n; (n = p.getInputStream().read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			tasklist1 = out.toString().indexOf(ProcessName);// 检查QQ进程
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (tasklist1 == -1) {
			// 程序在进程中没有发现
			return false;
		} else {
			System.out.println("model is running，please wait...");
			return true;
		}
	}

	public static boolean runModel(String modelPath, String modelName) {

		if(modelPath.equals("")||modelName.equals("")) return false;
		try {
			
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
					"start "+modelName);
			builder.directory(new File(modelPath));
			Process process;
			process = builder.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
			//	System.out.println(line);
			}
			process.waitFor();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	public static boolean runModel(String modelPath, String modelName,String parameter) {

		if(modelPath.equals("")||modelName.equals("")) return false;
		try {
			
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
					" "+modelName);
			builder.directory(new File(modelPath));
			Process process;
			process = builder.start();
			   //输入参数
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			   bw.write(parameter);
			   bw.newLine();
			   bw.flush();
			   bw.close();	
			
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "GB2312");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			process.waitFor();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}


}
