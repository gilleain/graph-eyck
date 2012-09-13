package divide;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import model.Graph;
import diagram.element.CircleElement;
import diagram.element.IDiagramElement;

public class GraphDivider extends AbstractDivider implements IrregularDivider {
	
	private Graph graph;
	
	private IDiagramElement graphDiagram;
	
	public GraphDivider(Graph graph, IDiagramElement graphDiagram) {
		this.graph = graph;
		this.graphDiagram = graphDiagram;
	}
	
	public List<Rectangle2D> divide(Rectangle2D canvas) {
		List<Rectangle2D> subCanvases = new ArrayList<Rectangle2D>();
		subCanvases.add(canvas);
		for (IDiagramElement subElement : graphDiagram.getChildren()) {
			if (subElement instanceof CircleElement) {		// XXX!
				subCanvases.add(subElement.getBounds());	// XXX!
			}
		}
//		System.out.println(subCanvases);
		return subCanvases;
	}

}
