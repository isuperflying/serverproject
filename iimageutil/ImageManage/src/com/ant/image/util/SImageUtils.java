package com.ant.image.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import clive.hua.app.simpleImageTool.SimpleImageTool;
import clive.hua.app.simpleImageTool.common.Positions;
import clive.hua.app.simpleImageTool.common.WatermarkParameter;

public class SImageUtils {

	public static void main(String[] args) {
		/*String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522664039261&di=8c8de5092d1f26ba12c2a7b8316a4952&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123912727.jpg";
		try {
			URL url = new URL(imgUrl);
			URLConnection connection = url.openConnection();
			InputStream input = connection.getInputStream();

			File tempFile = new File("d:/image/image_bg.jpg");

			SimpleImageTool.of("d:/image/image_bg.jpg").size(300, 300).toFile(new File("d:/0image/result2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		addWater();
	}

	public static void addWater() {
		try {
			BufferedImage watermarkImage = ImageIO.read(new FileInputStream("d:\\0image\\water1.png"));
			WatermarkParameter watermark = new WatermarkParameter().addWaterMarkImage(watermarkImage).rotate(20f);
					// .rotate(20f).opacity(0.2f);
			// 网络获取图片进行相应的图片处理 水印 缩放 或者剪切，旋转
			String src = "http://img1.ph.126.net/gpQLzCkas6xH4_amx3nFUw==/6598146188541857497.jpg";
			URL url = new URL(src);
			URLConnection uri = url.openConnection();
			// 获取数据流
			InputStream is = uri.getInputStream();

			SimpleImageTool.of("d:\\0image\\first.jpg").size(600, 600).watermark(watermark)
					// .watermark(watermark2)
					.toFile(new File("d:\\0image\\out_file6.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
