package com.ant.image.util;

import clive.hua.app.simpleImageTool.common.WatermarkParameter;
import clive.hua.app.simpleImageTool.util.ImageUtils;
import clive.hua.app.simpleImageTool.util.WaterMarkUtils;
import net.coobird.thumbnailator.geometry.Position;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class WatermarkParameterTest extends WatermarkParameter{
	private Font font;
	private Color color;
	private Color bgColor;
	private float alpha = 1.0F;
	private double degree;
	private Point point;
	private BufferedImage waterImage;
	private String warerText;
	private Position position;

	private void init() {
		this.font = new Font("????", 1, 30);
		this.color = Color.blue;
		this.bgColor = null;
		//this.alpha = 0.6F;
		this.degree = 0.0D;
		this.point = new Point(0, 0);
		this.waterImage = null;
		this.warerText = "";
		this.position = null;
	}

	public WatermarkParameterTest() {
		init();
	}

	public WatermarkParameterTest opacity(float alpha) {
		if ((alpha >= 1.0F) || (alpha < 0.0F)) {
			return this;
		}
		this.alpha = alpha;
		return this;
	}

	public WatermarkParameterTest postion(Position position) {
		this.position = position;
		return this;
	}

	public WatermarkParameterTest font(Font font) {
		this.font = font;
		return this;
	}

	public WatermarkParameterTest color(Color color) {
		this.color = color;
		return this;
	}

	public WatermarkParameterTest bgColor(Color bgColor) {
		this.bgColor = bgColor;
		return this;
	}

	public WatermarkParameterTest rotate(float degree) {
		this.degree = degree;
		return this;
	}

	public WatermarkParameterTest point(int x, int y) {
		if ((0 == x) && (0 == y)) {
			return this;
		}
		this.point = new Point(x, y);
		return this;
	}

	public WatermarkParameterTest addWaterMarkImage(BufferedImage waterImage) {
		this.waterImage = waterImage;
		this.warerText = "";
		return this;
	}

	public WatermarkParameterTest addWaterText(String warerText) {
		this.warerText = warerText;
		this.waterImage = null;
		return this;
	}

	public BufferedImage apply(BufferedImage image) {
		int srcWidth = image.getWidth();
		int srcHeight = image.getHeight();
		if (!this.warerText.isEmpty()) {
			int[] size = WaterMarkUtils.getTextWidthHeight(this.warerText, this.font);
			this.waterImage = WaterMarkUtils.createWaterTextImage(this.warerText, size, this.font, this.color,
					this.bgColor);
		}
		if (this.waterImage != null) {
			int watermarkWidth = this.waterImage.getWidth();
			int watermarkHeight = this.waterImage.getHeight();
			if ((watermarkHeight > srcHeight) || (watermarkWidth > srcWidth)) {
				this.waterImage = ImageUtils.scale(this.waterImage, (int) (srcWidth * 0.25D), (int) (srcHeight * 0.25D),
						0.0D, true, null);
			}
			if (this.degree != 0.0D) {
				this.waterImage = ImageUtils.rotate(this.waterImage, this.degree, null);
			}
			watermarkWidth = this.waterImage.getWidth();
			watermarkHeight = this.waterImage.getHeight();
			BufferedImage WithWatermarkImage = ImageUtils.createNewImage(image, srcWidth, srcHeight);
			if (this.position != null) {
				this.point = this.position.calculate(srcWidth, srcHeight, watermarkWidth, watermarkHeight, 0, 0, 0, 0);
			}
			Graphics2D g = WithWatermarkImage.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
			g.drawImage(image, 0, 0, null);
			//g.setComposite(AlphaComposite.getInstance(3, this.alpha));
			g.drawImage(this.waterImage, this.point.x, this.point.y, null);
			g.dispose();
			
			System.out.println("test run--->");
			
			return WithWatermarkImage;
		}
		return image;
	}
}
