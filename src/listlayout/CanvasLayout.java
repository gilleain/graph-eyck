package listlayout;

import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.vecmath.Point2d;

import model.Graph;
import sketcher.Sketcher;
import diagram.element.IDiagramElement;
import draw.CanvasRepresentation;
import draw.ParameterSet;

/**
 * Canvas layouts provide a mapping between graphs and canvases, such that all canvases are the same size.
 * 
 * @author maclean
 *
 */
public interface CanvasLayout {
	
	public ParameterSet getParams();
	
	public CanvasRepresentation layout(List<Graph> graphs, Sketcher<Graph, IDiagramElement> sketcher, Rectangle2D canvas);

	public CanvasRepresentation layout(List<Graph> graphs, Sketcher<Graph, IDiagramElement> sketcher, Point2d point);

}
