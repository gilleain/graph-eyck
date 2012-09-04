package sketch;

import java.awt.geom.Rectangle2D;

import layout.SimpleLayout;
import model.Graph;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.IDiagramElement;
import draw.Representation;

public class GraphSketcher extends AbstractArtist implements Sketcher<Graph, IDiagramElement> {

	private SimpleLayout layout;
	
	public GraphSketcher(SimpleLayout layout) {
		this.layout = layout;
	}

	@Override
	public IDiagramElement sketch(Graph graph) {
		Rectangle2D canvas = new Rectangle2D.Double(0, 0, 300, 300);
		layout.getParameters().set("edgeLength", 20);
		
		Representation rep = layout.layout(graph, canvas);
		return rep.getDiagram();
	}
}
