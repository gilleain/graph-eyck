package planar;

import java.awt.geom.Point2D;

public class PlacedVertex {
	
	private Vertex vertex;
	
	private Point2D point;
	
	public PlacedVertex(Vertex vertex, Point2D point) {
		this.vertex = vertex;
		this.point = point;
	}
	
	public Vertex getVertex() {
		return vertex;
	}
	
	public Point2D getPoint() {
		return point;
	}
	
	public String toString() {
		return vertex + "@" + point;
	}

}
