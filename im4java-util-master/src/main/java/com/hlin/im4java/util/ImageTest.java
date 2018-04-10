package com.hlin.im4java.util;

public class ImageTest {

	public static void main(String[] args) {
		String srcPath = "D:\\0image\\first.jpg";
		String outPath = "D:\\0image\\out_file1.jpg";
		try {
			ImageUtil.addTextWatermark(srcPath, outPath, new String("BBB".getBytes("utf-8"),"gbk"));
			System.out.println("make done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
