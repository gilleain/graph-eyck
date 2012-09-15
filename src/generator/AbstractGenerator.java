package generator;

import java.awt.geom.Point2D;

import javax.vecmath.Point2d;

import draw.ParameterSet;

public abstract class AbstractGenerator {
	
	protected ParameterSet params;
	
	public void setParams(ParameterSet params) {
		this.params = params;
	}
	
	public Point2d pointToPoint(Point2D p) {
		return new Point2d(p.getX(), p.getY());
	}

}
