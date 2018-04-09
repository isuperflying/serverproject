package com.ant.image.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	// ������ˮӡ
	public void mark(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y) {
		Graphics2D g = bufImg.createGraphics();
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.setColor(color);
		g.setFont(font);
		g.rotate(Math.toRadians(135), x, y);
		g.drawString(text, x, y);
		g.dispose();
	}

	// ��ͼƬˮӡ
	public void mark(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y) {
		Graphics2D g = bufImg.createGraphics();
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.drawImage(markImg, x, y, width, height, null);
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
	public void mark(String imgPath, String outImgPath, String text, Font font, Color color, int x, int y) {
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
			mark(bufImg, img, text, font, color, x, y);
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
	public void mark(String inputImg, String markImg, String outputImg, int width, int height, int x, int y) {
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
			mark(bufImg, img, mark, width, height, x, y);
			FileOutputStream outImgStream = new FileOutputStream(outputImg);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
			System.out.println("ˮӡͼƬ�������--->");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        Font font = new Font("����", Font.PLAIN, 14);  
        // ԭͼλ��, ���ͼƬλ��, ˮӡ������ɫ, ˮӡ����  
        // new MarkText4J().mark("eguidMarkText2.jpg", "eguidMarkText2.jpg", "ˮӡЧ������", font, Color.ORANGE, 0, 14);
        // ����ͼƬˮӡ
        new ImageUtils().mark("D:\\0image\\first.jpg", "D:\\0image\\water1.png", "D:\\0image\\out_file7.png", 80, 80, 200, 200);
    } 
}






