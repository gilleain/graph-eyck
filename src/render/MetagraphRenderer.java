package render;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import model.Metagraph;
import renderer.AbstractRenderer;
import sketcher.Sketcher;
import awt.ListAWTPainter;
import diagram.DiagramTransformer;
import diagram.element.IDiagramElement;
import divide.GraphDivider;

public class MetagraphRenderer extends AbstractRenderer<Metagraph> {
	
	private Sketcher<Metagraph, List<IDiagramElement>> metagraphSketcher;
	
	private ListAWTPainter painter;
	
	public MetagraphRenderer(Graphics graphics) {
		painter = new ListAWTPainter(graphics);
	}

	@Override
	public void render(Metagraph metagraph, Rectangle2D canvas) {
		List<IDiagramElement> diagrams = metagraphSketcher.sketch(metagraph);
		
		// get the top-level graph and scale it to fit the canvas
		IDiagramElement metagraphDiagram = diagrams.get(0);	// XXX
		DiagramTransformer.scaleToFit(metagraphDiagram, canvas);
		
		painter.paint(diagrams, canvas, new GraphDivider(metagraph.getMetagraph()));
	}

	@Override
	public void render(Metagraph metagraph, Point2d center) {
		// TODO Auto-generated method stub
	}

}
