package divide;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point2d;

import model.Graph;
import diagram.element.CircleElement;
import diagram.element.IDiagramElement;

public class GraphDivider extends AbstractDivider implements IrregularDivider {
	
	private Graph graph;
	
	private IDiagramElement graphDiagram;
	
	public GraphDivider(Graph graph, IDiagramElement graphDiagram) {
		this.graph = graph;
		this.graphDiagram = graphDiagram;
	}
	
	public List<Rectangle2D> divide(Rectangle2D canvas) {
		List<Rectangle2D> subCanvases = new ArrayList<Rectangle2D>();
		subCanvases.add(canvas);
		for (IDiagramElement subElement : graphDiagram.getChildren()) {
			if (subElement instanceof CircleElement) {		// XXX!
				Point2d p = ((CircleElement) subElement).center;
				double r = ((CircleElement) subElement).getRadius();
				// use the inner bounds - the square that fits in this circle
				double s = (2 * r) / Math.sqrt(2);
				double s2 = s / 2;
				subCanvases.add(new Rectangle2D.Double(p.x - s2, p.y - s2, s, s));	// XXX!
			}
		}
//		System.out.println(subCanvases);
		return subCanvases;
	}

}
