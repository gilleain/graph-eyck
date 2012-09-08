package sketch;

import java.util.ArrayList;
import java.util.List;

import model.Graph;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.IDiagramElement;

/**
 * Sketches a list of graphs by calling a layout for each one, 
 * then converts the points and lines into diagram elements.
 * 
 * @author maclean
 *
 */
public class GraphListSketcher extends AbstractArtist implements Sketcher<List<Graph>, List<IDiagramElement>> {
	
	private Sketcher<Graph, IDiagramElement> graphSketcher;
	
	public GraphListSketcher(Sketcher<Graph, IDiagramElement> graphSketcher) {
		this.graphSketcher = graphSketcher;
	}

	@Override
	public List<IDiagramElement> sketch(List<Graph> graphList) {
		List<IDiagramElement> diagrams = new ArrayList<IDiagramElement>();
		for (Graph g : graphList) {
			diagrams.add(graphSketcher.sketch(g));
		}
		return diagrams;
	}
}
