package sketch;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import model.Graph;
import planar.Block;
import planar.Edge;
import planar.GraphEmbedder;
import planar.GraphEmbedding;
import planar.Vertex;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.CircleElement;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import diagram.element.LineElement;
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
		
		ElementList root = new ElementList();
		for (Point2D p : rep.getPoints()) {
			root.add(new CircleElement(new Point2d(p.getX(), p.getY()), 1));
		}
		
		// TODO : FIXME this is terrible!
		for (Block tree : embedding.getTreeParts()) {
			getEdges(tree, root, rep);
		}
		for (Block block : embedding.getBlockParts()) {
			getEdges(block, root, rep);
		}
		return root;
	}
	
	private void getEdges(Block block, ElementList root, Representation rep) {
		for (Edge e : block.getEdges()) {
			Vertex a = rep.getVertex(e.getA().getIndex());
			Vertex b = rep.getVertex(e.getB().getIndex());
			Point2d pA = point2Point(rep.getPoint(a));
			Point2d pB = point2Point(rep.getPoint(b));
			root.add(new LineElement(pA, pB));
		}
	}
	
	// THIS IS FOOLISHNESS
	private Point2d point2Point(Point2D p) {
		return new Point2d(p.getX(), p.getY());
	}

}
