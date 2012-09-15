package test.planar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import layout.ConcentricCircularLayout;
import model.Graph;
import model.GraphFileReader;

import org.junit.Test;

import render.GraphRenderer;
import draw.ParameterSet;

public class GeneratedGraphsTest {
	
	public void draw(String inputDir, String outputDir, int w, int h) throws IOException {
		File outDir = new File(outputDir);
		if (!outDir.exists()) { outDir.mkdir(); }
		
		int i = 0;
		for (Graph graph : GraphFileReader.readAll(inputDir)) {
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g = (Graphics2D) image.getGraphics();
			ParameterSet params = new ParameterSet();
			GraphRenderer renderer = new GraphRenderer(g, new ConcentricCircularLayout(params), params);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, w, h);
			try {
				renderer.render(graph, new Rectangle2D.Double(0, 0, w, h));
			} catch (Exception e) {
				System.out.println("ERROR for " + i + " = " + graph);
			}
			g.dispose();

			// write
			String filename = "g" + i + ".png";
			ImageIO.write(image, "PNG", new File(outDir, filename));
			i++;
		}
	}
	
	@Test
	public void testSixThrees() throws IOException {
		draw("output/degree_threes/six_threes.txt", "gen_planar_6_3/", 300, 300);
	}
	
	@Test
	public void testTenThrees() throws IOException {
		draw("output/degree_threes/ten_threes.txt", "gen_planar_10_3/", 300, 300);
	}
	
	@Test
	public void testTwelveThrees() throws IOException {
		draw("output/degree_threes/twelve_threes.txt", "gen_planar_12_3/", 300, 300);
	}


}
