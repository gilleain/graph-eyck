package render;

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
		painter = new ListAWTPainter(graphics, true);
		metagraphSketcher = new MetagraphSketcher(new TopDownTreeLayout(getParams()), new GraphLayout());
	}
	
	private ParameterSet getParams() {
		ParameterSet params = new ParameterSet();
		params.set("edgeLength", 20);
		return params;
	}

	@Override
	public void render(Metagraph metagraph, Rectangle2D canvas) {
		List<IDiagramElement> diagrams = metagraphSketcher.sketch(metagraph);
		
		// get the top-level graph and scale it to fit the canvas
		IDiagramElement metagraphDiagram = diagrams.get(0);	// XXX
		DiagramTransformer.scaleToFit(metagraphDiagram, canvas);
		
		Graph topLevelGraph = metagraph.getMetagraph();
		painter.paint(diagrams, canvas, new GraphDivider(topLevelGraph, metagraphDiagram));
	}

	@Override
	public void render(Metagraph metagraph, Point2d center) {
		// TODO Auto-generated method stub
	}

}
