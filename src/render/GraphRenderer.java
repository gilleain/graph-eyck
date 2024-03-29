package render;

import generator.EdgeGenerator;
import generator.VertexShapeGenerator;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import layout.SimpleLayout;
import model.Graph;
import renderer.AbstractRenderer;
import sketch.GraphSketcher;
import awt.BasicAWTPainter;
import diagram.DiagramBounder;
import diagram.DiagramPrinter;
import diagram.DiagramTransformer;
import diagram.element.IDiagramElement;
import draw.ParameterSet;

@SuppressWarnings("unchecked")
public class GraphRenderer extends AbstractRenderer<Graph> {
	
	private Graphics g;	// TMP FIXME
	
	public GraphRenderer(Graphics g) {
		this(g, new GraphLayout(), new ParameterSet());
	}
	
    public GraphRenderer(Graphics g, SimpleLayout layout, ParameterSet params) {
    	GraphSketcher s = new GraphSketcher(layout);
    	s.addVertexGenerator(new VertexShapeGenerator());
//    	s.addVertexGenerator(new VertexLabelGenerator()); // XXX FIXME
    	s.addEdgeGenerator(new EdgeGenerator());
		setSketcher(s);
		setPainter(new BasicAWTPainter(g));
		this.g = g;
	}

	@Override
	public void render(Graph model, Rectangle2D canvas) {
		IDiagramElement diagram = (IDiagramElement) sketcher.sketch(model);
//		DiagramPrinter.print(diagram);
		painter.paint(diagram, canvas);
	}

	@Override
	public void render(Graph model, Point2d center) {
		IDiagramElement diagram = (IDiagramElement) sketcher.sketch(model);
		System.out.println("tree before scaling ");
		DiagramPrinter.print(diagram);
		Rectangle2D diagramBounds = new DiagramBounder(g).getBounds(diagram);
		Point2D diagramCenter = new Point2D.Double(diagramBounds.getCenterX(), diagramBounds.getCenterY());
		Point2D canvasCenter = new Point2D.Double(center.x, center.y);
		DiagramTransformer.center(diagram, diagramCenter, canvasCenter);
		System.out.println("tree after scaling ");
		DiagramPrinter.print(diagram);
		painter.paint(diagram, center);
	}

}
