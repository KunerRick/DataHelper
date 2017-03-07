package com.udx.util;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 针对大文件存储，请依次调用beginSave、AddSave、endSave。
 * 
 * @author CK
 *
 */
public class DataUtil {

	DataOutputStream BinaryOut=null;
	BufferedWriter TextOut=null;
	String FilePath=null;
	enum SaveFileType{Text,Binary};
	SaveFileType SaveFileType;

	/**
	 * double转byte[]
	 * 
	 * @param d
	 * @return
	 */
	public static byte[] double2Bytes(double d) {
		long value = Double.doubleToRawLongBits(d);
		byte[] byteRet = new byte[8];
		for (int i = 0; i < 8; i++) {
			byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
		}
		return byteRet;
	}

	/**
	 * byte[]转double
	 * 
	 * @param arr
	 * @return
	 */
	public static double bytes2Double(byte[] arr) {
		long value = 0;
		for (int i = 0; i < 8; i++) {
			value |= ((long) (arr[i] & 0xff)) << (8 * i);
		}
		return Double.longBitsToDouble(value);
	}
	/**
	 * 大型数据存储之开始存储
	 * @param FilePath 文件路径
	 * @param saveFileType 保存的文件类型，文本文件、双精度所存的二进制文件
	 * @return
	 * @throws IOException
	 */
	public boolean BeginSave(String FilePath,SaveFileType saveFileType) throws IOException {
		if (FilePath == "" || FilePath == null) {
			System.out.println("the SavePath is null.");
			return false;
		}
		this.FilePath=FilePath;
		this.SaveFileType=saveFileType;
		File dataFile = new File(FilePath);
		if (!dataFile.getParentFile().exists()) {
			dataFile.getParentFile().mkdirs();
		}
		if (dataFile.exists()) {
			dataFile.delete();
		}
		dataFile.createNewFile();
		switch(this.SaveFileType){
		case Text:
			TextOut=  new BufferedWriter(new FileWriter(dataFile,true));
			break;
		case Binary:
			BinaryOut = new DataOutputStream(new FileOutputStream(dataFile,true));
			break;
		default:
			break;
			
		}		
		return true;
	}
/**
 * 大型文件存储之追加存储
 * @param DataStr  若是文本存储则无要求，若是双精度的二进制文件，以若干空格隔开
 * @return
 * @throws IOException
 */
	public boolean AddSave(String DataStr) throws IOException{
		switch(this.SaveFileType){
		case Text:
			this.TextOut.append(DataStr);
			break;
		case Binary:
			DataStr=DataStr.trim();
			String[] dataArray=DataStr.split("\\s+");
			for(int i=0;i<dataArray.length;i++){
				this.BinaryOut.write(double2Bytes(Double.parseDouble(dataArray[i])));
			}					
			break;
		default:
			break;
		
		}
		
		return true;
	}
	/**
	 * 大型文件存储之结束保存，清空缓存、关闭文件。
	 * @return
	 * @throws IOException
	 */
	public boolean EndSave() throws IOException{
		switch(this.SaveFileType){
		case Text:
			this.TextOut.flush();
			this.TextOut.close();
			break;
		case Binary:
			this.BinaryOut.flush();
			this.BinaryOut.close();
			break;
		default:
			break;		
		}
		
		return true;
	}
	
	public boolean flushData() throws IOException{
		switch(this.SaveFileType){
		case Text:
			this.TextOut.flush();			
			break;
		case Binary:
			this.BinaryOut.flush();
			break;
		default:
			break;		
		}
		return true;
	}
	
	/**
	 * 将字符串保存为文件（一次完成）
	 * 
	 * @param DataStr
	 *            文件内容
	 * @param SavePath
	 *            文件路径，包含文件名、后缀
	 * @return
	 * @throws IOException
	 */
	public boolean saveTextFile(String DataStr, String SavePath)
			throws IOException {
		if (DataStr == "" || DataStr == null) {
			System.out.println("the dataStr is null.");
			return false;
		}
		if (SavePath == "" || SavePath == null) {
			System.out.println("the SavePath is null.");
			return false;
		}
		File dataFile = new File(SavePath);
		if (!dataFile.getParentFile().exists()) {
			dataFile.getParentFile().mkdirs();
		}
		if (dataFile.exists()) {
			dataFile.delete();
		}
		dataFile.createNewFile();
		BufferedWriter out;

		out = new BufferedWriter(new FileWriter(dataFile));

		out.append(DataStr);
		out.flush();
		out.close();

		return true;
	}

	/**
	 * 双精度存为二进制数据（一次存储）
	 * 
	 * @param DataStr  双精度数据组成的字符串，以若干空格隔开
	 * @param OutputPath
	 * @return
	 * @throws IOException
	 */
	public boolean saveBinaryFile(String DataStr, String OutputPath) throws IOException {

		if (DataStr == "" || DataStr == null) {
			System.out.println("the dataStr is null.");
			return false;
		}
		if (OutputPath == "" || OutputPath == null) {
			System.out.println("the OutputPath is null.");
			return false;
		}
		File dataFile = new File(OutputPath);

		if (!dataFile.getParentFile().exists()) {
			dataFile.getParentFile().mkdirs();
		}
		if (dataFile.exists()) {
			dataFile.delete();
		}
		dataFile.createNewFile();
		DataOutputStream out;
		out = new DataOutputStream(new FileOutputStream(dataFile));
		// 数据处理
		DataStr=DataStr.trim();
		String[] dataArray=DataStr.split("\\s+");
		for(int i=0;i<dataArray.length;i++){
			out.write(double2Bytes(Double.parseDouble(dataArray[i])));
		}		
		out.flush();
		out.close();
		return true;

	}
}
