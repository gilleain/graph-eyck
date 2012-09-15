package test.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import layout.ConcentricCircularLayout;
import model.Graph;

import org.junit.Test;

import render.GraphRenderer;
import draw.ParameterSet;

public class ConcentricCircularLayoutTest extends BaseDrawTest {
    
    public static final String OUT_DIR = "output/planar/concentric";
    
    public void test(Graph g, int w, int h, String filename) throws IOException {
        ParameterSet params = new ParameterSet();
        params.set("lineWidth", 2);
        params.set("pointRadius", 5);
        ConcentricCircularLayout layout = new ConcentricCircularLayout(params);
        Image image = makeBlankImage(w, h);
        Rectangle2D canvas = new Rectangle2D.Double(0, 0, w, h);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.BLACK);
        GraphRenderer renderer = new GraphRenderer(graphics, layout, params);
        renderer.render(g, canvas);
        ImageIO.write((RenderedImage) image, "PNG", getFile(OUT_DIR, filename));
    }
    
    @Test
    public void fourCycle() throws IOException {
        test(new Graph("0:1, 0:2, 1:3, 2:3"), 400, 400, "fourCycle.png");
    }
    
}
