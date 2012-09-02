package render;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2d;

import model.Graph;
import renderer.AbstractRenderer;
import sketch.GraphSketcher;
import awt.BasicAWTPainter;
import diagram.DiagramBounder;
import diagram.DiagramPrinter;
import diagram.DiagramTransformer;
import diagram.element.IDiagramElement;

@SuppressWarnings("unchecked")
public class GraphRenderer extends AbstractRenderer<Graph> {
	
	private Graphics g;	// TMP FIXME
	
	public GraphRenderer(Graphics g) {
		setSketcher(new GraphSketcher());
		setPainter(new BasicAWTPainter(g));
		this.g = g;
	}

	@Override
	public void render(Graph model, Rectangle2D canvas) {
		IDiagramElement diagram = (IDiagramElement) sketcher.sketch(model);
		DiagramPrinter.print(diagram);
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
