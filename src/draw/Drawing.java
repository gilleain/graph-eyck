package draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import layout.BlockLayout;
import layout.NullLayout;
import layout.Refiner;
import planar.BlockEmbedding;
import planar.Vertex;
import render.GraphRenderer;

public class Drawing {
	
	private BlockEmbedding embedding;
	
	private BlockLayout layout;
	
	private Refiner refiner;
	
	private Colorer colorer;
	
	public Drawing(BlockEmbedding embedding, BlockLayout layout) {
		this.embedding = embedding;
		this.layout = layout;
	}
	
	public Drawing(BlockEmbedding embedding, BlockLayout layout, Refiner refiner) {
		this(embedding, layout);
		this.refiner = refiner;
	}
	
	public Drawing(BlockEmbedding embedding, BlockLayout layout, Refiner refiner, Colorer colorer) {
		this(embedding, layout, refiner);
		this.colorer = colorer;
	}
	
	public void draw(Graphics2D g, int w, int h) {
		draw(g, new Rectangle2D.Double(0, 0, w, h), new ParameterSet());
	}
	
	/**
	 * Draw to fit in a fixed canvas of w*h.
	 * 
	 * @param g
	 * @param w
	 * @param h
	 */
	public void draw(Graphics2D g, Rectangle2D canvas, ParameterSet params) {
		// layout as a cycle
		Representation representation = layout.layout(embedding, canvas);
		Map<Vertex, Color> colorMap = null;
		if (colorer != null) {
			colorMap = colorer.getColors(embedding);
		}
		
		// TODO color
		if (refiner != null) {
			Representation refined = refiner.refine(representation, embedding);
			GraphRenderer renderer = new GraphRenderer(g, new NullLayout(refined));
			renderer.render(null, canvas);
		} else {
			GraphRenderer renderer = new GraphRenderer(g, new NullLayout(representation));
			renderer.render(null, canvas);
		}
	}
	
}
