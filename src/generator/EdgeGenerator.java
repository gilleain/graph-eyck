package generator;

import java.awt.geom.Point2D;

import planar.PlacedEdge;
import planar.PlacedVertex;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import diagram.element.LineElement;

public class EdgeGenerator extends AbstractGenerator implements IGenerator<PlacedEdge> {
	
	@Override
	public IDiagramElement generate(PlacedEdge edge) {
		PlacedVertex a = edge.getA();
		PlacedVertex b = edge.getB();
		if (a == null || b == null) return new ElementList();
		Point2D pA = a.getPoint();
		Point2D pB = b.getPoint();
		return new LineElement(pointToPoint(pA), pointToPoint(pB));
	}

}
