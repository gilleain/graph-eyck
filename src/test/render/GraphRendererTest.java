package test.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Graph;

import org.junit.Test;

import render.GraphRenderer;

public class GraphRendererTest {
	
	public Image getImage(int w, int h) {
		return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	}
	
	@Test
	public void basicTest() throws IOException {
		int w = 300;
		int h = 300;
		Graph graph = new Graph("0:1,0:9,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8:9");
		Image img = getImage(w, h);
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		GraphRenderer renderer = new GraphRenderer(g);
		renderer.render(graph, new Rectangle2D.Double(0, 0, w, h));
//		renderer.render(graph, new Point2d(w/2, h/2));
		ImageIO.write((RenderedImage)img, "PNG", new File("test.png"));
	}

}
