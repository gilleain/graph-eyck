package listlayout;

import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import model.Graph;
import draw.Representation;

/**
 * Layout a list of graphs in some manner.
 * 
 * @author maclean
 *
 */
public interface ListLayout {
	
	public Representation layout(List<Graph> graphs, Rectangle2D canvas);
	
	public Representation layout(List<Graph> graphs, Point2d point);

}
