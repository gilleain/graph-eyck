package test.sketch;

import model.Graph;

import org.junit.Test;

import sketch.GraphSketcher;
import diagram.DiagramPrinter;
import diagram.element.IDiagramElement;

public class GraphSketcherTest {
	
	@Test
	public void testBasicOutput() {
		Graph g = new Graph("0:1,1:2");
		GraphSketcher sketcher = new GraphSketcher();
		IDiagramElement d = sketcher.sketch(g);
		DiagramPrinter.print(d);
	}

}
