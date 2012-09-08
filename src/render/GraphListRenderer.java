package render;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import listlayout.CanvasLayout;
import listlayout.GridCanvasLayout;
import model.Graph;
import sketch.GraphListSketcher;
import sketch.GraphSketcher;
import sketcher.Sketcher;
import awt.BasicAWTPainter;
import awt.CompositeAWTPainter;
import diagram.element.IDiagramElement;


/**
 * Render a list of graphs.
 * 
 * @author maclean
 *
 */
public class GraphListRenderer  {
	
	private CanvasLayout canvasLayout;
	
	private Sketcher<List<Graph>, List<IDiagramElement>> listSketcher;
	
	private CompositeAWTPainter painter;
	
	public GraphListRenderer(Graphics graphics) {
		this(new GridCanvasLayout(), graphics);
	}
	
	public GraphListRenderer(CanvasLayout canvasLayout, Graphics graphics) {
		this.canvasLayout = canvasLayout;
		this.listSketcher = new GraphListSketcher(new GraphSketcher(new GraphLayout()));
		this.painter = new CompositeAWTPainter(graphics);
	}
	
	public void render(List<Graph> graphs, Rectangle2D canvas) {
		List<IDiagramElement> diagrams = listSketcher.sketch(graphs);
	}

	public void render(List<Graph> graphs, Point2d center) {
		List<IDiagramElement> diagrams = listSketcher.sketch(graphs);
		
	}

}
