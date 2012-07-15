package render;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2d;

import model.Graph;
import renderer.AbstractRenderer;
import sketch.GraphSketcher;
import awt.BasicAWTPainter;

public class GraphRenderer extends AbstractRenderer<Graph> {
	
	public GraphRenderer(Graphics g) {
		setSketcher(new GraphSketcher());
		setPainter(new BasicAWTPainter(g));
	}

	@Override
	public void render(Graph model, Rectangle2D canvas) {
		painter.paint(sketcher.sketch(model), canvas);
	}

	@Override
	public void render(Graph model, Point2d center) {
		painter.paint(sketcher.sketch(model), center);
	}

}
