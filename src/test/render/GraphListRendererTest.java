package test.render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.vecmath.Point2d;

import layout.GridCanvasLayout;
import layout.ILayout;
import layout.LinearLayout;
import model.Graph;

import org.junit.Test;

import render.GraphListRenderer;
import test.draw.BaseDrawTest;
import divide.GridDivider;
import divide.LinearDivider;
import divide.RegularDivider;

public class GraphListRendererTest extends BaseDrawTest {
	
	public static final String OUT_DIR = "output/render";
	
	public void standardTestSet(int w, int h, RegularDivider divider, ILayout layout, String outFile) throws IOException {
		List<Graph> graphs = new ArrayList<Graph>();
		graphs.add(new Graph("0:1,0:2,1:2"));
		graphs.add(new Graph("0:1,0:3,1:2,2:3"));
		graphs.add(new Graph("0:1,0:4,1:2,2:3,3:4"));
		graphs.add(new Graph("0:1,0:5,1:2,2:3,3:4,4:5"));
		Image img = makeBlankImage(w, h);
		Graphics g = img.getGraphics();
		GraphListRenderer renderer;
		if (divider != null) {
			renderer = new GraphListRenderer(divider, g);
			renderer.render(graphs, new Rectangle2D.Double(0, 0, w, h));
		} else {
			if (layout != null) {
				renderer = new GraphListRenderer(layout, g);
				renderer.render(graphs, new Point2d(w / 2, h / 2));
			} else {
				System.err.println("Specify one of divider or layout!");
				return;
			}
		}
		ImageIO.write((RenderedImage)img, "PNG", getFile(OUT_DIR, outFile));
	}
	
	@Test
	public void linearLayoutTest() throws IOException {
		int w = 400;
		int h = 150;
		standardTestSet(w, h, null, new LinearLayout(), "graph_list_linear_layout.png");
	}
	
	@Test
	public void gridLayoutTest() throws IOException {
		int w = 400;
		int h = 400;
		standardTestSet(w, h, null, new GridCanvasLayout(), "graph_list_grid_layout.png");
	}
	
	@Test
	public void linearDividerTest() throws IOException {
		int w = 400;
		int h = 150;
		standardTestSet(w, h, new LinearDivider(), null, "graph_list_linear_divider.png");
	}
	
	@Test
	public void gridDividerTest() throws IOException {
		int w = 400;
		int h = 400;
		standardTestSet(w, h, new GridDivider(), null, "graph_list_grid_divider.png");
	}
}
