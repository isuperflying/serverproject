package net.coobird.thumbnailator;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.filters.Caption;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thumbnailator4();
	}

	public static void thumbnailator1() {
		try {
			Thumbnails.of("d:\\0image\\first.jpg").size(80, 80).toFile("d:\\0image\\out1.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void thumbnailator2() {
		try {
			Thumbnails.of(new File("d:\\0image\\\\first.jpg")).scale(0.25).rotate(90).outputFormat("jpg")
					.toFile("d:\\0image\\out2.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void thumbnailator3() {
		try {

			Thumbnails.of(new File("d:\\0image\\first.jpg")).size(360, 360).rotate(90)
					.watermark(new Coordinate(10, 10), ImageIO.read(new File("d:\\0image\\water1.png")), 0.3f)
					.outputQuality(0.8f).toFile(new File("d:\\0image\\out3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void thumbnailator4() {
		try {
			File file = new File("d:\\0image\\\\first.jpg");
			BufferedImage bufferedImage = ImageIO.read(file);
			// Set up the caption properties
			String caption = "RASCUNHO";
			Font font = new Font("Monospaced", Font.PLAIN, 100);
			Color c = Color.BLACK;
			float opacity = 1f;
			Position[] positions = { Positions.TOP_LEFT, Positions.CENTER, Positions.BOTTOM_RIGHT };
			int insetPixels = 1;

			// Apply caption to the image
			Builder<BufferedImage> builder = Thumbnails.of(bufferedImage)
					.size(bufferedImage.getHeight(), bufferedImage.getWidth())
					.outputFormat("png");
			Caption cc = new Caption(caption, font, c, opacity, Positions.TOP_LEFT, insetPixels);
			
			builder.addFilter(cc);

			BufferedImage result;

			result = builder.asBufferedImage();
			ImageIO.write(result, "png", new File("d:\\0image\\\\out777.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
