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
		int w = 600;
		int h = 600;
		
		Metagraph metagraph = new Metagraph();
		metagraph.addSubgraph(new Graph("0:1,0:2,1:2"));
		metagraph.addSubgraph(new Graph("0:1,0:3,1:2,2:3"));
		metagraph.addSubgraph(new Graph("0:1,0:2,0:3,1:2,1:3"));
		metagraph.addSubgraph(new Graph("0:1,0:3,1:2,2:3,3:4"));
		metagraph.addSubgraph(new Graph("0:1,0:3,1:2,1:4,2:3,3:5"));
		metagraph.addEdge(0, 1);
		metagraph.addEdge(0, 2);
		metagraph.addEdge(1, 3);
		metagraph.addEdge(1, 4);
		
		Image img = makeBlankImage(w, h);
		Graphics g = img.getGraphics();
		MetagraphRenderer renderer = new MetagraphRenderer(g);
		renderer.render(metagraph, new Rectangle2D.Double(0, 0, w, h));
		ImageIO.write((RenderedImage)img, "PNG", getFile(OUT_DIR, "tree_of_graphs.png"));
	}
}
