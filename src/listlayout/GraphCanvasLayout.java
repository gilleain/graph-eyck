package listlayout;

import java.util.List;

import diagram.element.IDiagramElement;
import model.Graph;

/**
 * Layout a diagram as a graph of canvases, each of which may contain another diagram.
 * 
 * @author maclean
 *
 */
public class GraphCanvasLayout {
	
	private Graph canvasGraph;
	
	public GraphCanvasLayout(Graph canvasGraph) {
		this.canvasGraph = canvasGraph;
	}

	public void layout(List<IDiagramElement> diagrams) {
		// TODO Auto-generated method stub
		
	}

}
