package listlayout;

import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import model.Graph;
import draw.Representation;

public class GridLayout implements ListLayout {

	@Override
	public Representation layout(List<Graph> graphs, Rectangle2D canvas) {
		Representation rep = new Representation();
		return rep;
	}

	@Override
	public Representation layout(List<Graph> graphs, Point2d point) {
		// TODO Auto-generated method stub
		return null;
	}

}
