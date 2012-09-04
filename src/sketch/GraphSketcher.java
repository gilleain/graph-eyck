package sketch;

import java.awt.geom.Rectangle2D;

import layout.GraphLayout;
import model.Graph;
import planar.GraphEmbedder;
import planar.GraphEmbedding;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.IDiagramElement;
import draw.ParameterSet;
import draw.Representation;

public class GraphSketcher extends AbstractArtist implements Sketcher<Graph, IDiagramElement> {
	
	public GraphSketcher() {
		// TODO : find some way to pass in a Layout
	}

	@Override
	public IDiagramElement sketch(Graph graph) {
		GraphEmbedding embedding = GraphEmbedder.embed(graph);
		
		Rectangle2D canvas = new Rectangle2D.Double(0, 0, 300, 300);
		ParameterSet params = new ParameterSet();
		params.set("edgeLength", 20);
		GraphLayout layout = new GraphLayout(params);
		
		Representation rep = layout.layout(embedding, canvas);
		return rep.getDiagram();
	}
}
