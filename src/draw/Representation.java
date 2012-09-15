package draw;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planar.Edge;
import planar.PlacedEdge;
import planar.PlacedVertex;
import planar.Vertex;

/**
 * A graphical representation of a graph.
 * 
 * @author maclean
 *
 */
public class Representation {
	
	private Map<Vertex, Point2D> points;
	
	private List<PlacedVertex> placedVertices;
	
	private List<PlacedEdge> placedEdges;
	
	private List<Edge> edges;
	
	public Representation() {
		this.points = new HashMap<Vertex, Point2D>();
		this.edges = new ArrayList<Edge>();
		this.placedVertices = new ArrayList<PlacedVertex>();
		this.placedEdges = new ArrayList<PlacedEdge>();
	}
	
	public void addPoint(Vertex vertex, Point2D point) {
		this.points.put(vertex, point);
		this.placedVertices.add(new PlacedVertex(vertex, point));
	}
	
	public Point2D getPoint(Vertex vertex) {
		return points.get(vertex);
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
		this.placedEdges.add(makePlacedEdge(edge));
	}
	
	private PlacedEdge makePlacedEdge(Edge e) {
		PlacedVertex vA = null;
		PlacedVertex vB = null;
		for (PlacedVertex pv : placedVertices) {
			Vertex v = pv.getVertex();
			if (v.equals(e.getA())) {
				vA = pv;
			} else if (v.equals(e.getB())) {
				vB = pv;
			}
		}
		return new PlacedEdge(vA, vB);
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public List<PlacedVertex> getPlacedVertices() {
		return placedVertices;
	}
	
	public List<PlacedEdge> getPlacedEdges() {
		return placedEdges;
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
