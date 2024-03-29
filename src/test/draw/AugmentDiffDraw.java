package test.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import layout.GraphLayout;
import model.Graph;
import model.GraphFileReader;

import org.junit.Test;

import render.GraphRenderer;
import draw.ParameterSet;

public class AugmentDiffDraw extends BaseDrawTest {
    
    public static final String IN_DIR = "output/diff";
    
    public static final String OUT_DIR = "output/diff/img";
    
    public void drawGraph(Graph g, int w, int h, int edgeLen, boolean drawNumbers, String subdir, String filename) throws IOException {
        ParameterSet params = new ParameterSet();
        params.set("edgeLength", edgeLen);
        params.set("pointRadius", 5);
        params.set("lineWidth", 2);
        if (drawNumbers) {
            params.set("drawNumberLabels", 1);
        } else { 
            params.set("drawNumberLabels", 0);
        }
        
        Image image = makeBlankImage(w, h);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.BLACK);
        
        GraphLayout layout = new GraphLayout(params);
        GraphRenderer renderer = new GraphRenderer(graphics, layout, params);
        renderer.render(g, new Rectangle2D.Double(0, 0, w, h));
        ImageIO.write((RenderedImage) image, "PNG", getFile(new File(OUT_DIR, subdir), filename));
    }
    
    public void drawFile(String inputFilename, int w, int h, String prefix, int limit) throws IOException {
        GraphFileReader graphs = new GraphFileReader(new FileReader(new File(IN_DIR, inputFilename)), 1);
        int counter = 0;
        for (Graph g : graphs) {
            try {
                drawGraph(g, w, h, 30, true, prefix, prefix + "_" + counter + ".png");
                System.out.println("done " + counter + "\t" + g);
            } catch (Exception e) {
                StackTraceElement[] st = e.getStackTrace();
                if (st.length > 0) {
                    System.out.println("errr "  + counter + "\t" + e.getStackTrace()[0] + "\t" + g);
                } else {
                    System.out.println("errr "  + counter + "\t" + e + "\t" + g);
                }
            }
            counter++;
            if (counter == limit) break;
        }
    }
    
    @Test
    public void drawNine11() throws IOException {
        drawGraph(new Graph("0:3, 0:4, 0:6, 0:8, 1:4, 1:5, 1:6, 1:7, 2:5, 2:6, 2:7, 2:8, 3:5, 3:6, 3:7, 4:7, 4:8, 5:8"), 400, 400, 50, true, "misc", "nineEleven");
    }
    
    @Test
    public void drawEights() throws IOException {
        drawFile("eights_diff.txt", 400, 400, "eights", -1);
    }
    
    @Test
    public void drawNines() throws IOException {
        drawFile("nines_diff.txt", 400, 400, "nines", -1);
    }
    
}
