package generator;

import java.awt.Color;
import java.awt.geom.Point2D;

import planar.PlacedVertex;
import diagram.element.CircleElement;
import diagram.element.IDiagramElement;

public class VertexShapeGenerator extends AbstractGenerator implements IGenerator<PlacedVertex> {
	
	@Override
	public IDiagramElement generate(PlacedVertex vertex) {
		int r = (int) params.getDouble("vertexRadius");	// TODO
		
		Point2D p = vertex.getPoint();
		CircleElement element = new CircleElement(pointToPoint(p), r);
		
		boolean fill = params.getBoolean("fillVertex");
		if (fill) {
			Color fillColor = (Color) params.getObject("vertexFillColor");
			element.setFilled(true);
			element.setFillColor(fillColor);
		}
		
		boolean outline = params.getBoolean("outlineVertex");
		if (outline) {
			Color outlineColor = (Color) params.getObject("vertexOutlineColor");
			element.setOutlined(true);
			element.setOutlineColor(outlineColor);
		}
		return element;
	}

}
