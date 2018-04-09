package com.hlin.im4java.util;

public class ImageTest {

	public static void main(String[] args) {
		String srcPath = "D:\\image\\image_bg.jpg";
		String outPath = "D:\\image\\out_file1.jpg";
		try {
			ImageUtil.addTextWatermark(srcPath, outPath, "测试");
			System.out.println("make done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
