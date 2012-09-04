package test.sketch;

import layout.CircleLayout;
import model.Graph;

import org.junit.Test;

import sketch.GraphSketcher;
import diagram.DiagramPrinter;
import diagram.element.IDiagramElement;
import draw.ParameterSet;

public class GraphSketcherTest {
	
	@Test
	public void testBasicOutput() {
		Graph g = new Graph("0:1,1:2");
		GraphSketcher sketcher = new GraphSketcher(new CircleLayout(new ParameterSet()));
		IDiagramElement d = sketcher.sketch(g);
		DiagramPrinter.print(d);
	}

}
