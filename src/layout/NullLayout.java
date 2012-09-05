package layout;

import java.awt.geom.Rectangle2D;

import model.Graph;
import draw.ParameterSet;
import draw.Representation;

public class NullLayout implements SimpleLayout {
	
	private Representation rep;
	
	public NullLayout(Representation rep) {
		this.rep = rep;
	}

	@Override
	public Representation layout(Graph graph, Rectangle2D canvas) {
		return rep;
	}

	@Override
	public ParameterSet getParameters() {
		return new ParameterSet();
	}

}
