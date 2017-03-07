package com.dataUtil.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.DataConversion;

/**
 * Binary data helper,data input and output
 * 
 * @author Administrator
 * 
 */
public class BinaryIO {
	final static Logger logger=LoggerFactory.getLogger(BinaryIO.class);
	// public static enum BinarySaveType{Double,Int};
	/**
	 * Get long integer data from binary file.
	 * 
	 * @param in
	 *            RandomAccessFile
	 * @return long integer data
	 */
	public static long readLong(RandomAccessFile in) {
		long rslt = 0;
		byte[] longBytes = new byte[8];
		try {
			in.read(longBytes, 0, 8);
			rslt = DataConversion.bytesToLong(longBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Read long error.");

		}
		return rslt;
	}

	/**
	 * Get integer data from binary file.
	 * 
	 * @param in
	 *            RandomAccessFile
	 * @return integer data
	 */
	public static int readInt(RandomAccessFile in) {
		int rslt = 0;
		byte[] intBytes = new byte[4];
		try {
			in.read(intBytes, 0, 4);
			rslt = DataConversion.bytesToInt(intBytes);

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Int Read Error.");
		}
		return rslt;
	}

	/**
	 * Get float data from binary file.
	 * 
	 * @param in
	 *            RandomAccessFile
	 * @return float data
	 */
	public static float readFloat(RandomAccessFile in) {
		float rslt = 0;
		byte[] floatBytes = new byte[4];
		try {
			in.read(floatBytes, 0, 4);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Float read  error.");
		}
		return rslt;
	}

	/**
	 * Get double data from binary file.
	 * 
	 * @param in
	 *            RandomAccessFile
	 * @return double data
	 */
	public static double readDouble(RandomAccessFile in) {
		double rslt = 0.0;
		byte[] doubleBytes = new byte[8];
		try {
			in.read(doubleBytes, 0, 8);
			rslt = DataConversion.bytesToDouble(doubleBytes);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Double read error.");
		}
		return rslt;
	}

	/**
	 * Get String data from binary file.
	 * 
	 * @param in
	 *            RandomAccessFile
	 * @return String data
	 */
	public static String readString(RandomAccessFile in, int StringLength) {
		String rslt = "";
		if (StringLength <= 0) {
			logger.warn("String length should be more than 0.");
			return rslt;
		}
		byte[] stringBytes = new byte[StringLength];
		try {
			in.read(stringBytes, 0, StringLength);
			rslt = DataConversion.charsToString(DataConversion
					.byteToChars(stringBytes));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("read String error.");
		}
		return rslt;
	}

	/**
	 * Write long integer data to the DataOutputStrem
	 * 
	 * @param out
	 *            the DataOutputStrem of save binary file.
	 * @param writeData
	 *            long integer data
	 */
	public static void writeLong(DataOutputStream out, long writeData) {
		if (out.equals(null) || writeData == 0) {
			logger.warn("DataOutputStream or writeData can not be null or 0.");
			return;
		}
		byte[] longBytes = DataConversion.longToBytes(writeData);
		try {
			out.write(longBytes);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Write long error.");
		}
	}

	/**
	 * Write long integer data to the DataOutputStrem
	 * 
	 * @param out
	 *            the DataOutputStrem of save binary file.
	 * @param writeData
	 *            long integer data
	 */

	public static void writeInt(DataOutputStream out, int writeData) {
		if (out.equals(null) || writeData == 0) {
			logger.warn("DataOutputStream or writeData can not be null or 0.");
			return;
		}
		byte[] intBytes = DataConversion.intToBytes(writeData);
		try {
			out.write(intBytes);

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Write int error.");
		}

	}

	/**
	 * Write float data to the DataOutputStrem
	 * 
	 * @param out
	 *            the DataOutputStrem of save binary file.
	 * @param writeData
	 *            float data
	 */
	public static void writeFloat(DataOutputStream out, float writeData) {
		if (out.equals(null) || writeData == 0) {
			logger.warn("DataOutputStream or writeData can not be null or 0.");
			return;
		}
		byte[] floatBytes = DataConversion.floatToBytes(writeData);
		try {
			out.write(floatBytes);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Write float error.");
		}
	}

	/**
	 * Write double data to the DataOutputStrem
	 * 
	 * @param out
	 *            the DataOutputStrem of save binary file.
	 * @param writeData
	 *            double data
	 */
	public static void writeDouble(DataOutputStream out, double writeData) {
		if (out.equals(null) || writeData == 0) {
			logger.warn("DataOutputStream or writeData can not be null or 0.");
			return;
		}
		byte[] doubleBytes = DataConversion.doubleToBytes(writeData);
		try {
			out.write(doubleBytes);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Write double error.");
		}
	}
	/**
	 * Write String data to the DataOutputStrem
	 * @param out
	 * the DataOutputStrem of save binary file.
	 * @param writeData
	 * String data
	 */
	public static void writeString(DataOutputStream out, String writeData,
			int StrLen) {
		if (out.equals(null) || writeData.equals("") || StrLen <= 0) {
			logger.warn("DataOutputStream or writeData can not be null or blank,StrLen should more than 0.");
		}
		byte[] stringBytes = DataConversion.charsToBytes(DataConversion
				.stringToChars(writeData, StrLen));

		try {
			out.write(stringBytes);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Write string error.");
		}

	}
	
	

}
