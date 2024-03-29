package test.planar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import layout.BlockLayout;
import layout.ConcentricCircularLayout;
import layout.NullLayout;
import layout.Refiner;
import planar.BlockEmbedding;
import render.GraphRenderer;
import draw.Colorer;
import draw.ParameterSet;

public abstract class AbstractDrawingTest {
	
	public static int WIDTH = 400;
	
	public static int HEIGHT = 400;
	
	public abstract String getOutputDir();
	
	public void draw(BlockEmbedding em, String filename) throws IOException {
		int w = 300;
		int h = 300;
		draw(em, w, h, filename, null, null);
	}
	
	public void draw(BlockEmbedding em, String filename, Colorer colorer) throws IOException {
		int w = 300;
		int h = 300;
		draw(em, w, h, filename, null, null);
	}
	
	public void draw(BlockEmbedding em, int w, int h, String filename) throws IOException {
		draw(em, w, h, filename, null, null);
	}
	
	public void draw(BlockEmbedding em, int w, int h, String filename, Colorer colorer) throws IOException {
	    draw(em, w, h, filename, colorer, null);
	}
	
	public void draw(
            BlockEmbedding em, int w, int h, String filename, Colorer colorer, BlockLayout layout) throws IOException {
	    ParameterSet params = new ParameterSet();
	    params.set("lineWidth", 2);
	    params.set("pointRadius", 4);
	    draw(em, w, h, filename, colorer, layout, null, params);
	}
	
	public void draw(BlockEmbedding em, int w, int h, String filename, 
	                 Colorer colorer, BlockLayout layout, Refiner refiner, ParameterSet params) throws IOException {
		int border = 20;
		int ww = w + (2 * border);
		int hh = h + (2 * border);
		Rectangle2D canvas = new Rectangle2D.Double(border, border, w, h);
		if (layout == null) {
		    layout = new ConcentricCircularLayout(params);
		}
		// TODO : fix the combinations of null refiners/colorers/layouts...
//		if (colorer == null) {
//		    if (refiner == null) {
//		    } else {
//		    }
//		} else {
//		}
		BufferedImage image = new BufferedImage(ww, hh, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) image.getGraphics();
		GraphRenderer renderer = new GraphRenderer(g, new NullLayout(layout.layout(em, canvas)), params);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ww, hh);
		g.setColor(Color.BLACK);
		renderer.render(null, canvas);
		
		// write
		File dir = new File(getOutputDir());
		if (!dir.exists()) { dir.mkdir(); }
		ImageIO.write(image, "PNG", new File(dir, filename));
	}

}
