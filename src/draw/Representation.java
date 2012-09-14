package draw;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.Point2d;

import planar.Edge;
import planar.Vertex;
import diagram.element.CircleElement;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import diagram.element.LineElement;

/**
 * A graphical representation of a graph.
 * 
 * @author maclean
 *
 */
public class Representation {
	
	private Map<Vertex, Point2D> points;
	
	private List<Edge> edges;
	
	public Representation() {
		this.points = new HashMap<Vertex, Point2D>();
		this.edges = new ArrayList<Edge>();
	}
	
	public IDiagramElement getDiagram() {
		return getDiagram(1);
	}
	
    public IDiagramElement getDiagram(int r) {
		// XXX for now, construct from scratch...
		IDiagramElement root = new ElementList();
		for (Point2D p : points.values()) {
			root.add(new CircleElement(new Point2d(p.getX(), p.getY()), r));
		}
		for (Edge edge : edges) {
			Point2d pA = point2Point(points.get(edge.getA()));
			Point2d pB = point2Point(points.get(edge.getB()));
			root.add(new LineElement(pA, pB));
		}
		return root;
	}
	
	private Point2d point2Point(Point2D p) {
		return new Point2d(p.getX(), p.getY());
	}
	
	public void addPoint(Vertex vertex, Point2D point) {
		this.points.put(vertex, point);
	}
	
	public Point2D getPoint(Vertex vertex) {
		return points.get(vertex);
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public List<Point2D> getPoints() {
		return new ArrayList<Point2D>(points.values());
	}

	public List<Vertex> getVertices() {
		return new ArrayList<Vertex>(points.keySet());
	}
	
	public Vertex getVertex(int index) {
        for (Vertex v : points.keySet()) {
            if (v.getIndex() == index) {
                return v;
            }
        }
        return null;
    }

    public void add(Representation other) {
        points.putAll(other.points);
        edges.addAll(other.edges);
    }

	public String toString() {
	    StringBuffer sb = new StringBuffer();
	    for (Vertex v : points.keySet()) {
	        sb.append(v).append("\t").append(points.get(v)).append("\n");
	    }
	    for (Edge edge : edges) {
	        sb.append(edge);
	    }
	    return sb.toString();
	}

}
