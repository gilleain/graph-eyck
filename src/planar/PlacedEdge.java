package planar;

public class PlacedEdge {
	
	private PlacedVertex a;
	
	private PlacedVertex b;
	
	public PlacedEdge(PlacedVertex a, PlacedVertex b) {
		this.a = a;
		this.b = b;
	}

	public PlacedVertex getA() {
		return a;
	}

	public PlacedVertex getB() {
		return b;
	}
	
	public String toString() {
		return a + ":" + b;
	}

}
