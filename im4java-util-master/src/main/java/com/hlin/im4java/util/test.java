package com.hlin.im4java.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IMOperation;

public class test {

	public static void main(String[] args) {

		try {

			String src = "D://0image//2.jpg"; // 需要加水印的源图片
			String desc = "D://0image//desc.jpg"; // 生成的水印图片的路径
			String water = "D://0image//water_temp.png"; // 用中文转换成的背景透明的png图片
			String fontType = "C:\\fonts\\sfzsj.TTF"; // 指定字体文件为宋体
			String colorStr = "939393"; // 颜色
			int fontSize = 16;

			Watermark watermark = new Watermark();

			/*
			 * 把文字转化为一张背景透明的png图片
			 * 
			 * @param str 文字的内容
			 * 
			 * @param fontType 字体，例如宋体
			 * 
			 * @param fontSize 字体大小
			 * 
			 * @param colorStr 字体颜色，不带#号，例如"990033"
			 * 
			 * @param outfile png图片的路径
			 * 
			 * @throws Exception
			 */
			watermark.converFontToImage("小可爱", fontType, fontSize, colorStr, water);

			/*
			 * 把文字的png图片贴在原图上，生成水印
			 * 
			 * @param srcPath 原图片路径
			 * 
			 * @param distPath 新图片路径
			 * 
			 * @param watermarkImg 水印图片路径
			 * 
			 * @param position 九宫格位置[1-9],从上往下,从左到右排序
			 * 
			 * @param x 横向边距
			 * 
			 * @param y 纵向边距
			 * 
			 * @param alpha 透明度
			 */
			watermark.WatermarkImg(src, desc, water, 1, 290, 200, 200);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
