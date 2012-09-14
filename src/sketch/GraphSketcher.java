package sketch;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import layout.SimpleLayout;
import model.Graph;
import planar.Edge;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.CircleElement;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import diagram.element.LineElement;
import draw.Representation;

/**
 * Sketches the graph by calling a layout, then converts the points and lines into 
 * diagram elements.
 * 
 * @author maclean
 *
 */
public class GraphSketcher extends AbstractArtist implements Sketcher<Graph, IDiagramElement> {

	private SimpleLayout layout;
	
	public GraphSketcher(SimpleLayout layout) {
		this.layout = layout;
	}

	@Override
	public IDiagramElement sketch(Graph graph) {
		layout.getParameters().set("edgeLength", 20);
		Rectangle2D canvas = new Rectangle2D.Double(0, 0, 100, 100);
		
		Representation rep = layout.layout(graph, canvas);
		IDiagramElement root = new ElementList();
		int r = 10;	// TODO
		List<Point2D> points = rep.getPoints();
		for (Point2D p : points) {
			root.add(new CircleElement(new Point2d(p.getX(), p.getY()), r));
		}
		for (Edge edge : rep.getEdges()) {
			Point2d pA = point2Point(rep.getPoint(edge.getA()));
			Point2d pB = point2Point(rep.getPoint(edge.getB()));
			root.add(new LineElement(pA, pB));
		}
		return root;
	}
	
	private Point2d point2Point(Point2D p) {
		return new Point2d(p.getX(), p.getY());
	}
}
