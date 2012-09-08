package test.render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.vecmath.Point2d;

import model.Graph;

import org.junit.Test;

import render.GraphListRenderer;
import test.draw.BaseDrawTest;

public class GraphListRendererTest extends BaseDrawTest {
	
	public static final String OUT_DIR = "output/render";
	
	@Test
	public void basicTest() throws IOException {
		List<Graph> graphs = new ArrayList<Graph>();
		graphs.add(new Graph("0:1,0:2,1:2"));
		graphs.add(new Graph("0:1,0:3,1:2,2:3"));
		graphs.add(new Graph("0:1,0:4,1:2,2:3,3:4"));
		int w = 300;
		int h = 150;
		Image img = makeBlankImage(w, h);
		Graphics g = img.getGraphics();
		GraphListRenderer renderer = new GraphListRenderer(g);
		renderer.render(graphs, new Point2d(w / 2, h / 2));
		ImageIO.write((RenderedImage)img, "PNG", getFile(OUT_DIR, "graph_list.png"));
	}
	    

}
