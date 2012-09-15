package generator;

import java.awt.geom.Point2D;

import planar.PlacedVertex;
import diagram.element.CircleElement;
import diagram.element.IDiagramElement;

public class VertexShapeGenerator extends AbstractGenerator implements IGenerator<PlacedVertex> {
	
	@Override
	public IDiagramElement generate(PlacedVertex vertex) {
		int r = (int) params.get("vertexRadius");
		Point2D p = vertex.getPoint();
		return new CircleElement(pointToPoint(p), r);
	}

}
