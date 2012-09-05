package render;

import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import listlayout.ListLayout;
import model.Graph;
import renderer.AbstractRenderer;
import draw.Representation;


public class GraphListRenderer extends AbstractRenderer<List<Graph>> {
	
	private ListLayout listLayout;
	
	public GraphListRenderer() {
		
	}
	
	public GraphListRenderer(ListLayout listLayout) {
		this.listLayout = listLayout;
	}
	
	@Override
	public void render(List<Graph> graphs, Rectangle2D canvas) {
		Representation rep = listLayout.layout(graphs, canvas);
	}

	@Override
	public void render(List<Graph> graphs, Point2d center) {
		Representation rep = listLayout.layout(graphs, center);
		
	}

}
