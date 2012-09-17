package test.render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import layout.ConcentricCircularLayout;
import layout.GraphLayout;
import layout.SimpleLayout;
import model.Graph;

import org.junit.Test;

import render.GraphRenderer;
import test.draw.BaseDrawTest;
import draw.ParameterSet;

public class GraphRendererTest extends BaseDrawTest {
	
	public static final String OUT_DIR = "output/render";
	
	public void draw(Graph graph, SimpleLayout layout, ParameterSet params, int w, int h, String filename) throws IOException {
		Image img = makeBlankImage(w, h);
		Graphics g = img.getGraphics();
		params.set("vertexRadius", 2);
		GraphRenderer renderer = new GraphRenderer(g, layout, params);
		renderer.render(graph, new Rectangle2D.Double(0, 0, w, h));
		ImageIO.write((RenderedImage)img, "PNG", getFile(OUT_DIR, filename + ".png"));
		
	}
	
	@Test
	public void circleTest() throws IOException {
		int w = 300;
		int h = 300;
		Graph graph = new Graph("0:1,0:9,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8:9");
		ParameterSet params = new ParameterSet();
		SimpleLayout layout = new ConcentricCircularLayout(params); 
		draw(graph, layout, params, w, h, "circle");
	}
	
	@Test
	public void multiPartGraphTest() throws IOException {
		int w = 300;
		int h = 300;
		Graph graph = new Graph("0:1,0:2,0:4,1:3,2:3,3:5");
		ParameterSet params = new ParameterSet();
		SimpleLayout layout = new GraphLayout(params);
		draw(graph, layout, params, w, h, "multipart");
	}

}
