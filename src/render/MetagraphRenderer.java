package render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import layout.GraphLayout;
import layout.TopDownTreeLayout;
import model.Graph;
import model.Metagraph;
import renderer.AbstractRenderer;
import sketch.MetagraphSketcher;
import sketcher.Sketcher;
import awt.ListAWTPainter;
import diagram.DiagramTransformer;
import diagram.element.IDiagramElement;
import divide.GraphDivider;
import draw.ParameterSet;

public class MetagraphRenderer extends AbstractRenderer<Metagraph> {
	
	private Sketcher<Metagraph, List<IDiagramElement>> metagraphSketcher;
	
	private ListAWTPainter painter;
	
	public MetagraphRenderer(Graphics graphics) {
		painter = new ListAWTPainter(graphics, false);
		ParameterSet topLevelParams = getTopLevelParams();
		ParameterSet subLevelParams = getSubLevelParams();
		metagraphSketcher = new MetagraphSketcher(
								topLevelParams,
								new TopDownTreeLayout(topLevelParams),
								subLevelParams,
								new GraphLayout(subLevelParams));
	}
	
	private ParameterSet getSubLevelParams() {
		ParameterSet params = new ParameterSet();
		params.set("vertexRadius", 1);
		params.set("edgeLength", 8);
		
		params.set("fillVertex", true);
		params.set("vertexFillColor", Color.WHITE);
		params.set("outlineVertex", true);
		params.set("vertexOutlineColor", Color.BLACK);
		return params;
	}

	private ParameterSet getTopLevelParams() {
		ParameterSet params = new ParameterSet();
		params.set("vertexRadius", 8);
		params.set("edgeLength", 15);
		params.set("rootLabel", 0);
		
		params.set("fillVertex", true);
		params.set("vertexFillColor", Color.LIGHT_GRAY);
		params.set("outlineVertex", true);
		params.set("vertexOutlineColor", Color.BLACK);
		return params;
	}

	@Override
	public void render(Metagraph metagraph, Rectangle2D canvas) {
		List<IDiagramElement> diagrams = metagraphSketcher.sketch(metagraph);
		
		// get the top-level graph and scale it to fit the canvas
		IDiagramElement metagraphDiagram = diagrams.get(0);	// XXX
		DiagramTransformer.scaleToFit(metagraphDiagram, canvas);
		
		Graph topLevelGraph = metagraph.getMetagraph();
		GraphDivider divider = new GraphDivider(topLevelGraph, metagraphDiagram);
		List<Rectangle2D> canvases = divider.divide(canvas);
		for (int i = 1; i < diagrams.size(); i++) {
			IDiagramElement diagram = diagrams.get(i);
			DiagramTransformer.scaleToFit(diagram, canvases.get(i));
		}
		
		painter.paint(diagrams, canvas, divider);
	}

	@Override
	public void render(Metagraph metagraph, Point2d center) {
		// TODO Auto-generated method stub
	}

}
