package sketch;

import generator.IGenerator;

import java.util.ArrayList;
import java.util.List;

import layout.SimpleLayout;
import model.Graph;
import model.Metagraph;
import planar.Edge;
import sketcher.Sketcher;
import diagram.element.IDiagramElement;

public class MetagraphSketcher implements Sketcher<Metagraph, List<IDiagramElement>> {
	
	private List<IGenerator<Edge>> edgeGenerators;
	
	private GraphSketcher graphSketcher;
	
	private GraphSketcher subGraphSketcher;
	
	public MetagraphSketcher(SimpleLayout topLevelLayout, SimpleLayout subLevelLayout) {
		this.edgeGenerators = new ArrayList<IGenerator<Edge>>();
		graphSketcher = new GraphSketcher(topLevelLayout);
		subGraphSketcher = new GraphSketcher(subLevelLayout);
	}
	
	public void addEdgeGenerator(IGenerator<Edge> edgeGenerator) {
		this.edgeGenerators.add(edgeGenerator);
	}

	@Override
	public List<IDiagramElement> sketch(Metagraph metagraph) {
		Graph metagraphGraph = metagraph.getMetagraph();
		List<IDiagramElement> diagrams = new ArrayList<IDiagramElement>();
		diagrams.add(graphSketcher.sketch(metagraphGraph));
		for (Graph subgraph : metagraph.getGraphs()) {
			diagrams.add(subGraphSketcher.sketch(subgraph));
		}
		return diagrams;
	}

}
