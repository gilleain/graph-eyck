package sketch;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import model.Graph;
import planar.GraphEmbedder;
import planar.GraphEmbedding;
import sketcher.Sketcher;
import diagram.element.CircleElement;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import draw.ParameterSet;
import draw.Representation;

public class GraphSketcher implements Sketcher<Graph, IDiagramElement> {

	@Override
	public IDiagramElement sketch(Graph graph) {
		GraphLayout layout = new GraphLayout(new ParameterSet());
		GraphEmbedding embedding = GraphEmbedder.embed(graph);
		// TODO : remove representation class?
		Representation rep = layout.layout(embedding, new Rectangle2D.Double(-10, -10, 10, 10));
		
		IDiagramElement root = new ElementList();
		for (Point2D p : rep.getPoints()) {
			root.add(new CircleElement(new Point2d(p.getX(), p.getY()), 10));
		}
		return root;
	}

}
