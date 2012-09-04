package layout;

import java.awt.geom.Rectangle2D;

import model.Graph;
import draw.ParameterSet;
import draw.Representation;

public interface SimpleLayout {
    
    public Representation layout(Graph graph, Rectangle2D canvas);
    
    public ParameterSet getParameters();
    
}
