package divide;

import java.awt.geom.Rectangle2D;
import java.util.List;

import model.Graph;

public class GraphDivider extends AbstractDivider implements IrregularDivider {
	
	private Graph graph;
	
	public GraphDivider(Graph graph) {
		this.graph = graph;
	}
	
	public List<Rectangle2D> divide(Rectangle2D canvas) {
		return null;
	}

}
