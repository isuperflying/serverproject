package com.ant.image.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	// 加文字水印
	public static void markText(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y,int angle) {
		Graphics2D g = bufImg.createGraphics();
		//去锯齿效果
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
		
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.setColor(color);
		g.setFont(font);
		g.rotate(Math.toRadians(angle), x, y);
		g.drawString(text, x, y);
		g.dispose();
	}

	// 加图片水印
	public static void markImage(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y,float alpha) {
		Graphics2D g = bufImg.createGraphics();
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.drawImage(markImg, x, y, width, height, null);
		//透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
		g.dispose();
	}

	/**
	 * 给图片增加文字水印
	 * 
	 * @param imgPath
	 *            -要添加水印的图片路径
	 * @param outImgPath
	 *            -输出路径
	 * @param text-文字
	 * @param font
	 *            -字体
	 * @param color
	 *            -颜色
	 * @param x
	 *            -文字位于当前图片的横坐标
	 * @param y
	 *            -文字位于当前图片的竖坐标
	 */
	public static void markTextWater(String imgPath, String outImgPath, String text, Font font, Color color, int x, int y,int angle) {
		try {
			// 读取原图片信息
			File imgFile = null;
			Image img = null;
			if (imgPath != null) {
				imgFile = new File(imgPath);
			}
			if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead()) {
				img = ImageIO.read(imgFile);
			}
			int imgWidth = img.getWidth(null);
			int imgHeight = img.getHeight(null);
			// 加水印
			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
			markText(bufImg, img, text, font, color, x, y,angle);
			// 输出图片
			FileOutputStream outImgStream = new FileOutputStream(outImgPath);
			ImageIO.write(bufImg, "png", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片增加图片水印
	 * 
	 * @param inputImg
	 *            -源图片，要添加水印的图片
	 * @param markImg
	 *            - 水印图片
	 * @param outputImg
	 *            -输出图片(可以是源图片)
	 * @param width
	 *            - 水印图片宽度
	 * @param height
	 *            -水印图片高度
	 * @param x
	 *            -横坐标，相对于源图片
	 * @param y
	 *            -纵坐标，同上
	 */
	public static void markImageWater(String inputImg, String markImg, String outputImg, int width, int height, int x, int y,float alpha) {
		// 读取原图片信息
		File inputImgFile = null;
		File markImgFile = null;
		Image img = null;
		Image mark = null;
		try {
			if (inputImg != null && markImg != null) {
				inputImgFile = new File(inputImg);
				markImgFile = new File(markImg);
			}
			if (inputImgFile != null && inputImgFile.exists() && inputImgFile.isFile() && inputImgFile.canRead()) {

				img = ImageIO.read(inputImgFile);

			}
			if (markImgFile != null && markImgFile.exists() && markImgFile.isFile() && markImgFile.canRead()) {

				mark = ImageIO.read(markImgFile);

			}
			int imgWidth = img.getWidth(null);
			int imgHeight = img.getHeight(null);
			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
			markImage(bufImg, img, mark, width, height, x, y,alpha);
			FileOutputStream outImgStream = new FileOutputStream(outputImg);
			ImageIO.write(bufImg, "png", outImgStream);
			outImgStream.flush();
			outImgStream.close();
			System.out.println("水印图片制作完成--->");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}






