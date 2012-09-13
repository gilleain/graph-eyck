package sketch;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import layout.SimpleLayout;
import model.Graph;
import model.Metagraph;
import sketcher.Sketcher;
import diagram.element.IDiagramElement;
import draw.Representation;

public class MetagraphSketcher implements Sketcher<Metagraph, List<IDiagramElement>> {
	
	private SimpleLayout topLevelLayout;
	
	private SimpleLayout subLevelLayout;
	
	public MetagraphSketcher(SimpleLayout topLevelLayout, SimpleLayout subLevelLayout) {
		this.topLevelLayout = topLevelLayout;
		this.subLevelLayout = subLevelLayout;
	}

	@Override
	public List<IDiagramElement> sketch(Metagraph metagraph) {
		Graph metagraphGraph = metagraph.getMetagraph();
		List<IDiagramElement> diagrams = new ArrayList<IDiagramElement>();
		Representation topLevelRep = topLevelLayout.layout(metagraphGraph, new Rectangle2D.Double(0, 0, 200, 200));
		diagrams.add(topLevelRep.getDiagram(3));
		for (Graph subgraph : metagraph.getGraphs()) {
			Representation subRep = subLevelLayout.layout(subgraph, new Rectangle2D.Double(0, 0, 100, 100));
			diagrams.add(subRep.getDiagram());
		}
		return diagrams;
	}

}
