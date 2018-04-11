package com.hlin.im4java.util;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageTest {

	public static String srcPath = "D:\\0image\\2.jpg";
	public static String outPath = "D:\\0image\\out_file1.png";

	public static int TO_LEFT = 0;

	public static int TO_RIGHT = 1;

	public static int TO_CENTER = 2;

	public static void main(String[] args) {
		test2();
	}

	// 根据字体，获取需要添加水印的文字的宽度
	public static int getFontStrWidth(String fontPath,int fontSize,String content) {
		Font font = new Font(fontPath, Font.PLAIN, fontSize);
		// 获取font的样式应用在str上的整个矩形
		Rectangle2D r = font.getStringBounds(content, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
		// int unitHeight = (int) Math.floor(r.getHeight());// 获取单个字符的高度
		// 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
		return (int) Math.round(r.getWidth()) + 1;
	}

	public static void test1() {
		try {
			long start = new Date().getTime();

			ImageUtil.addTextWatermark(srcPath, outPath, "腾牛生成器");

			long times = new Date().getTime() - start;
			System.out.println("使用时间(毫秒)--->" + times);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		try {
			long start = new Date().getTime();

			List<Object[]> tList = new ArrayList<Object[]>();

			Object[] params1 = { "c:\\fonts\\sfzsj.TTF", 16, "#939393", 0, 185, 260, 249, 276, TO_RIGHT, "小可爱" };

			if (Integer.parseInt(params1[8].toString()) == TO_RIGHT) {
				int mwidth = Integer.parseInt(params1[6].toString()) - Integer.parseInt(params1[4].toString());
				int twidth = getFontStrWidth(params1[0].toString(),Integer.parseInt(params1[1].toString()),params1[9].toString());
				params1[4] = Integer.parseInt(params1[4].toString()) + (mwidth - twidth);
				
				System.out.println("文本长度" + twidth);
			}
			System.out.println("real start x --->" + params1[4]);
			
			// 文字一信息
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("font", params1[0]);
			tMap.put("size", params1[1]);
			tMap.put("color", params1[2]);
			tMap.put("rotate", params1[3]);
			tMap.put("point", params1[4] + "," + params1[7]);
			tMap.put("content", params1[9]);// 内容

			// 文字二信息
			Map<String, Object> tMap1 = new HashMap<String, Object>();
			tMap1.put("font", "c:\\\\fonts\\\\szzt.ttf");
			tMap1.put("size", 30);
			tMap1.put("color", "#060606");
			tMap1.put("rotate", 0);
			// tMap1.put("point", "100,320");

			int textLength = getFontStrWidth("c:\\\\fonts\\\\szzt.ttf",30,"6663421666.00元");
			System.out.println("tl--" + textLength);
			int width = (390 - textLength) / 2 + 100;
			System.out.println("width--" + width);

			tMap1.put("point", width + ",320");

			tMap1.put("content", "6663421666.00元");// 内容

			//tList.add(tMap);
			//tList.add(tMap1);
			tList.add(params1);
			ImageUtil.addTextWatermark(srcPath, outPath, tList);

			long times = new Date().getTime() - start;
			System.out.println("test2 使用时间(毫秒)--->" + times);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
