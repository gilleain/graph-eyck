package test.render;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Graph;
import model.Metagraph;

import org.junit.Test;

import render.MetagraphRenderer;
import test.draw.BaseDrawTest;

public class MetagraphRendererTest extends BaseDrawTest {
	
	public static final String OUT_DIR = "output/render";
	
	@Test
	public void basicTest() throws IOException {
		int w = 300;
		int h = 300;
		Graph topLevelGraph = new Graph("0:1,1:2,1:3");
		Metagraph metagraph = new Metagraph();
		Image img = makeBlankImage(w, h);
		Graphics g = img.getGraphics();
		MetagraphRenderer renderer = new MetagraphRenderer(g);
		renderer.render(metagraph, new Rectangle2D.Double(0, 0, w, h));
		ImageIO.write((RenderedImage)img, "PNG", getFile(OUT_DIR, "tree_of_graphs.png"));
	}
}
