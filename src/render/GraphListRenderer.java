package render;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import layout.ILayout;
import layout.LinearLayout;
import model.Graph;
import sketch.GraphListSketcher;
import sketch.GraphSketcher;
import sketcher.Sketcher;
import awt.ListAWTPainter;
import diagram.element.IDiagramElement;
import divide.RegularDivider;

/**
 * Render a list of graphs.
 * 
 * @author maclean
 *
 */
public class GraphListRenderer  {
	
	private ILayout canvasLayout;
	
	private RegularDivider canvasDivider;
	
	private Sketcher<List<Graph>, List<IDiagramElement>> listSketcher;
	
	private ListAWTPainter painter;
	
	public GraphListRenderer(Graphics graphics) {
		this(new LinearLayout(), graphics);
	}
	
	public GraphListRenderer(ILayout canvasLayout, Graphics graphics) {
		this.canvasLayout = canvasLayout;
		this.listSketcher = new GraphListSketcher(new GraphSketcher(new GraphLayout()));
		this.painter = new ListAWTPainter(graphics);
	}
	
	public GraphListRenderer(RegularDivider canvasDivider, Graphics graphics) {
		this.canvasDivider = canvasDivider;
		this.listSketcher = new GraphListSketcher(new GraphSketcher(new GraphLayout()));
		this.painter = new ListAWTPainter(graphics);
	}
	
	public void render(List<Graph> graphs, Rectangle2D canvas) {
		List<IDiagramElement> diagrams = listSketcher.sketch(graphs);
		painter.paint(diagrams, canvas, canvasDivider);	
	}

	public void render(List<Graph> graphs, Point2d center) {
		List<IDiagramElement> diagrams = listSketcher.sketch(graphs);
		painter.paint(diagrams, center, canvasLayout);
	}

}
