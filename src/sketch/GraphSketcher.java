package sketch;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import model.Graph;
import planar.GraphEmbedder;
import planar.GraphEmbedding;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.CircleElement;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import draw.ParameterSet;
import draw.Representation;

public class GraphSketcher extends AbstractArtist implements Sketcher<Graph, IDiagramElement> {

	@Override
	public IDiagramElement sketch(Graph graph) {
		ParameterSet params = new ParameterSet();
		params.set("edgeLength", 20);
		GraphLayout layout = new GraphLayout(params);
		GraphEmbedding embedding = GraphEmbedder.embed(graph);
		// TODO : remove representation class?
		Rectangle2D canvas = new Rectangle2D.Double(0, 0, 300, 300);
		Representation rep = layout.layout(embedding, canvas);
		
		IDiagramElement root = new ElementList();
		for (Point2D p : rep.getPoints()) {
			root.add(new CircleElement(new Point2d(p.getX(), p.getY()), 1));
		}
		return root;
	}

}
