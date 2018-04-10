package com.ant.image.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import clive.hua.app.simpleImageTool.SimpleImageTool;
import clive.hua.app.simpleImageTool.common.WatermarkParameter;
import clive.hua.app.simpleImageTool.exception.MyImageException;
import sun.awt.Win32FontManager;
import sun.font.FontManager;

public class SImageUtils {

	public static void main(String[] args) {
		System.out.println("start--->" + new Date().getTime());
		addTextWater();
		// addImageWater();
		//test1();
	}

	public static void addTextWater() {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("c:\\fonts\\zlt.ttf"));
			// 注册字体，再使用
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			System.out.println(font);
			
			boolean isFlag = ge.registerFont(font);
			System.out.println(isFlag);
			
			font = new Font(font.getName(), Font.PLAIN, 30);
			ImageUtils.markTextWater("d:\\0image\\1.jpg", "d:\\0image\\out_file1.png", "测试数据", font, Color.black,
					556, 170, -22);
			System.out.println("end--->" + new Date().getTime());
			
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void addImageWater() {
		String srcPath = "d:\\0image\\first.jpg";
		String waterPath = "d:\\0image\\water1.png";
		String outFilePath = "d:\\0image\\out_file3.png";

		ImageUtils.markImageWater(srcPath, waterPath, outFilePath, 100, 100, 30, 30, 1);
	}
	
	public static void test1() {
		WatermarkParameterTest watermark = new WatermarkParameterTest().addWaterText("测试水印");
				watermark.rotate(20)
                //.postion(Positions.CENTER)
                .point(200,200)
                //.rotate(20f)
                .font(new Font("c:\\fonts\\simkai.ttf",Font.PLAIN,20))
                .color(Color.blue)
                .opacity(1.0f);
		
		 try {
			SimpleImageTool.of("d:\\0image\\first.jpg")
			 .size(600,600)
			 .watermark(watermark)
			 .toFile(new File("d:\\0image\\out_file2.png"));
		} catch (MyImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
