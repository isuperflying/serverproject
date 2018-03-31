package com.ant.image.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import clive.hua.app.simpleImageTool.SimpleImageTool;

public class SImageUtils {
	
	public static void main(String[] args) {
		String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516556276721&di=373796127f8eb7473d55a5f55d0ff401&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2F2015%2Fa1%2F43%2Fd%2F82.jpg";
		try {
			URL url = new URL(imgUrl);
			URLConnection connection = url.openConnection();
			InputStream input = connection.getInputStream();
			
			File tempFile = new File("d:/image/image_bg.jpg");
			
			SimpleImageTool.of("d:/image/image_bg.jpg").size(300,300).toFile(new File("d:/image/test3.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
