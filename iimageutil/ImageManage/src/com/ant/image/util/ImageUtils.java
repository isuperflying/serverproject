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

	// ������ˮӡ
	public static void markText(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y,int angle) {
		Graphics2D g = bufImg.createGraphics();
		//ȥ���Ч��
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
		
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.setColor(color);
		g.setFont(font);
		g.rotate(Math.toRadians(angle), x, y);
		g.drawString(text, x, y);
		g.dispose();
	}

	// ��ͼƬˮӡ
	public static void markImage(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y,float alpha) {
		Graphics2D g = bufImg.createGraphics();
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.drawImage(markImg, x, y, width, height, null);
		//͸����
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
		g.dispose();
	}

	/**
	 * ��ͼƬ��������ˮӡ
	 * 
	 * @param imgPath
	 *            -Ҫ���ˮӡ��ͼƬ·��
	 * @param outImgPath
	 *            -���·��
	 * @param text-����
	 * @param font
	 *            -����
	 * @param color
	 *            -��ɫ
	 * @param x
	 *            -����λ�ڵ�ǰͼƬ�ĺ�����
	 * @param y
	 *            -����λ�ڵ�ǰͼƬ��������
	 */
	public static void markTextWater(String imgPath, String outImgPath, String text, Font font, Color color, int x, int y,int angle) {
		try {
			// ��ȡԭͼƬ��Ϣ
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
			// ��ˮӡ
			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
			markText(bufImg, img, text, font, color, x, y,angle);
			// ���ͼƬ
			FileOutputStream outImgStream = new FileOutputStream(outImgPath);
			ImageIO.write(bufImg, "png", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ͼƬ����ͼƬˮӡ
	 * 
	 * @param inputImg
	 *            -ԴͼƬ��Ҫ���ˮӡ��ͼƬ
	 * @param markImg
	 *            - ˮӡͼƬ
	 * @param outputImg
	 *            -���ͼƬ(������ԴͼƬ)
	 * @param width
	 *            - ˮӡͼƬ���
	 * @param height
	 *            -ˮӡͼƬ�߶�
	 * @param x
	 *            -�����꣬�����ԴͼƬ
	 * @param y
	 *            -�����꣬ͬ��
	 */
	public static void markImageWater(String inputImg, String markImg, String outputImg, int width, int height, int x, int y,float alpha) {
		// ��ȡԭͼƬ��Ϣ
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
			System.out.println("ˮӡͼƬ�������--->");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}






