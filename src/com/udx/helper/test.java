package com.udx.helper;

import com.ngis.udx.UdxDataset;

public class test {

	public static void main(String[] args) {

		testAscGridHelper();
	}
	
	public static void testImgHelper(){
		try {
			UdxDataset ds = ImageUdxHelper.img2Udx("G:\\out.jpg","C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
//			ds.FormatToXmlFile("C:\\out.xml");

		if(ImageUdxHelper.udx2Img(ds, "C:\\out.jpg", "C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library"))
				System.out.println("ok");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public void testOMS(){
		try {
			UdxDataset ds = ImageUdxHelper.img2Udx("G:\\out.jpg","C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
			
//			ds.FormatToXmlFile("C:\\out.xml");
			
			if(ImageUdxHelper.udx2Img(ds, "C:\\out.jpg", "C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library"))
				System.out.println("ok");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public static void testRsHelper() {
		try {

//			UdxDataset ds = RsUdxHelper.rsBSQ2UDX("G:\\AA.hdr",
//					"C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
//			 ds.FormatToXmlFile("C:\\oms_input.xml");

			
			UdxDataset udxDataset = new UdxDataset("C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
			udxDataset.LoadFromXmlFile("C:\\oms_input.xml");
			
			RsUdxHelper.udx2RsBSQ(udxDataset, "C:\\AA.hdr","C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");

			System.out.println("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testAscGridHelper() {
		try {
			UdxDataset ds = AsciiGridUdxHelper.asciiGrid2Udx("D:\\DEM.txt",
					"C:\\Users\\Administrator\\workspace2\\UdxHelper\\Library");
			ds.FormatToXmlFile("C:\\Users\\Administrator\\Desktop\\UDXToolkit\\new.xml");

			System.out.println("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
