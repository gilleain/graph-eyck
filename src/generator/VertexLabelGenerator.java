package generator;

import java.awt.geom.Point2D;

import planar.PlacedVertex;
import diagram.element.IDiagramElement;
import diagram.element.TextElement;

public class VertexLabelGenerator extends AbstractGenerator implements IGenerator<PlacedVertex> {
	
	@Override
	public IDiagramElement generate(PlacedVertex vertex) {
		int i = vertex.getVertex().getIndex();
		Point2D p = vertex.getPoint();
		return new TextElement(String.valueOf(i), pointToPoint(p));
	}

}
