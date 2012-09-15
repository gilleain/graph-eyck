package sketch;

import generator.EdgeGenerator;
import generator.VertexLabelGenerator;
import generator.VertexShapeGenerator;

import java.util.ArrayList;
import java.util.List;

import layout.SimpleLayout;
import model.Graph;
import model.Metagraph;
import sketcher.Sketcher;
import diagram.element.IDiagramElement;
import draw.ParameterSet;

public class MetagraphSketcher implements Sketcher<Metagraph, List<IDiagramElement>> {
	
	private GraphSketcher graphSketcher;
	
	private GraphSketcher subGraphSketcher;
	
	public MetagraphSketcher(SimpleLayout topLevelLayout, SimpleLayout subLevelLayout) {
		graphSketcher = new GraphSketcher(topLevelLayout, getTopLevelParams());
		graphSketcher.addVertexGenerator(new VertexShapeGenerator());
		graphSketcher.addEdgeGenerator(new EdgeGenerator());
		
		subGraphSketcher = new GraphSketcher(subLevelLayout, getSubLevelParams());
		subGraphSketcher.addVertexGenerator(new VertexShapeGenerator());
		subGraphSketcher.addVertexGenerator(new VertexLabelGenerator());
		subGraphSketcher.addEdgeGenerator(new EdgeGenerator());
	}
	
	private ParameterSet getSubLevelParams() {
		ParameterSet params = new ParameterSet();
		params.set("vertexRadius", 1);
		return params;
	}

	private ParameterSet getTopLevelParams() {
		ParameterSet params = new ParameterSet();
		params.set("vertexRadius", 8);
		return params;	
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
