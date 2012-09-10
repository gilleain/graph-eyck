package model;

import java.util.ArrayList;
import java.util.List;

public class Metagraph {
	
	private Graph metagraph;
	
	private List<Graph> subgraphs;
	
	public Metagraph() {
		metagraph = new Graph();
		subgraphs = new ArrayList<Graph>();
	}
	
	public void addSubgraph(Graph graph) {
		subgraphs.add(graph);
	}
	
	public void addEdge(int a, int b) {
		metagraph.makeEdge(a, b);
	}
	
	public Graph getMetagraph() {
		return metagraph;
	}

	public List<Graph> getGraphs() {
		return subgraphs;
	}

}
