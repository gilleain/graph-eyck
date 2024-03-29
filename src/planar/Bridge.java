package planar;

import java.util.ArrayList;
import java.util.List;

import planar.visitor.DFSPathFinder;

public class Bridge extends GraphObject {
	
	private List<Vertex> endpoints;
	
	public Bridge() {
		super();
		this.endpoints = new ArrayList<Vertex>();
	}
	
	public Bridge(List<Vertex> vertices, List<Edge> edges) {
		super(vertices, edges);
		this.endpoints = new ArrayList<Vertex>();
	}

	public void addEndpoint(Vertex vertex) {
		endpoints.add(vertex);
		super.add(vertex);
	}

	public List<Vertex> getEndpoints() {
		return endpoints;
	}
	
	public SpanningTree getTree() {
	    return new SpanningTree(this);
	}
	
	public List<Path> getAllPaths(List<Vertex> endpoints, Vertex vi, Vertex vj) {
		DFSPathFinder pathFinder = new DFSPathFinder();
		this.accept(pathFinder);
		return pathFinder.getPaths();
	}

	public Path getPath(Vertex vi, Vertex vj) {
		DFSPathFinder pathFinder = new DFSPathFinder();
		this.accept(pathFinder);
		List<Path> paths = pathFinder.getPaths();
		if (paths.isEmpty()) {
			return null;
		} else {
			for (Path path : paths) {
				if (path.hasVertex(vi) && path.hasVertex(vj)) {
					return path;
				}
			}
			return null;
		}
	}
	
	public String toString() {
		return super.toString() + " " + endpoints;
	}

}
