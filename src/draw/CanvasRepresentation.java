package draw;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import model.Graph;
import diagram.element.IDiagramElement;

public class CanvasRepresentation {
	
	private Map<Graph, Rectangle2D> canvasMap;
	
	private IDiagramElement diagram;
	
	public CanvasRepresentation() {
		canvasMap = new HashMap<Graph, Rectangle2D>();
	}
	
	public void addMapping(Graph g, Rectangle2D r) {
		canvasMap.put(g, r);
	}
	
	public void setDiagram(IDiagramElement diagram) {
		this.diagram = diagram;
	}
	
	public IDiagramElement getDiagram() {
		return diagram;
	}

}
