package test.render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import layout.ConcentricCircularLayout;
import model.Graph;

import org.junit.Test;

import render.GraphRenderer;
import test.draw.BaseDrawTest;
import draw.ParameterSet;

public class GraphRendererTest extends BaseDrawTest {
	
	public static final String OUT_DIR = "output/render";
	
	@Test
	public void basicTest() throws IOException {
		int w = 300;
		int h = 300;
		Graph graph = new Graph("0:1,0:9,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8:9");
		Image img = makeBlankImage(w, h);
		Graphics g = img.getGraphics();
		ParameterSet params = new ParameterSet();
		params.set("vertexRadius", 2);
		GraphRenderer renderer = new GraphRenderer(g, new ConcentricCircularLayout(params), params);
		renderer.render(graph, new Rectangle2D.Double(0, 0, w, h));
//		renderer.render(graph, new Point2d(w/2, h/2));
		ImageIO.write((RenderedImage)img, "PNG", getFile(OUT_DIR, "circle.png"));
	}

}
