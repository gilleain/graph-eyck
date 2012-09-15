package sketch;

import generator.IGenerator;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import layout.SimpleLayout;
import model.Graph;
import planar.PlacedEdge;
import planar.PlacedVertex;
import sketcher.Sketcher;
import core.AbstractArtist;
import diagram.element.ElementList;
import diagram.element.IDiagramElement;
import draw.ParameterSet;
import draw.Representation;

/**
 * Sketches the graph by calling a layout, then converts the points and lines into 
 * diagram elements.
 * 
 * @author maclean
 *
 */
public class GraphSketcher extends AbstractArtist implements Sketcher<Graph, IDiagramElement> {

	private SimpleLayout layout;
	
	private ParameterSet params;
	
	private List<IGenerator<PlacedVertex>> vertexGenerators;
	
	private List<IGenerator<PlacedEdge>> edgeGenerators;
	
	public GraphSketcher(SimpleLayout layout) {
		this(layout, new ParameterSet());
	}
	
	public GraphSketcher(SimpleLayout layout, ParameterSet params) {
		this.layout = layout;
		this.params = params;
		this.vertexGenerators = new ArrayList<IGenerator<PlacedVertex>>();
		this.edgeGenerators = new ArrayList<IGenerator<PlacedEdge>>();
	}
	
	public void addVertexGenerator(IGenerator<PlacedVertex> vertexGenerator) {
		vertexGenerator.setParams(params);
		vertexGenerators.add(vertexGenerator);
	}
	
	public void addEdgeGenerator(IGenerator<PlacedEdge> edgeGenerator) {
		edgeGenerator.setParams(params);
		edgeGenerators.add(edgeGenerator);
	}

	@Override
	public IDiagramElement sketch(Graph graph) {
		Rectangle2D canvas = new Rectangle2D.Double(0, 0, 100, 100);
		
		Representation rep = layout.layout(graph, canvas);
		IDiagramElement root = new ElementList();
		for (PlacedVertex v : rep.getPlacedVertices()) {
			for (IGenerator<PlacedVertex> genV : vertexGenerators) {
				root.add(genV.generate(v));
			}
		}
		for (PlacedEdge e : rep.getPlacedEdges()) {
			for (IGenerator<PlacedEdge> genE : edgeGenerators) {
				root.add(genE.generate(e));
			}
		}
		return root;
	}
}
