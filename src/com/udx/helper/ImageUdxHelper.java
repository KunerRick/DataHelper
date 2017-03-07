package com.udx.helper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelIntArray;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;

public class ImageUdxHelper {

	public static UdxDataset img2Udx(String filename,String udxDllsPath) throws Exception{
		
		File file = new File(filename);
		//为什么我们用FileInputStream读取图片数据，而不是使用BufferedImage读取图片的rgb值，这样省去了指定图片格式的麻烦，图片可能是RGB、GBR等等其他颜色格式的。
		//FileInputStream fis = new FileInputStream(file);
		
		BufferedImage bufferedImage = ImageIO.read(file);   
		int width = bufferedImage.getWidth();   
		int height = bufferedImage.getHeight();   
		
		UdxDataset udx_return_Dt = new UdxDataset(udxDllsPath);
		
		//head
		UdxNode headNode = udx_return_Dt.addChildNode("head", EKernelType.EKT_NODE, 1);
		
		UdxNode pathNode = headNode.addChildNode("path", EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue)pathNode.getKernel()).setTypedValue(filename);
		
		UdxNode widthNode = headNode.addChildNode("width", EKernelType.EKT_INT, 1);
		((UdxKernelIntValue)widthNode.getKernel()).setTypedValue(width);
		
		UdxNode heightNode = headNode.addChildNode("height", EKernelType.EKT_INT, 1);
		((UdxKernelIntValue)heightNode.getKernel()).setTypedValue(height);
		
		UdxNode desNode = headNode.addChildNode("description", EKernelType.EKT_STRING, 1);
		((UdxKernelStringValue)desNode.getKernel()).setTypedValue("this is a output image.");
		
		//body
		UdxNode bodyNode = udx_return_Dt.addChildNode("body", EKernelType.EKT_LIST, 1);
		
//		int data = -1;
		
		File f = new File(filename);
		BufferedImage bi = ImageIO.read(f);
		
			for (int i = 0; i < height; i++) {
				UdxNode rowNode = bodyNode.addChildNode("row"+(i+1), EKernelType.EKT_INT, width);
			
				for (int j = 0; j < width; j++) {
											                
					((UdxKernelIntArray)rowNode.getKernel()).addTypedValue(bi.getRGB(i,j));
				}
			}
		
		
//		fis.close();
			
		
		return udx_return_Dt;
	}
	
	public static boolean udx2Img(UdxDataset dataset,String savePath,String udxDllsPath) throws Exception{
		
		boolean result = true;
		
		//head
		UdxNode headNode =  dataset.getChildNode(0);
		
//		UdxNode pathNode = headNode.getChildNode(0);
//		String path = ((UdxKernelStringValue)pathNode.getKernel()).getTypedValue();
		
		//width
		UdxNode widthNode = headNode.getChildNode(1);
		int width = ((UdxKernelIntValue)widthNode.getKernel()).getTypedValue();
		
		//height
		UdxNode heightNode = headNode.getChildNode(2);
		int height = ((UdxKernelIntValue)heightNode.getKernel()).getTypedValue();

		UdxNode bodyNode =  dataset.getChildNode(1);
		
//		File file = new File(savePath);
//		FileOutputStream fos = new FileOutputStream(file);
		
		File file = new File(savePath);
		BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < height; i++) {
			
			UdxNode rowNode = bodyNode.getChildNode(i);
			
			for (int j = 0; j < width; j++) {
				
				int data = ((UdxKernelIntArray)rowNode.getKernel()).getTypedValueByIndex(j);
//				fos.write((byte)data);
//				 int r = (data & 0xff0000) >> 16;  
//	             int g = (data & 0xff00) >> 8;  
//	             int b = (data & 0xff);  
//				Color c = new Color(r,g, b);
				bi.setRGB(i,j,data);
			}
		}
		ImageIO.write(bi, "jpg", file);
//		fos.flush();
//		fos.close();
		
		return result;
		
	}
}
